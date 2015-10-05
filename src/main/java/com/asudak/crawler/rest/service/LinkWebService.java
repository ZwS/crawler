package com.asudak.crawler.rest.service;

import com.asudak.crawler.service.LinkService;
import com.asudak.crawler.vo.LinkVO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("site")
public class LinkWebService {
    @EJB
    private LinkService linkService;

    @GET
    @Path("{site_id}/link")
    @Produces(MediaType.APPLICATION_JSON)
    public List<LinkVO> getLinks(@PathParam("site_id") int site_id) {
        return linkService.getBySiteId(site_id);
    }

    @GET
    @Path("{site_id}/link/{link_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLink(@PathParam("site_id") int site_id, @PathParam("link_id") int link_id) {
        LinkVO link = linkService.get(link_id);
        return Response.ok(link).build();
    }
}
