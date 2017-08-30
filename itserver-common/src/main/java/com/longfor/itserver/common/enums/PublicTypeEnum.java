package com.longfor.itserver.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author mayee
 *         Created on 2017/2/9 下午7:49
 * @version v1.0
 */
public enum PublicTypeEnum {

    /* 状态 */
    PUBLIC(1, "公开", "label label-sm label-info"),
    PRIVATE(0, "私有", "label label-sm label-danger");

    /* 枚举码 */
    private int code;
    /* 枚举描述 */
    private String text;
    /* 前端渲染时的class字符串 */
    private String classStr;

    PublicTypeEnum(int code, String text, String classStr) {
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
    public static PublicTypeEnum getByCode(int code) {
        for (PublicTypeEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

    public static String printICheckInline(String name, int code) {
        StringBuilder sb = new StringBuilder();
        String publicCheckedStr = (PUBLIC.getCode() == code) ? "checked" : "";
        String privateCheckedStr = (PRIVATE.getCode() == code) ? "checked" : "";
        sb.append("<div class=\"icheck-inline\">")
                .append("<label>").append("<input type=\"radio\" value=\"").append(PUBLIC.getCode()).append("\" name=\"").append(name).append("\" class=\"icheck\" ").append(publicCheckedStr).append(" data-radio=\"iradio_square-blue\">")
                .append(PUBLIC.getText())
                .append("</label>")
                .append("<label>").append("<input type=\"radio\" value=\"").append(PRIVATE.getCode()).append("\" name=\"").append(name).append("\" class=\"icheck\" ").append(privateCheckedStr).append(" data-radio=\"iradio_square-blue\">")
                .append(PRIVATE.getText())
                .append("</label>")
                .append("</div>");
        return sb.toString();
    }

    public static String printSelect2HTML(String val) {
        StringBuilder sb = new StringBuilder();
        sb.append("<select id=\"status\" name=\"EQ#type\" class=\"form-control select2\"\"").append("\">");
        String selected_private = "";
        String selected_public = "";
        if (StringUtils.isNotBlank(val)) {
            int v = Integer.parseInt(val);
            selected_private = (PRIVATE.getCode() == v) ? " selected " : "";
            selected_public = (PUBLIC.getCode() == v) ? " selected " : "";
        } else {
            sb.append("<option selected value=\"-1\">全部</option>");
        }
        sb.append("<option ").append(selected_private).append(" value=\"").append(PRIVATE.getCode()).append("\">").append(PRIVATE.getText()).append("</option>");
        sb.append("<option ").append(selected_public).append(" value=\"").append(PUBLIC.getCode()).append("\">").append(PUBLIC.getText()).append("</option>");
        sb.append("</select>");
        return sb.toString();
    }

}
