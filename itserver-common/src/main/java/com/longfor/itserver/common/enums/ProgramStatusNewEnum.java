package com.longfor.itserver.common.enums;

/**
 * @author mayee
 *         Created on 2017/2/9 下午7:49
 * @version v1.0
 */
public enum ProgramStatusNewEnum {

    /* 状态 */
    WLX(100, "未立项"),
    LX(110, "立项"),
    DPS(120, "Demo评审"),
    ZTBSQ(130, "招投标申请"),
    ZBSQ(140, "中标申请"),
    CPPS(150, "产品评审"),
    KFPS(160, "开发评审"),
    CSPS(170, "测试评审"),
    SXPS(180, "上线计划"),
    HDFB(190, "灰度发布"),
    XMFP(195, "项目复盘"),
    YQSX(200, "延期上线"),
    XQBG(210, "需求变更"),
    ZZSQ(220, "终止申请"),
    WC(900, "完成"),
    ZZ(999, "终止");

    /* 枚举码 */
    private int code;
    /* 枚举描述 */
    private String text;

    ProgramStatusNewEnum(int code, String text) {
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
    public static ProgramStatusNewEnum getByCode(int code) {
        for (ProgramStatusNewEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

}
