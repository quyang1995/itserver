package com.longfor.itserver.common.vo.programBpm;

public class ApproveVo {
    private String instanceId ;
    private String  toDoId;
    private String  title;
    private String  programId;
    private String  programName;
    private int  programStatus;
    private String  programStatusCh;
    private String  applyName;
    private String  applyTime;

    public String getProgramStatusCh() {
        return programStatusCh;
    }

    public void setProgramStatusCh(String programStatusCh) {
        this.programStatusCh = programStatusCh;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getToDoId() {
        return toDoId;
    }

    public void setToDoId(String toDoId) {
        this.toDoId = toDoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getProgramStatus() {
        return programStatus;
    }

    public void setProgramStatus(int programStatus) {
        this.programStatus = programStatus;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }
}