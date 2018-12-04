package com.wim.quartz.business.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableFieldRelation {

    private Long tableId;

    private Long fieldId;

    private String description;

    private Integer order;


}