package com.asudak.crawler.entity;

import com.asudak.crawler.entity.enumeration.Status;

import javax.persistence.*;
import java.net.URL;

@Entity
@NamedQueries({
        @NamedQuery(name = "Link.GET_BY_SITE_ID", query = "SELECT l FROM Link l WHERE l.site.id = :site_id"),
        @NamedQuery(name = "Link.DELETE_BY_SITE_ID", query = "DELETE FROM Link l WHERE l.site.id = :site_id")
})
public class Link {
    public static String GET_BY_SITE_ID = "Link.GET_BY_SITE_ID";
    public static String DELETE_BY_SITE_ID = "Link.DELETE_BY_SITE_ID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private URL url;
    private Status status = Status.NOT_PARSED;
    private int depth = 0;
    private int externals = 0;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Site site;

    public Link() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getExternals() {
        return externals;
    }

    public void setExternals(int externals) {
        this.externals = externals;
    }

    public void increaseExternals() {
        this.externals++;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (id != link.id) return false;
        return !(url != null ? !url.equals(link.url) : link.url != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
