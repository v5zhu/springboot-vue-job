package com.zhuxl.job.business.entity;

import com.zhuxl.job.util.IDGenUtils;
import com.zhuxl.job.util.SpringUtils;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.quartz.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author zhuxl
 */
@Getter
@Setter
@ApiModel(value = "quartz框架运行日志", description = "记录job、调度日志")
public class QuartzLog extends BaseEntity {

    public QuartzLog() {
        Date date = new Date();
        this.setId(IDGenUtils.timeSeqId());
        this.setGmtCreate(date);
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            this.setHost(inetAddress.getHostAddress());
            this.setHostname(inetAddress.getHostName());
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }

        Thread t = Thread.currentThread();
        this.setThreadId(t.getId());
        this.setThreadName(t.getName());
        this.setThreadGroup(t.getThreadGroup().getName());
        this.setActiveGroupCount(t.getThreadGroup().activeGroupCount());
        this.setActiveCount(t.getThreadGroup().activeCount());
        this.setStatus("RUNNING");
    }

    public QuartzLog(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        this();
        Trigger trigger = jobExecutionContext.getTrigger();

        ScheduleJobDO scheduleJob = (ScheduleJobDO) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");

        TriggerKey triggerKey = jobExecutionContext.getTrigger().getKey();
        this.setTriggerGroup(triggerKey.getGroup());
        this.setTriggerName(triggerKey.getName());

        this.setJobClass(scheduleJob.getBeanClass());
        this.setCronExpression(scheduleJob.getCronExpression());
        this.setJobGroup(trigger.getJobKey().getGroup());
        this.setJobName(trigger.getJobKey().getName());
        this.setMisfireInstruction(trigger.getMisfireInstruction());
        this.setPriority(trigger.getPriority());

        this.setStartTime(trigger.getFireTimeAfter(trigger.getPreviousFireTime()));
        this.setEndTime(null);
        this.setRunTime(jobExecutionContext.getJobRunTime());
        this.setPrevFireTime(trigger.getPreviousFireTime());
        this.setNextFireTime(trigger.getNextFireTime());

        this.setException(e != null ? e.getMessage() : null);
    }

    private String id;

    private String triggerGroup;

    private String triggerName;

    private String jobClass;

    private String jobGroup;

    private String jobName;

    private String cronExpression;

    private Integer misfireInstruction;

    private Date startTime;

    private Date endTime;

    private Long runTime;

    private Date prevFireTime;

    private Date nextFireTime;

    private Integer priority;

    private String host;

    private String hostname;

    private Long threadId;

    private String threadGroup;

    private String threadName;

    private Integer activeGroupCount;

    private Integer activeCount;

    private String status;

    private String exception;

}
