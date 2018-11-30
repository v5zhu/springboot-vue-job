package com.wim.quartz.business.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business.entity
 * @description
 * @time 2018/11/16 21:58
 */
@Getter
@Setter
public class ConvertField {
    private String bizType;

    private String field;

    private String fieldKey;

    private String fieldValue;
}