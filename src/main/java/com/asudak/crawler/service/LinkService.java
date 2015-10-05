package com.asudak.crawler.service;

import com.asudak.crawler.entity.Link;
import com.asudak.crawler.vo.LinkVO;

import java.util.List;

public interface LinkService {
    void create(Link link);

    void delete(Link link);

    void update(Link link);

    LinkVO get(int id);

    List<LinkVO> getBySiteId(int id);

    void deleteBySiteId(int id);

}
