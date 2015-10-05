package com.asudak.crawler.service.impl;

import com.asudak.crawler.dao.impl.LinkDao;
import com.asudak.crawler.entity.Link;
import com.asudak.crawler.service.LinkService;
import com.asudak.crawler.vo.LinkVO;
import com.asudak.crawler.vo.adapter.LinkAdapter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class LinkServiceImpl implements LinkService {
    @EJB
    private LinkDao linkDao;

    @Override
    public void create(Link link) {
        linkDao.save(link);
    }

    @Override
    public void delete(Link link) {
        linkDao.delete(link);
    }

    @Override
    public void update(Link link) {
        linkDao.update(link);
    }

    @Override
    public LinkVO get(int id) {
        return LinkAdapter.toVO((Link) linkDao.find(id));
    }

    @Override
    public List<LinkVO> getBySiteId(int id) {
        return LinkAdapter.toVO(linkDao.getBySiteId(id));
    }

    @Override
    public void deleteBySiteId(int id) {
        linkDao.deleteBySiteId(id);
    }
}
