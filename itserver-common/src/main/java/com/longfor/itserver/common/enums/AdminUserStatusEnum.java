package com.longfor.itserver.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author mayee
 *         Created on 2017/3/1 10:04
 * @version v1.0
 */
public enum AdminUserStatusEnum {

    /* 管理台用户状态 */
    ADMINUSER_CANCEL(0, "已注销", "label label-sm bg-grey-salsa bg-font-grey-salsa"),
    ADMINUSER_SUPER_ADMIN(1, "超级管理员", "label label-sm bg-dark bg-font-dark"),
    ADMINUSER_ADMIN(2, "管理员", "label label-sm bg-purple bg-font-purple"),
    ADMINUSER_INACTIVATE(3, "未激活", "label label-sm bg-grey-salsa bg-font-grey-salsa"),
    ADMINUSER_NORMAL(4, "正常", "label label-sm label-info"),
    ADMINUSER_LOCKED(5, "锁定", "label label-sm bg-red-mint bg-font-red-mint");

    /* 枚举码 */
    private int code;
    /* 枚举描述 */
    private String text;
    /* 前端渲染时的class字符串 */
    private String classStr;

    AdminUserStatusEnum(int code, String text, String classStr) {
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
    public static AdminUserStatusEnum getByCode(int code) {
        for (AdminUserStatusEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

    public static String getSelect2HTML(String val) {
        StringBuilder sb = new StringBuilder();
        sb.append("<select id=\"status\" name=\"EQ#status\" class=\"form-control select2\"\"").append("\">");
        String selected_cancel = "";
        String selected_super_admin = "";
        String selected_admin = "";
        String selected_inactivate = "";
        String selected_normal = "";
        String selected_locked = "";
        if(StringUtils.isNotBlank(val)){
            int v = Integer.parseInt(val);
            selected_cancel = (ADMINUSER_CANCEL.getCode() == v) ? " selected " : "";
            selected_super_admin = (ADMINUSER_SUPER_ADMIN.getCode() == v) ? " selected " : "";
            selected_admin = (ADMINUSER_ADMIN.getCode() == v) ? " selected " : "";
            selected_inactivate = (ADMINUSER_INACTIVATE.getCode() == v) ? " selected " : "";
            selected_normal = (ADMINUSER_NORMAL.getCode() == v) ? " selected " : "";
            selected_locked = (ADMINUSER_LOCKED.getCode() == v) ? " selected " : "";
        }else{
            sb.append("<option selected value=\"-1\">全部</option>");
        }
        sb.append("<option ").append(selected_cancel).append(" value=\"").append(ADMINUSER_CANCEL.getCode()).append("\">").append(ADMINUSER_CANCEL.getText()).append("</option>");
        sb.append("<option ").append(selected_super_admin).append(" value=\"").append(ADMINUSER_SUPER_ADMIN.getCode()).append("\">").append(ADMINUSER_SUPER_ADMIN.getText()).append("</option>");
        sb.append("<option ").append(selected_admin).append(" value=\"").append(ADMINUSER_ADMIN.getCode()).append("\">").append(ADMINUSER_ADMIN.getText()).append("</option>");
        sb.append("<option ").append(selected_inactivate).append(" value=\"").append(ADMINUSER_INACTIVATE.getCode()).append("\">").append(ADMINUSER_INACTIVATE.getText()).append("</option>");
        sb.append("<option ").append(selected_normal).append(" value=\"").append(ADMINUSER_NORMAL.getCode()).append("\">").append(ADMINUSER_NORMAL.getText()).append("</option>");
        sb.append("<option ").append(selected_locked).append(" value=\"").append(ADMINUSER_LOCKED.getCode()).append("\">").append(ADMINUSER_LOCKED.getText()).append("</option>");
        sb.append("</select>");
        return sb.toString();
    }
//
//    public static String getSelectHTML(String width, String val) {
//        StringBuilder sb = new StringBuilder();
//        String widthStr = (width.contains("%")) ? width : width + "px";
//        sb.append("<select name=\"EQ#status\" id=\"status\" class=\"bs-select form-control\" data-width=\"").append(widthStr).append("\">");
//        String selected_cancel = "";
//        String selected_super_admin = "";
//        String selected_admin = "";
//        String selected_inactivate = "";
//        String selected_normal = "";
//        String selected_locked = "";
//        if(StringUtils.isNotBlank(val)){
//            int v = Integer.parseInt(val);
//            selected_cancel = (ADMINUSER_CANCEL.getCode() == v) ? " selected " : "";
//            selected_super_admin = (ADMINUSER_SUPER_ADMIN.getCode() == v) ? " selected " : "";
//            selected_admin = (ADMINUSER_ADMIN.getCode() == v) ? " selected " : "";
//            selected_inactivate = (ADMINUSER_INACTIVATE.getCode() == v) ? " selected " : "";
//            selected_normal = (ADMINUSER_NORMAL.getCode() == v) ? " selected " : "";
//            selected_locked = (ADMINUSER_LOCKED.getCode() == v) ? " selected " : "";
//        }else{
//            sb.append("<option selected value=\"\">全部</option>");
//        }
//        sb.append("<option ").append(selected_cancel).append(" value=\"").append(ADMINUSER_CANCEL.getCode()).append("\">").append(ADMINUSER_CANCEL.getText()).append("</option>");
//        sb.append("<option ").append(selected_super_admin).append(" value=\"").append(ADMINUSER_SUPER_ADMIN.getCode()).append("\">").append(ADMINUSER_SUPER_ADMIN.getText()).append("</option>");
//        sb.append("<option ").append(selected_admin).append(" value=\"").append(ADMINUSER_ADMIN.getCode()).append("\">").append(ADMINUSER_ADMIN.getText()).append("</option>");
//        sb.append("<option ").append(selected_inactivate).append(" value=\"").append(ADMINUSER_INACTIVATE.getCode()).append("\">").append(ADMINUSER_INACTIVATE.getText()).append("</option>");
//        sb.append("<option ").append(selected_normal).append(" value=\"").append(ADMINUSER_NORMAL.getCode()).append("\">").append(ADMINUSER_NORMAL.getText()).append("</option>");
//        sb.append("<option ").append(selected_locked).append(" value=\"").append(ADMINUSER_LOCKED.getCode()).append("\">").append(ADMINUSER_LOCKED.getText()).append("</option>");
//        sb.append("</select>");
//        return sb.toString();
//    }

}
