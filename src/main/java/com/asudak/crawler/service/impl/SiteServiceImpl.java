package com.asudak.crawler.service.impl;

import com.asudak.crawler.dao.impl.SiteDao;
import com.asudak.crawler.entity.Site;
import com.asudak.crawler.service.ParserService;
import com.asudak.crawler.service.SiteService;
import com.asudak.crawler.vo.SiteVO;
import com.asudak.crawler.vo.adapter.SiteAdapter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class SiteServiceImpl implements SiteService {
    @EJB
    private ParserService parserService;
    @EJB
    private SiteDao siteDao;

    @Override
    public void create(Site site) {
        siteDao.save(site);
    }

    @Override
    public void update(Site site) {
        siteDao.update(site);
    }

    @Override
    public void parse(Site site) {
        parserService.parse(site);
    }

    @Override
    public Site get(int id) {
        return (Site) siteDao.find(id);
    }

    @Override
    public SiteVO getVO(int id) {
        return SiteAdapter.toVO(get(id));
    }

    @Override
    public void delete(int id) {
        siteDao.delete(this.get(id));
    }

    @Override
    public List<SiteVO> getAll() {
        return SiteAdapter.toVO(siteDao.findAll());
    }
}
