package com.wim.quartz.business.enumeration;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business
 * @description
 * @time 2018/11/30 10:31
 */
public enum TableTypeEnum implements CodeBaseEnum<String> {
    /**
     * 同步表
     */
    ISSUE("ISSUE"),
    /**
     * 正式表
     */
    FORMAL("FORMAL"),
    /**
     * 回执表
     */
    SEND("SEND"),
    /**
     * 审批表
     */
    APPROVAL("APPROVAL"),
    /**
     * 网元加载统计表
     */
    LOADING("LOADING"),
    /**
     * 文件表
     */
    FILE("FILE");

    private String type;

    TableTypeEnum(String type) {
        this.type = type;
    }


    @Override
    public String getValue() {
        return this.type;
    }
}
