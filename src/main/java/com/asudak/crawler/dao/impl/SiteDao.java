package com.asudak.crawler.dao.impl;

import com.asudak.crawler.dao.GenericDao;
import com.asudak.crawler.entity.Site;

import javax.ejb.Stateless;

@Stateless
public class SiteDao extends GenericDao {
    public SiteDao() {
        super(Site.class);
    }
}
