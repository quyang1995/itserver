package com.longfor.itserver.common.vo.MoApprove;

import com.longfor.itserver.common.vo.programBpm.ProgramManagerVo;

import java.util.List;


public class MoApproveVo {
    /** id */
    private int id;
    /** 待办id/已办id */
    private String todoId;
    /** 系统编号 */
    private String systemNo;
    /** 标题 */
    private String title;
    /**DOHALFDATA 状态 0:待审批，1:已审批，2:已取消 3:已删除 4:审批中 5:审批失败 */
    private int todoStatus;
    /** 流程编号 */
    private String flowNo;
    /** 业务类型 */
    private String business_type;
    /** 发布人用户名 */
    private String pubUsername;
    /** 审批人用户名 */
    private String appvUsername;
    /** 待办数据 */
    private String dohalfData;
    /** 创建时间 */
    private String createDate;
    /** 更新时间 */
    private String updateDate;
    /** 步骤id */
    private String runnote_id;
    /** oa_url(bmp需传) */
    private String oa_url;
    /** 发布人真实姓名 */
    private String pubTrueName;
    /** 催办 0为催办 */
    private String otherStatus;
    /** 未开放数据 0为未开放 */
    private String notOpen;
    /** 是否是滑动删除 0为删除 */
    private String slideDel;
    /** 点击次数 */
    private String opentimer;
    /** 逻辑删除 0为删除 */
    private String logicDel;
    /** 待办类型 0待办，1通知，2传阅 3、催办、4归档 */
    private int todo_type;

    /** 流程状态 0:未完成,1:已完成,2:已取消 */
    //我的发起列表，流程状态
    private Integer flowStatus;

    public Integer getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(Integer flowStatus) {
        this.flowStatus = flowStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(int todoStatus) {
        this.todoStatus = todoStatus;
    }

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }

    public String getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(String business_type) {
        this.business_type = business_type;
    }

    public String getPubUsername() {
        return pubUsername;
    }

    public void setPubUsername(String pubUsername) {
        this.pubUsername = pubUsername;
    }

    public String getAppvUsername() {
        return appvUsername;
    }

    public void setAppvUsername(String appvUsername) {
        this.appvUsername = appvUsername;
    }

    public String getDohalfData() {
        return dohalfData;
    }

    public void setDohalfData(String dohalfData) {
        this.dohalfData = dohalfData;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRunnote_id() {
        return runnote_id;
    }

    public void setRunnote_id(String runnote_id) {
        this.runnote_id = runnote_id;
    }

    public String getOa_url() {
        return oa_url;
    }

    public void setOa_url(String oa_url) {
        this.oa_url = oa_url;
    }

    public String getPubTrueName() {
        return pubTrueName;
    }

    public void setPubTrueName(String pubTrueName) {
        this.pubTrueName = pubTrueName;
    }

    public String getOtherStatus() {
        return otherStatus;
    }

    public void setOtherStatus(String otherStatus) {
        this.otherStatus = otherStatus;
    }

    public String getNotOpen() {
        return notOpen;
    }

    public void setNotOpen(String notOpen) {
        this.notOpen = notOpen;
    }

    public String getSlideDel() {
        return slideDel;
    }

    public void setSlideDel(String slideDel) {
        this.slideDel = slideDel;
    }

    public String getOpentimer() {
        return opentimer;
    }

    public void setOpentimer(String opentimer) {
        this.opentimer = opentimer;
    }

    public String getLogicDel() {
        return logicDel;
    }

    public void setLogicDel(String logicDel) {
        this.logicDel = logicDel;
    }

    public int getTodo_type() {
        return todo_type;
    }

    public void setTodo_type(int todo_type) {
        this.todo_type = todo_type;
    }
}