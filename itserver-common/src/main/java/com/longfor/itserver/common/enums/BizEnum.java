package com.longfor.itserver.common.enums;

/**
 * @author mayee
 *         Created on 2017/2/9 下午3:41
 * @version v1.0
 */
public enum BizEnum {

    /* ----------- 成功信息 S开头 ----------- */
    SSSS("SSSS", "操作成功"),
    SSSS_C("SSSS_C", "新增成功"),
    SSSS_R("SSSS_R", "加载成功"),
    SSSS_U("SSSS_U", "更新成功"),
    SSSS_D("SSSS_D", "删除成功"),
    SSSS_S("SSSS_S", "发送成功"),

    /* ----------- 全局异常 E开头 ----------- */
    E9999("E9999", "系统异常,请稍后再试"),
    E9998("E9998", "请求被拒绝"),
    E9997("E9997", "身份校验失败"),
    E9996("E9996", "操作过于频繁"),
    E9995("E9995", "验证码错误或失效"),
    E9994("E9994", "参数异常"),
    E9993("E9993", "[ {?} ]参数非空"),
    E9992("E9992", "[ {?} ]格式错误"),
    E9991("E9991", "附件不存在"),

    /* ACCOUNT E10开头 */
    E1001("E1001", "用户不存在"),
    E1002("E1002", "用户名或密码错误"),
    E1003("E1003", "{?} [ {?} ] 已存在"),
    E1004("E1004", "[ {?} ] 只能包含大小写字母和数字 [4 ~ 20个字符]"),
    E1005("E1005", "[ {?} ] 只能包含a-zA-Z0-9!@#$%^*_ [6 ~ 16个字符]"),
    E1006("E1006", "[ {?} ] 两次输入不一致"),
    E1007("E1007", "[ {?} ] 长度在2 ~ 10个字符之间"),
    E1008("E1008", "[ {?} ] 请输入合法的邮箱地址"),
    E1009("E1009", "[ {?} ] 请输入合法的移动电话号码"),
    E1010("E1010", "[ {?} ] 请选择机构和部门"),
    E1011("E1011", "[ {?} ] 不能为空"),
    E1012("E1012", "[ {?} ] 最多可选10个角色"),
    E1013("E1013", "请先移除所有子部门"),
    E1014("E1014", "请先移除该部门下所有用户"),
    E1015("E1015", "不能将自己设为上级部门"),
    E1016("E1016", "[ {?} ] 长度在2 ~ 20个字符之间"),
    E1017("E1017", "[ {?} ] 不存在"),
    E1018("E1018", "请先移除该角色下所有用户"),
    E1019("E1019", "用户[ {?} ]不可以没有任何角色"),
    E1020("E1020", "密码错误次数过多！请在{?}分钟后重试"),
    E1021("E1021", "此账户已被锁定"),
    E1022("E1022", "登录失败次数过多"),
    E1023("E1023", "旧密码不正确"),
    E1024("E1024", "新密码不能与旧密码相同"),
    E1025("E1025", "密码错误"),
    E1026("E1026", "此账户没有修改权限"),

    /* INVESTMENT E11开头 */
    E1101("E1101", "[ {?} ] 长度在1 ~ 50个字符之间"),
    E1102("E1102", "[ {?} ] 最多{?}个字符");


    /* 枚举码 */
    private String code;
    /* 枚举描述 */
    private String text;

    BizEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
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
    public static BizEnum getByCode(String code) {
        for (BizEnum bizEnum : values()) {
            if (code.equals(bizEnum.getCode())) {
                return bizEnum;
            }
        }
        return null;
    }



}
