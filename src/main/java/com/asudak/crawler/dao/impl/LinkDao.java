package com.asudak.crawler.dao.impl;

import com.asudak.crawler.dao.GenericDao;
import com.asudak.crawler.entity.Link;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class LinkDao extends GenericDao {
    public LinkDao() {
        super(Link.class);
    }

    public List<Link> getBySiteId(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("site_id", id);
        return findMultipleResults(Link.GET_BY_SITE_ID, params);
    }

    public void deleteBySiteId(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("site_id", id);
        executeUpdate(Link.DELETE_BY_SITE_ID, params);
    }
}
