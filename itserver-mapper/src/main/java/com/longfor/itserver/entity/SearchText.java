package com.longfor.itserver.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.mayee.commons.CustomDateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "search_text")
public class SearchText implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private Integer type;

    @Column(name = "text")
    private String text;

    @Column(name = "pf_acc")
    private String pfAcc;

    @Column(name = "create_time")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPfAcc() {
        return pfAcc;
    }

    public void setPfAcc(String pfAcc) {
        this.pfAcc = pfAcc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}