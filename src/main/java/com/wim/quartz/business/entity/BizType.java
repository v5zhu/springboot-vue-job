package com.wim.quartz.business.entity;

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
public class BizType {
    private String bizType;

    private String bizTypeCode;

    private String bizTypeName;

    private Integer dataType;

    private String fileRegex;

    private String lowerCase;

    private String upperCase;

    private String firstUpperOtherCamel;

    private String firstLowerOtherCamel;

    private String centerNetworks;

    private String provinceNetworks;

    //关联对象

    private List<BizTypeField> fieldList;

    private List<NetworkElement> centerNetworkList;

    private List<NetworkElement> provinceNetworkList;

}
