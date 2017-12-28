package com.longfor.itserver.common.enums;

/**
 * @author mayee
 * Created on 2017/2/9 下午7:49
 *
 * @version v1.0
 */
public enum AvaStatusEnum {

    /* 状态 */
    AVA(1, "正常", "label label-sm label-info"),
    UNAVA(0, "不可用", "label label-sm label-danger"),

    /*人员类型*/
    LIABLEAVA(1, "责任人", ""),
    MEMBERAVA(2, "成员", ""),
    /*人员类型角色*/
    PRODAVA(1, "产品经理", ""),
    PROGAVA(2, "项目经理", ""),
    DEVEAVA(3, "开发人员", ""),
    UEDAVA(4, "UED人员", ""),
    TESTINGAVA(5, "测试人员", ""),
    BUSINESSAVA(6, "业务人员", ""),
    OPERATION(7, "运维人员", ""),
    OPERATE(8, "运营人员", ""),
    /*评论回复类型*/
    REPLY_ZERO(0, "未回复", ""),
    REPLY_ONE(1, "已回复", ""),
    ;

    /* 枚举码 */
    private int code;
    /* 枚举描述 */
    private String text;
    /* 前端渲染时的class字符串 */
    private String classStr;

    AvaStatusEnum(int code, String text, String classStr) {
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
    public static AvaStatusEnum getByCode(int code) {
        for (AvaStatusEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

}
