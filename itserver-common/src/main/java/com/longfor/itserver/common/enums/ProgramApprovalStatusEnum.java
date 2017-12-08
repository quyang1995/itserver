package com.longfor.itserver.common.enums;

public enum ProgramApprovalStatusEnum {
    /* 状态 */
    SHZ(100, "审核中"),
    SHTG(110, "审核通过"),
    SHBH(120, "审核驳回"),
    BGSHZ(130, "变更审核中"),
    BGSHBH(140, "变更审核驳回");

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
