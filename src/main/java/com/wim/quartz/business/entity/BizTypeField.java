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
public class BizTypeField extends IdEntity {

    private Long bizId;

    private String fieldName;

    private Boolean keyword;

    private Boolean auxKeyword;

    private Integer fieldConstraint;

    private String fieldType;

    private String fieldLength;

    private String description;

    private String fieldValueDescription;

    private String defaultValue;

    private String dictField;

    private Integer order;

    private List<FieldConvertDict> fieldConvertList;

}
