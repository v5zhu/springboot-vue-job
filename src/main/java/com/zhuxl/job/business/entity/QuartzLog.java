package com.zhuxl.job.business.entity;

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
    public static final String TYPE_OF_JOB_LISTENER = "JOB";
    public static final String TYPE_OF_SCHEDULER_LISTENER = "SCHEDULER";
    public static final String TYPE_OF_TRIGGER_LISTENER = "TRIGGER";

    public static final int JOB_GROUP = 1;
    public static final int TRIGGER_GROUP = 2;

    private QuartzLog() {
        Date date = new Date();
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
    }

    public QuartzLog(JobExecutionContext jobExecutionContext, JobExecutionException e, String status) {
        this();
        this.setStatus(status);
        this.setType(TYPE_OF_JOB_LISTENER);
        Trigger trigger = jobExecutionContext.getTrigger();

        ScheduleJobDO scheduleJob = (ScheduleJobDO) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");

        TriggerKey triggerKey = jobExecutionContext.getTrigger().getKey();
        this.setTriggerGroup(triggerKey.getGroup());
        this.setTriggerName(triggerKey.getName());

        this.setJobClass(scheduleJob.getBeanClass());
        this.setCronExpression(scheduleJob.getCronExpression());
        this.setJobGroup(trigger.getJobKey().getGroup());
        this.setJobName(trigger.getJobKey().getName());
        this.setStartTime(trigger.getStartTime());
        this.setEndTime(trigger.getEndTime());
        this.setMisfireInstruction(trigger.getMisfireInstruction());
        this.setPriority(trigger.getPriority());

        this.setRunTime(jobExecutionContext.getJobRunTime());
        this.setPrevFireTime(jobExecutionContext.getPreviousFireTime());
        this.setNextFireTime(jobExecutionContext.getNextFireTime());

        this.setException(e != null ? e.getMessage() : null);
    }

    public QuartzLog(Trigger trigger, SchedulerException e, String status) {
        this();
        this.setStatus(status);
        this.setType(TYPE_OF_TRIGGER_LISTENER);

        this.setTriggerGroup(trigger.getKey().getGroup());
        this.setTriggerName(trigger.getKey().getName());

        this.setJobGroup(trigger.getJobKey().getGroup());
        this.setJobName(trigger.getJobKey().getName());
        this.setStartTime(trigger.getStartTime());
        this.setEndTime(trigger.getEndTime());
        this.setMisfireInstruction(trigger.getMisfireInstruction());
        this.setPriority(trigger.getPriority());

        this.setException(e != null ? e.getMessage() : null);
    }

    public QuartzLog(JobDetail jobDetail, SchedulerException e, String status) throws SchedulerException {
        this();
        this.setStatus(status);
        this.setType(TYPE_OF_SCHEDULER_LISTENER);

        Scheduler scheduler = SpringUtils.getBean("scheduler", Scheduler.class);
        assert scheduler != null;
        Trigger trigger = scheduler.getTriggersOfJob(jobDetail.getKey()).get(0);

        this.setTriggerGroup(trigger.getKey().getGroup());
        this.setTriggerName(trigger.getKey().getName());

        this.setJobGroup(trigger.getJobKey().getGroup());
        this.setJobName(trigger.getJobKey().getName());
        this.setStartTime(trigger.getStartTime());
        this.setEndTime(trigger.getEndTime());
        this.setMisfireInstruction(trigger.getMisfireInstruction());
        this.setPriority(trigger.getPriority());

        this.setException(e != null ? e.getMessage() : null);
    }

    public QuartzLog(TriggerKey triggerKey, SchedulerException e, String status) throws SchedulerException {
        this(SpringUtils.getBean("scheduler", Scheduler.class).getTrigger(triggerKey), e, status);
    }

    public QuartzLog(JobKey jobKey, SchedulerException e, String status) throws SchedulerException {
        this(SpringUtils.getBean("scheduler", Scheduler.class).getJobDetail(jobKey), e, status);
    }

    public QuartzLog(String group, Integer type, SchedulerException e, String status) {
        this();
        this.setStatus(status);
        this.setType(TYPE_OF_SCHEDULER_LISTENER);

        if (type == JOB_GROUP) {
            this.setJobGroup(group);
        } else if (type == TRIGGER_GROUP) {
            this.setTriggerGroup(group);
        }  // do nothing


        this.setException(e != null ? e.getMessage() : null);
    }


    private String type;

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

    private String threadName;

    private String threadGroup;

    private Integer activeGroupCount;

    private Integer activeCount;

    private String status;

    private String exception;

}
