package com.longfor.itserver.entity.ps;

import javax.persistence.Transient;

/**
 * Created by wangs on 2017/8/30.
 */
public class PsFeedBackStatus {
    //反馈状态
    @Transient
    private  Long cancel;   //0 已取消

    @Transient
    private Long close;     //1 已关闭

    @Transient
    private Long pending;   //3 待处理

    @Transient
    private Long working;   //4 处理中

    @Transient
    private Long completed; //5 已完成


    public Long getCancel() {
        return cancel;
    }

    public void setCancel(Long cancel) {
        this.cancel = cancel;
    }

    public Long getClose() {
        return close;
    }

    public void setClose(Long close) {
        this.close = close;
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

    public Long getCompleted() {
        return completed;
    }

    public void setCompleted(Long completed) {
        this.completed = completed;
    }
}
