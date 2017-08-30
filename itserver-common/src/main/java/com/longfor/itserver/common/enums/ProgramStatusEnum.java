package com.longfor.itserver.common.enums;

/**
 * @author mayee
 *         Created on 2017/2/9 下午7:49
 * @version v1.0
 */
public enum ProgramStatusEnum {

    /* 状态 */
    NOTSTARTED(0, "未开始", "label label-sm label-danger"),
    WORKING(1, "进行中", "label label-sm label-info"),
    COMPLETED(2, "已完成", "label label-sm label-success");

    /* 枚举码 */
    private int code;
    /* 枚举描述 */
    private String text;
    /* 前端渲染时的class字符串 */
    private String classStr;

    ProgramStatusEnum(int code, String text, String classStr) {
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
    public static ProgramStatusEnum getByCode(int code) {
        for (ProgramStatusEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

    public static String printICheckInline(String name, int code) {
        StringBuilder sb = new StringBuilder();
        String notStartedCheckedStr = (NOTSTARTED.getCode() == code) ? "checked" : "";
        String workingCheckedStr = (WORKING.getCode() == code) ? "checked" : "";
        String completedCheckedStr = (COMPLETED.getCode() == code) ? "checked" : "";
        sb.append("<div class=\"icheck-inline\">")
                .append("<label>").append("<input type=\"radio\" value=\"").append(COMPLETED.getCode()).append("\" name=\"").append(name).append("\" class=\"icheck\" ").append(completedCheckedStr).append(" data-radio=\"iradio_square-blue\">")
                .append(COMPLETED.getText())
                .append("</label>")
                .append("<label>").append("<input type=\"radio\" value=\"").append(WORKING.getCode()).append("\" name=\"").append(name).append("\" class=\"icheck\" ").append(workingCheckedStr).append(" data-radio=\"iradio_square-blue\">")
                .append(WORKING.getText())
                .append("</label>")
                .append("<label>").append("<input type=\"radio\" value=\"").append(NOTSTARTED.getCode()).append("\" name=\"").append(name).append("\" class=\"icheck\" ").append(notStartedCheckedStr).append(" data-radio=\"iradio_square-blue\">")
                .append(NOTSTARTED.getText())
                .append("</label>")
                .append("</div>");
        return sb.toString();
    }

}
