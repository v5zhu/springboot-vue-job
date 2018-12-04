package com.wim.quartz.business.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SyncFileError extends IdEntity {
    private Long syncId;

    private String dictCode;

    private String fileErrorCode;

    private Integer order;

    //关联对象
    
    private SyncConfig syncConfig;

}