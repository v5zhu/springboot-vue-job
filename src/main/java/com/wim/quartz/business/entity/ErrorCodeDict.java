package com.wim.quartz.business.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuxiaolong
 * @project springboot-vue-quartz
 * @package com.wim.quartz.business.entity
 * @description
 * @time 2018/12/3 10:36
 */
@Getter
@Setter
public class ErrorCodeDict {
    private String code;

    private String name;

    private String type;

    private String defaultValue;

    private Integer order;
}
