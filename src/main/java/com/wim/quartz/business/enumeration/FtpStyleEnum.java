package com.wim.quartz.business.enumeration;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business
 * @description
 * @time 2018/11/30 10:31
 */
public enum FtpStyleEnum implements CodeBaseEnum<String> {
    /**
     * 全量同步
     */
    SYST_UNIX("UNIX"),
    /**
     * 增量同步
     */
    SYST_VMS("VMS"),

    SYST_NT("WINDOWS"),

    SYST_OS2("OS/2"),

    SYST_OS400("OS/400"),

    SYST_MVS("MVS");

    private String ftpStyle;

    FtpStyleEnum(String style) {
        this.ftpStyle = style;
    }


    @Override
    public String getValue() {
        return this.ftpStyle;
    }
}
