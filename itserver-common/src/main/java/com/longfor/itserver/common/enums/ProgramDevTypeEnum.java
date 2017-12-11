package com.longfor.itserver.common.enums;

/***
 * 项目研发方式枚举
 */
public enum ProgramDevTypeEnum {
    ZTB(1, "招投标"),
    ZY(2, "自研");

    /* 枚举码 */
    private int code;
    /* 枚举描述 */
    private String text;

    ProgramDevTypeEnum(int code, String text) {
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
    public static ProgramDevTypeEnum getByCode(int code) {
        for (ProgramDevTypeEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

}
