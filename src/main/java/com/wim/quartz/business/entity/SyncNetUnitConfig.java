package com.wim.quartz.business.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
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
public class SyncNetUnitConfig {

    private String bizType;

    private String netUnitCode;

    private String netUnitName;

    private String netUnitFtpName;

    private String fileFormat;

    private String syncFields;

    private String description;

    // 关联对象

    private NetUnit netUnit;

}
