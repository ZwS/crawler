package com.asudak.crawler.service.impl;

import com.asudak.crawler.config.Config;
import com.asudak.crawler.entity.Link;
import com.asudak.crawler.entity.Site;
import com.asudak.crawler.entity.enumeration.Status;
import com.asudak.crawler.service.LinkService;
import com.asudak.crawler.service.ParserService;
import com.asudak.crawler.service.SiteService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.ejb.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ParserServiceImpl implements ParserService {
    @EJB
    private LinkService linkService;
    @EJB
    private SiteService siteService;

    private static final Logger logger = Logger.getLogger(Config.APPLICATION_NAME);

    private Set<URL> siteMap = new HashSet<>();
    private Queue<Link> queue = new ArrayDeque();
    private Link initialLink;

    @Asynchronous
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void parse(Site site) {
        site.setStatus(Status.IN_PROGRESS);
        siteService.update(site);
        linkService.deleteBySiteId(site.getId());

        run(site);

        if (initialLink.getStatus() == Status.NOT_PARSED)
            site.setStatus(Status.NOT_PARSED);
        else
            site.setStatus(Status.PARSED);

        siteService.update(site);
    }

    private void run(Site site) {
        try {
            URL url = new URL("http://" + site.getDomain());

            initialLink = new Link();
            initialLink.setUrl(url);
            initialLink.setSite(site);
            queue.add(initialLink);
            siteMap.add(initialLink.getUrl());
        } catch (MalformedURLException e) {
            logger.log(Level.WARNING, "Malformed URL " + e.getMessage());
        }

        while (!queue.isEmpty())
            parseLink(queue.poll());
    }

    private void parseLink(Link link) {
        Document doc = null;
        try {
            doc = Jsoup.connect(link.getUrl().toString()).get();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Unable to get URL: " + e.getMessage());
            link.setStatus(Status.NOT_PARSED);
        }

        if (doc != null) {
            Elements links = doc.select("a[href]");
            for (Element e : links) {
                URL url;
                try {
                    url = new URL(e.attr("abs:href"));
                } catch (MalformedURLException e1) {
                    logger.log(Level.WARNING, "Malformed URL : " + e1.getMessage());
                    continue;
                }

                if (url.getHost().equals(link.getSite().getDomain())) {
                    if (siteMap.add(url)) {
                        Link childLink = new Link();
                        childLink.setUrl(url);
                        childLink.setSite(link.getSite());
                        childLink.setDepth(link.getDepth() + 1);
                        queue.add(childLink);
                    }
                } else
                    link.increaseExternals();
            }

            link.setStatus(Status.PARSED);
        }

        linkService.create(link);
    }
}
