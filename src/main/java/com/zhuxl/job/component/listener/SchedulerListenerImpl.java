package com.zhuxl.job.component.listener;

import com.zhuxl.job.business.entity.QuartzLog;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * @author zhuxl
 */
@Slf4j
public class SchedulerListenerImpl implements SchedulerListener {
    private static final String JOB_SCHEDULED = "jobScheduled";
    private static final String JOB_UNSCHEDULED = "jobUnscheduled";
    private static final String TRIGGER_FINALIZED = "triggerFinalized";
    private static final String TRIGGER_PAUSED = "triggerPaused";
    private static final String TRIGGERS_PAUSED = "triggersPaused";
    private static final String TRIGGER_RESUMED = "triggerResumed";
    private static final String TRIGGERS_RESUMED = "triggersResumed";
    private static final String JOB_ADDED = "jobAdded";
    private static final String JOB_DELETED = "jobDeleted";
    private static final String JOB_PAUSED = "jobPaused";
    private static final String JOBS_PAUSED = "jobsPaused";
    private static final String JOB_RESUMED = "jobResumed";
    private static final String JOBS_RESUMED = "jobsResumed";
    private static final String SCHEDULER_ERROR = "schedulerError";


    @Override
    public void jobScheduled(Trigger trigger) {
        //Scheduler 在有新的 JobDetail 部署时调用此方法。
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        //Scheduler 在有新的 JobDetail卸载时调用此方法
        try {
            QuartzLog log = new QuartzLog(triggerKey, null, JOB_SCHEDULED);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        //当一个 Trigger 来到了再也不会触发的状态时调用这个方法。除非这个 Job 已设置成了持久性，否则它就会从 Scheduler 中移除。
        QuartzLog log = new QuartzLog(trigger, null, JOB_UNSCHEDULED);

    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        //Scheduler 调用这个方法是发生在一个 Trigger 或 Trigger 组被暂停时。假如是 Trigger 组的话，triggerName 参数将为 null。
        try {
            QuartzLog log = new QuartzLog(triggerKey, null, TRIGGER_PAUSED);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void triggersPaused(String triggerGroup) {
        //所在组的全部触发器被停止时被执行
        QuartzLog log = new QuartzLog(triggerGroup, QuartzLog.TRIGGER_GROUP, null, TRIGGERS_PAUSED);
    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        //Scheduler 调用这个方法是发生成一个 Trigger 或 Trigger 组从暂停中恢复时。假如是 Trigger 组的话，triggerName 参数将为 null。
        try {
            QuartzLog log = new QuartzLog(triggerKey, null, TRIGGER_RESUMED);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void triggersResumed(String triggerGroup) {
        //所在组的全部触发器被回复时被执行
        QuartzLog log = new QuartzLog(triggerGroup, QuartzLog.TRIGGER_GROUP, null, TRIGGERS_RESUMED);
    }

    @Override
    public void jobAdded(JobDetail jobDetail) {
        //一个JobDetail被动态添加进来
        try {
            QuartzLog log = new QuartzLog(jobDetail, null, JOB_ADDED);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void jobDeleted(JobKey jobKey) {
        //jobKey + "被删除时被执行"
        try {
            QuartzLog log = new QuartzLog(jobKey, null, JOB_DELETED);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void jobPaused(JobKey jobKey) {
        //当一个或一组 JobDetail 暂停时调用这个方法。
        try {
            QuartzLog log = new QuartzLog(jobKey, null, JOB_PAUSED);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void jobsPaused(String jobGroup) {
        // "(一组任务）被暂停时被执行"
        QuartzLog log = new QuartzLog(jobGroup, QuartzLog.JOB_GROUP, null, JOB_PAUSED);
    }

    @Override
    public void jobResumed(JobKey jobKey) {
        //当一个或一组 Job 从暂停上恢复时调用这个方法。假如是一个 Job 组，jobName 参数将为 null。
        log.info(jobKey.getGroup() + "/" + jobKey.getName());
    }

    @Override
    public void jobsResumed(String jobGroup) {
        //(一组任务）被恢复时被执行
        QuartzLog log = new QuartzLog(jobGroup, QuartzLog.JOB_GROUP, null, JOBS_RESUMED);

    }

    @Override
    public void schedulerError(String msg, SchedulerException e) {
        //Scheduler 的正常运行期间产生一个严重错误时调用这个方法。错误的类型会各式的，但是下面列举了一些错误例子：初始化 Job 类的问题,试图去找到下一 Trigger 的问题,JobStore 中重复的问题,数据存储连接的问题。我们可以使用 SchedulerException 的 getErrorCode() 或者 getUnderlyingException() 方法或获取到特定错误的更详尽的信息。
        QuartzLog log = new QuartzLog(msg, null, e, SCHEDULER_ERROR);
        e.getUnderlyingException().printStackTrace();
    }

    @Override
    public void schedulerInStandbyMode() {
        //scheduler被设为standBy等候模式时被执行
        log.info("started in standbyMode");
    }

    @Override
    public void schedulerStarted() {
        //scheduler启动时被执行
        log.info("scheduler started");
    }

    @Override
    public void schedulerStarting() {
        //scheduler正在启动时被执行
        log.info("scheduler starting");
    }

    @Override
    public void schedulerShutdown() {
        //scheduler关闭时被执行
        log.info("scheduler shutdown");
    }

    @Override
    public void schedulerShuttingdown() {
        //Scheduler 调用这个方法用来通知 SchedulerListener Scheduler 将要被关闭。
        log.info("scheduler shutting down");
    }

    @Override
    public void schedulingDataCleared() {
        //scheduler中所有数据包括jobs, triggers和calendars都被清空时被执行
        log.info("scheduler schedulingDataCleared");
    }
}
