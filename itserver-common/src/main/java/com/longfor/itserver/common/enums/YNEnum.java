package com.longfor.itserver.common.enums;

/**
 * @author mayee
 * Created on 2017/2/9 下午7:49
 *
 * @version v1.0
 */
public enum YNEnum {

    /* 状态 */
    YES(1, "是", "label label-sm label-info"),
    NO(0, "否", "label label-sm label-danger");

    /* 枚举码 */
    private int code;
    /* 枚举描述 */
    private String text;
    /* 前端渲染时的class字符串 */
    private String classStr;

    YNEnum(int code, String text, String classStr) {
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
     * @param code 枚举码
     * @return 枚举
     */
    public static YNEnum getByCode(int code) {
        for (YNEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

}
