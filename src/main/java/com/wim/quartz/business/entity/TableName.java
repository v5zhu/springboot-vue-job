package com.wim.quartz.business.entity;

import com.wim.quartz.business.enumeration.TableTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableName {
    private Long id;

    private Long bizId;

    private TableTypeEnum type;

    private String tableName;

    private String description;

    private Integer order;

    // 关联对象

    private BizType bizType;

}