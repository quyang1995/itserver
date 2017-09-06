package com.longfor.itserver.entity.ps;

import javax.persistence.Transient;

/**
 * @version 1.0
 * @Auther Long.Li
 * @Description
 * @Created 2017/8/9
 */
public class PsProduct {
    @Transient
    private Long id;
    @Transient
    private String name;
    @Transient
    private String type;
    @Transient
    private String status;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
