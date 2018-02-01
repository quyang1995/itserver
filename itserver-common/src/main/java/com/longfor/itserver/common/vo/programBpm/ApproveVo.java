package com.longfor.itserver.common.vo.programBpm;

public class ApproveVo {
    //流程id
    private String instanceId ;
    //节点id
    private String  toDoId;
    //标题
    private String  title;
    //项目id
    private String  programId;
    //项目名称
    private String  programName;
    //项目状态
    private int  programStatus;

    private String  programStatusCh;
    //申请人
    private String  applyName;
    //申请时间
    private String  applyTime;

    /**DOHALFDATA 状态 0:待审批，1:已审批，2:已取消 3:已删除 4:审批中 5:审批失败 */
    private int todoStatus;

    public int getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(int todoStatus) {
        this.todoStatus = todoStatus;
    }

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