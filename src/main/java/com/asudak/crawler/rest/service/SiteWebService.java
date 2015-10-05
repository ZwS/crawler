package com.asudak.crawler.rest.service;

import com.asudak.crawler.entity.Site;
import com.asudak.crawler.service.SiteService;
import com.asudak.crawler.vo.SiteVO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Stateless
@Path("site")
public class SiteWebService {
    @EJB
    SiteService siteService;

    @GET
    public Response getSites() {
        List<SiteVO> sites = siteService.getAll();
        if (sites.size() == 0) {
            return Response.noContent().build();
        }
        return Response.ok(sites).build();
    }

    @POST
    public Response createSite(String domain) {
        Site site = new Site();
        site.setDomain(domain);
        siteService.create(site);
        URI uri;
        try {
            uri = new URI("site/" + site.getId());
        } catch (URISyntaxException e) {
            return Response.serverError().build();
        }
        return Response.created(uri).build();
    }

    @GET
    @Path("{site_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSite(@PathParam("site_id") int site_id) {
        SiteVO site = siteService.getVO(site_id);
        if (site == null)
            return Response.status(404).build();
        return Response.ok(site).build();
    }

    @GET
    @Path("{site_id}/parse")
    public Response parseSite(@PathParam("site_id") int site_id) {
        Site site = siteService.get(site_id);
        if (site == null)
            return Response.status(404).build();
        siteService.parse(site);
        return Response.ok().build();

    }

    @DELETE
    @Path("{site_id}")
    public Response deleteSite(@PathParam("site_id") int site_id) {
        siteService.delete(site_id);
        return Response.ok().build();
    }
}
