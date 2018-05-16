package com.longfor.itserver.common.vo;

import java.io.Serializable;

public class JiraIssue implements Serializable {
    private static final long serialVersionUID = -653566611207069924L;
    private String name;

    private String date;

    private String count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}