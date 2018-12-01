package com.wim.quartz.business.enumeration;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business
 * @description
 * @time 2018/11/30 10:31
 */
public enum MoveFormalModeEnum implements CodeBaseEnum<Integer> {
    /**
     * 待所有网元加载成功的局数据入正式表
     */
    ALL_NET_UNIT_SUCCESS_IN(0),
    /**
     * 根据moveFormalOfNetunitCode所设置的网元加载成功的局数据入正式表
     */
    SPECIFIC_NET_UNIT_SUCCESS_IN(1);

    private Integer moveFormalMode;

    MoveFormalModeEnum(Integer mode) {
        this.moveFormalMode = mode;
    }


    @Override
    public Integer getValue() {
        return this.moveFormalMode;
    }
}
