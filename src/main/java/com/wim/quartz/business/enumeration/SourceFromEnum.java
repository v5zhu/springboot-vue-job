package com.wim.quartz.business.enumeration;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business
 * @description
 * @time 2018/11/30 10:31
 */
public enum SourceFromEnum implements CodeBaseEnum {
    /**
     * 全网
     */
    ALL(0),
    /**
     * 本省
     */
    LOCAL_PROVINCE(1);

    private Integer sourceFrom;

    SourceFromEnum(Integer from) {
        this.sourceFrom = from;
    }

    @Override
    public int code() {
        return this.sourceFrom;
    }
}
