package com.wim.quartz.business.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableName {
    private Long id;

    private Integer type;

    private String tableName;

    private String description;

    private Integer order;

}