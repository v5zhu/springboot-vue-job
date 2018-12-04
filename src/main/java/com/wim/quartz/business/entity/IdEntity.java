package com.wim.quartz.business.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuxiaolong
 * @project springboot-vue-quartz
 * @package com.wim.quartz.business.entity
 * @description
 * @time 2018/12/4 14:09
 */
@Getter
@Setter
@ApiModel(value = "具有id的对象", description = "具有long型主键id的对象")
public class IdEntity extends BaseEntity{

    @ApiModelProperty(name = "主键id", value = "主键id，自增序列")
    private Long id;
}
