package com.zhuxl.job.business.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author cnlm.me@qq.com
 * @Description: 计划任务信息
 * @date 2017年7月22日
 */
@Setter
@Getter
@ApiModel(value = "调度任务对象", description = "quartz调度对象")
public class ScheduleJobDO extends BaseEntity implements Serializable {

    public static final String STATUS_RUNNING = "1";
    public static final String STATUS_NOT_RUNNING = "0";
    public static final String CONCURRENT_IS = "1";
    public static final String CONCURRENT_NOT = "0";


    @ApiModelProperty(name = "任务ID")
    private Long id;

    @ApiModelProperty(name = "任务名称")
    private String jobName;

    @ApiModelProperty(name = "任务分组")
    private String jobGroup;

    @ApiModelProperty(name = "任务状态", value = "0:停止状态;1:运行状态;")
    private String jobStatus;

    @ApiModelProperty(name = "任务执行时间表达式")
    private String cronExpression;

    @ApiModelProperty(name = "任务功能描述")
    private String description;

    @ApiModelProperty(name = "任务执行时调用哪个类的方法 包名+类名")
    private String beanClass;

    @ApiModelProperty(name = "任务执行方法")
    private String methodName;

    @ApiModelProperty(name = "任务是否有状态,即是否允许并发")
    private String isConcurrent;

    @ApiModelProperty(name = "spring bean名称")
    private String springId;

    @ApiModelProperty(name = "任务执行时传入方法参数")
    private String parameters;
}