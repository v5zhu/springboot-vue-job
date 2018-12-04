package com.wim.quartz.business.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldConvertDict {
    private String fieldName;

    private String fieldKey;

    private String fieldValue;

    private String display;

    private Integer order;

}