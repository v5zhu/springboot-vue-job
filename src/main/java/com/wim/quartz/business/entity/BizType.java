package com.wim.quartz.business.entity;

import com.wim.quartz.business.enumeration.MoveFormalModeEnum;
import com.wim.quartz.business.enumeration.SourceFromEnum;
import com.wim.quartz.business.enumeration.SyncTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business.entity
 * @description
 * @time 2018/11/16 21:46
 */
@Getter
@Setter
public class BizType extends IdEntity{
    private String bizType;

    private String bizTypeCode;

    private String bizTypeName;

    private String fileRegex;

    private String syncFilePattern;

    private String rejectFilePattern;

    private String receiptFilePattern;

    private Integer maxRecordsOneFile;

    private SyncTypeEnum syncType;

    private SourceFromEnum sourceFrom;

    private MoveFormalModeEnum moveFormalMode;

    private String moveFormalOfNetUnitCode;

    private Integer order;

    // 关联对象
    /**
     * 局数据字段列表
     */
    private List<BizTypeField> fieldList;
    /**
     * 同步网元配置列表
     */
    private List<SyncConfig> syncConfigList;
}


