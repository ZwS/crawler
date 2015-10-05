package com.asudak.crawler.service;

import com.asudak.crawler.entity.Site;
import com.asudak.crawler.vo.SiteVO;

import java.util.List;

public interface SiteService {
    void create(Site site);

    void update(Site site);

    void parse(Site site);

    Site get(int id);

    SiteVO getVO(int id);

    void delete(int id);

    List<SiteVO> getAll();
}
