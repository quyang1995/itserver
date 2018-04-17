package com.longfor.itserver.common.enums;

public enum ProgramTypeEnum {

    /* 状态 */
    RJKF(1, "软件开发实施项目", "label label-sm label-danger"),
    YWFW(2, "运维服务、咨询采购项目", "label label-sm label-info"),
    RJXK(3, "软件许可、硬件采购项目", "label label-sm label-success"),
    JCSS(4, "基础设施项目", "label label-sm label-success");

    /* 枚举码 */
    private int code;
    /* 枚举描述 */
    private String text;
    /* 前端渲染时的class字符串 */
    private String classStr;

    ProgramTypeEnum(int code, String text, String classStr) {
        this.code = code;
        this.text = text;
        this.classStr = classStr;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public String getClassStr() {
        return classStr;
    }

    /**
     * 根据枚举码获取枚举
     *
     * @param code 枚举码
     * @return 枚举
     */
    public static ProgramTypeEnum getByCode(int code) {
        for (ProgramTypeEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

}
