package com.wim.quartz.business.enumeration;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business
 * @description
 * @time 2018/11/30 10:31
 */
public enum ErrorCodeEnum implements CodeBaseEnum<String> {
    /**
     * 全网
     */
    FILE("F"),
    /**
     * 本省
     */
    RECORD("R");

    private String type;

    ErrorCodeEnum(String type) {
        this.type = type;
    }


    @Override
    public String getValue() {
        return this.type;
    }
}
