package com.zhuxl.job.business.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhuxl
 * @project springboot-vue-job
 * @package com.zhuxl.job.business.entity
 * @description
 * @date 2018/9/21 22:28
 */
@Getter
@Setter
@ApiModel(value = "公共属性对象", description = "存储id,创建时间,修改时间")
public class BaseEntity implements Serializable {

    @ApiModelProperty(name = "创建时间", value = "记录创建日期")
    private Date gmtCreate;

    @ApiModelProperty(name = "修改时间", value = "记录最后修改时间")
    private Date gmtModified;
}
