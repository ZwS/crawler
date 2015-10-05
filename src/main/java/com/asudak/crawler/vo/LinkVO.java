package com.asudak.crawler.vo;

import com.asudak.crawler.entity.enumeration.Status;

import java.net.URL;

public class LinkVO {
    private int id;
    private URL url;
    private int depth;
    private int externals;
    private Status status;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
