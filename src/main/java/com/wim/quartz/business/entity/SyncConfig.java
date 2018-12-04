package com.wim.quartz.business.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business.entity
 * @description
 * @time 2018/11/30 10:59
 */
@Getter
@Setter
public class SyncConfig extends IdEntity {

    private Long bizId;

    private Long netUnitId;

    private String fileFormat;

    private Boolean newSwitch;

    private String description;

    // 关联对象

    private BizType bizType;

    private NetUnit netUnit;

    private List<SyncField> syncFieldList;

    private List<SyncFileError> syncFileErrorList;

    private List<SyncRecordError> syncRecordErrorList;
}
