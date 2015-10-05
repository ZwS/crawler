package com.asudak.crawler.vo.adapter;

import com.asudak.crawler.entity.Site;
import com.asudak.crawler.vo.SiteVO;

import java.util.ArrayList;
import java.util.List;

public class SiteAdapter {
    public static SiteVO toVO(Site site) {
        SiteVO siteVO = new SiteVO();
        siteVO.setId(site.getId());
        siteVO.setDomain(site.getDomain());
        siteVO.setStatus(site.getStatus());
        return siteVO;
    }

    public static List<SiteVO> toVO(List<Site> sites) {
        List<SiteVO> siteVOs = new ArrayList<>();
        for (Site site : sites)
            siteVOs.add(toVO(site));
        return siteVOs;
    }

    public static Site toEntity(SiteVO siteVO) {
        Site site = new Site();
        site.setId(siteVO.getId());
        site.setDomain(siteVO.getDomain());
        site.setStatus(siteVO.getStatus());
        return site;
    }

    public static List<Site> toEntities(List<SiteVO> siteVOs) {
        List<Site> sites = new ArrayList<>();
        for (SiteVO siteVO : siteVOs)
            sites.add(toEntity(siteVO));
        return sites;
    }
}
