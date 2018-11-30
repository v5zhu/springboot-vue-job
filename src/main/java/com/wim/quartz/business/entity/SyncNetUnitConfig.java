package com.wim.quartz.business.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business.entity
 * @description
 * @time 2018/11/30 10:59
 */
@Getter
@Setter
public class SyncNetUnitConfig {

    private String bizType;

    private String netUnitCode;

    private String netUnitName;

    private String netUnitFtpName;

    private String fileFormat;

    private String bizTypeFields;

    private String fileFields;

    private String description;
}
