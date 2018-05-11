package com.longfor.itserver.common.enums;

/***
 * 项目风险等级枚举
 */
public enum ProgramWarningEnum {
    GREEN(0, "已关闭"),
    YELLOW(1, "中风险"),
    RED(2, "高风险");

    /* 枚举码 */
    private int code;
    /* 枚举描述 */
    private String text;

    ProgramWarningEnum(int code, String text) {
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
    public static ProgramWarningEnum getByCode(int code) {
        for (ProgramWarningEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

}
