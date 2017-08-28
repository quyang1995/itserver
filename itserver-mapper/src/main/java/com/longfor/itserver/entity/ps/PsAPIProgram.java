package com.longfor.itserver.entity.ps;

import javax.persistence.Transient;

/**
 * @version 1.0
 * @Auther wax
 * @Description
 * @Created 2017/8/28
 */
public class PsAPIProgram {
    @Transient
    private Long id;
    @Transient
    private String name;
    @Transient
    private Long pending;
    @Transient
    private Long working;
    @Transient
    private Long complete;

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

    public Long getPending() {
        return pending;
    }

    public void setPending(Long pending) {
        this.pending = pending;
    }

    public Long getWorking() {
        return working;
    }

    public void setWorking(Long working) {
        this.working = working;
    }

    public Long getComplete() {
        return complete;
    }

    public void setComplete(Long complete) {
        this.complete = complete;
    }
}
