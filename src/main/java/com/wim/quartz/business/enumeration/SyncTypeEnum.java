package com.wim.quartz.business.enumeration;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business
 * @description
 * @time 2018/11/30 10:31
 */
public enum SyncTypeEnum implements CodeBaseEnum<Integer> {
    /**
     * 全量同步
     */
    FULL(0),
    /**
     * 增量同步
     */
    INCREMENT(1);

    private Integer syncType;

    SyncTypeEnum(Integer type) {
        this.syncType = type;
    }


    @Override
    public Integer getValue() {
        return this.syncType;
    }
}
