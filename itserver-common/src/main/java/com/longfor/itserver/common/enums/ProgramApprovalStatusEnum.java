package com.longfor.itserver.common.enums;

public enum ProgramApprovalStatusEnum {
    /* 状态 */
    SHZ(100, "审批中"),
    SHTG(110, "审批通过"),
    SHBH(120, "审批驳回"),
    BGSHZ(130, "变更审批中"),
    BGSHBH(140, "变更审批驳回");

    /* 枚举码 */
    private int code;
    /* 枚举描述 */
    private String text;

    ProgramApprovalStatusEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }


    /**
     * 根据枚举码获取枚举
     *
     * @param code 枚举码
     * @return 枚举
     */
    public static ProgramApprovalStatusEnum getByCode(int code) {
        for (ProgramApprovalStatusEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

}
