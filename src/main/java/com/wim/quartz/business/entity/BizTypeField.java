package com.wim.quartz.business.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business.entity
 * @description
 * @time 2018/11/16 21:52
 */
@Getter
@Setter
public class BizTypeField {

    private String bizType;

    private String fieldName;

    private String description;

    private String convertField;

    private List<ConvertField> convertFieldList;

}
