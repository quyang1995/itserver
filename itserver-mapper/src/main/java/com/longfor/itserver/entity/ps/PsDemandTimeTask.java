package com.longfor.itserver.entity.ps;

import javax.persistence.Transient;


/**
 * @version 1.0
 * @Auther wax
 * @Description
 * @Created 2017/8/9
 */
public class PsDemandTimeTask {

    @Transient
    private Long amount;
    @Transient
    private String callonAccountId;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCallonAccountId() {
        return callonAccountId;
    }

    public void setCallonAccountId(String callonAccountId) {
        this.callonAccountId = callonAccountId;
    }
}
