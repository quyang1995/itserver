package com.longfor.itserver.common.enums;

/**
 * @author mayee
 *         Created on 2017/2/9 下午7:49
 * @version v1.0
 */
public enum ProductStatusEnum {

    /* 状态 */
    AUTO(2, "待上线", "label label-sm label-info"),
    ONLINE(1, "已上线", "label label-sm label-info"),
    OFFLINE(0, "已下线", "label label-sm label-danger");

    /* 枚举码 */
    private int code;
    /* 枚举描述 */
    private String text;
    /* 前端渲染时的class字符串 */
    private String classStr;

    ProductStatusEnum(int code, String text, String classStr) {
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
    public static ProductStatusEnum getByCode(int code) {
        for (ProductStatusEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

    public static String printICheckInline(String name, int code) {
        StringBuilder sb = new StringBuilder();
        String offLineCheckedStr = (OFFLINE.getCode() == code) ? "checked" : "";
        String onLineCheckedStr = (ONLINE.getCode() == code) ? "checked" : "";
        sb.append("<div class=\"icheck-inline\">")
                .append("<label>").append("<input type=\"radio\" value=\"").append(ONLINE.getCode()).append("\" name=\"").append(name).append("\" class=\"icheck\" ").append(onLineCheckedStr).append(" data-radio=\"iradio_square-blue\">")
                .append(ONLINE.getText())
                .append("</label>")
                .append("<label>").append("<input type=\"radio\" value=\"").append(OFFLINE.getCode()).append("\" name=\"").append(name).append("\" class=\"icheck\" ").append(offLineCheckedStr).append(" data-radio=\"iradio_square-blue\">")
                .append(OFFLINE.getText())
                .append("</label>")
                .append("</div>");
        return sb.toString();
    }

}
