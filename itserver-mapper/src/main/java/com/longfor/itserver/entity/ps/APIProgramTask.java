package com.longfor.itserver.entity.ps;


/**
 * @version 1.0
 * @Auther wax
 * @Description
 * @Created 2017/8/28
 */
public class APIProgramTask {

    private Long programId;

    private String accountId;

    private String title;

    private String content;

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
