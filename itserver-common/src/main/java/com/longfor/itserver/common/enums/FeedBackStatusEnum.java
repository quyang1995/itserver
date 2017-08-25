package com.longfor.itserver.common.enums;

/**
 * @author mayee
 *         Created on 2017/2/9 下午7:49
 * @version v1.0
 */
public enum FeedBackStatusEnum {

    /* 状态 */
    CANCEL(0, "已取消", "label label-sm label-default"),
    CLOSE(1, "已关闭", "label label-sm label-default"),
    PENDING(2, "待处理", "label label-sm label-danger"),
    WORKING(3, "处理中", "label label-sm label-info"),
    COMPLETED(4, "已完成", "label label-sm label-primary");

    /* 枚举码 */
    private int code;
    /* 枚举描述 */
    private String text;
    /* 前端渲染时的class字符串 */
    private String classStr;

    FeedBackStatusEnum(int code, String text, String classStr) {
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
    public static FeedBackStatusEnum getByCode(int code) {
        for (FeedBackStatusEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }


}
