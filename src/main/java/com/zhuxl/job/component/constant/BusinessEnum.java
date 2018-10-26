package com.zhuxl.job.component.constant;

/**
 * @author zhuxiaolong
 * @project springboot-vue-quartz
 * @package com.zhuxl.job.component.constant
 * @description
 * @time 2018/10/25 16:03
 */
public enum BusinessEnum {
    /**
     * quartz业务编码配置
     */
    BUSINESS_QUARTZ_LOG(1000);

    BusinessEnum(Integer code) {
        this.code = code;
    }

    private Integer code;

    public Integer getCode() {
        return code;
    }

}
