package com.zhuxl.job.component.listener;

import com.zhuxl.job.business.dao.QuartzLogDao;
import com.zhuxl.job.business.entity.QuartzLog;
import com.zhuxl.job.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

import java.util.Date;

/**
 * @author zhuxl
 */
@Slf4j
public class TriggerListenerImpl implements TriggerListener {

    private ThreadLocal<QuartzLog> quartzLog = new ThreadLocal<QuartzLog>();

    private QuartzLogDao getQuartzDao() {

        return InstanceHolder.quartzLogDao;
    }

    private static class InstanceHolder {
        private static QuartzLogDao quartzLogDao = SpringUtils.getBean(QuartzLogDao.class);
    }

    @Override
    public String getName() {
        //定义并返回监听器的名字
        return TriggerListenerImpl.class.getName();
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        //当与监听器相关联的 Trigger 被触发，Job 上的 execute() 方法将要被执行时，Scheduler 就调用这个方法。在全局 TriggerListener 情况下，这个方法为所有 Trigger 被调用。
//        log.info(trigger.getCalendarName() + "/" + jobExecutionContext.getFireInstanceId());
        QuartzLog log = new QuartzLog(jobExecutionContext, null);
        getQuartzDao().insert(log);
        quartzLog.set(log);

    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        //在 Trigger 触发后，Job 将要被执行时由 Scheduler 调用这个方法。TriggerListener 给了一个选择去否决 Job 的执行。假如这个方法返回 true，这个 Job 将不会为此次 Trigger 触发而得到执行。
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        //Scheduler 调用这个方法是在 Trigger 错过触发时。如这个方法的 JavaDoc 所指出的，你应该关注此方法中持续时间长的逻辑：在出现许多错过触发的 Trigger 时，长逻辑会导致骨牌效应。你应当保持这上方法尽量的小。
//        log.info(trigger.getCalendarName());
        log.info(trigger.getKey().getName() + "错过触发");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jec, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
//        log.info(trigger.getCalendarName() + "/" + jobExecutionContext.getFireInstanceId() + "/" + completedExecutionInstruction.name());
        //Trigger 被触发并且完成了 Job 的执行时，Scheduler 调用这个方法。这不是说这个 Trigger 将不再触发了，而仅仅是当前 Trigger 的触发(并且紧接着的 Job 执行) 结束时。这个 Trigger 也许还要在将来触发多次的。
        QuartzLog log = quartzLog.get();
        if (log != null) {

            Date now = new Date();
            log.setEndTime(DateUtils.addMilliseconds(log.getStartTime(), (int) jec.getJobRunTime()));
            log.setGmtModified(now);
            log.setRunTime(jec.getJobRunTime());
            log.setStatus("FINISHED");
            getQuartzDao().updateByPrimaryKeySelective(log);

            quartzLog.remove();
        }

    }
}
