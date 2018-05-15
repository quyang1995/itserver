package com.longfor.itserver.common.enums;

/**
 * @author mayee
 * Created on 2017/2/9 下午7:49
 *
 * @version v1.0
 */
public enum AvaStatusTypeEnum {

    /*人员类型角色*/
    PRODAVA(1, "产品经理", ""),
    PROGAVA(2, "项目经理", ""),
    DEVEAVA(3, "开发人员", ""),
    UEDAVA(4, "UED人员", ""),
    TESTINGAVA(5, "测试人员", ""),
    BUSINESSAVA(6, "业务人员", ""),
    OPERATION(7, "运维人员", ""),
    OPERATE(8, "运营人员", "");

    /* 枚举码 */
    private int code;
    /* 枚举描述 */
    private String text;
    /* 前端渲染时的class字符串 */
    private String classStr;

    AvaStatusTypeEnum(int code, String text, String classStr) {
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
    public static AvaStatusTypeEnum getByCode(int code) {
        for (AvaStatusTypeEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

    /**
     * 根据枚举码获取枚举
     * @param code 枚举值
     * @return 枚举
     */
    public static String getTextByCode(int code) {
        for (AvaStatusTypeEnum status : values()) {
            if (status.getCode() == code) {
                return status.getText();
            }
        }
        return null;
    }

}
