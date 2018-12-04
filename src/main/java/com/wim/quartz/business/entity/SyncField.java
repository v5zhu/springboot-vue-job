package com.wim.quartz.business.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SyncField extends IdEntity {

    private Long syncId;

    private String sourceField;

    private String targetField;

    private Boolean newInterface;

    private Integer order;

    //关联对象

    private SyncConfig syncConfig;

}