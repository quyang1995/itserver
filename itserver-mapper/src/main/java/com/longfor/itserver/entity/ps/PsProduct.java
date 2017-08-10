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
}
