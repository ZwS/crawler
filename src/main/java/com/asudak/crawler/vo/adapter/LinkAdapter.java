package com.asudak.crawler.vo.adapter;

import com.asudak.crawler.entity.Link;
import com.asudak.crawler.vo.LinkVO;

import java.util.ArrayList;
import java.util.List;

public class LinkAdapter {
    public static LinkVO toVO(Link link) {
        LinkVO linkVO = new LinkVO();
        linkVO.setId(link.getId());
        linkVO.setUrl(link.getUrl());
        linkVO.setDepth(link.getDepth());
        linkVO.setExternals(link.getExternals());
        linkVO.setStatus(link.getStatus());
        return linkVO;
    }

    public static List<LinkVO> toVO(List<Link> links) {
        List<LinkVO> linkVOs = new ArrayList<>();
        for (Link link : links)
            linkVOs.add(toVO(link));
        return linkVOs;
    }

    public static Link toEntity(LinkVO linkVO) {
        Link link = new Link();
        link.setId(linkVO.getId());
        link.setUrl(linkVO.getUrl());
        link.setDepth(linkVO.getDepth());
        link.setExternals(linkVO.getExternals());
        link.setStatus(linkVO.getStatus());
        return link;
    }

    public static List<Link> toEntities(List<LinkVO> linkVOs) {
        List<Link> links = new ArrayList<>();
        for (LinkVO linkVO : linkVOs)
            links.add(toEntity(linkVO));
        return links;
    }
}
