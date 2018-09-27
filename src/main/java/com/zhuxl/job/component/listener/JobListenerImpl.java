package com.zhuxl.job.component.listener;

import com.zhuxl.job.business.entity.QuartzLog;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @author zhuxl
 */
@Slf4j
public class JobListenerImpl implements JobListener {

    private static final String STATUS_TOBEEXE = "jobToBeExecuted";
    private static final String STATUS_VETOED = "jobExecutionVetoed";
    private static final String STATUS_FINISHED = "jobWasExecuted";

    @Override
    public String getName() {
        //getName() 方法返回一个字符串用以说明 JobListener 的名称。对于注册为全局的监听器，getName() 主要用于记录日志，对于由特定 Job 引用的 JobListener，注册在 JobDetail 上的监听器名称必须匹配从监听器上 getName() 方法的返回值。
        return "JobListenerImpl";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        //Scheduler 在 JobDetail 将要被执行时调用这个方法。
        QuartzLog log = new QuartzLog(jobExecutionContext, null, STATUS_TOBEEXE);

//        System.out.println(JSONObject.toJSONString(log));
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        //Scheduler 在 JobDetail 即将被执行，但又被 TriggerListener 否决了时调用这个方法。
        QuartzLog log = new QuartzLog(jobExecutionContext, null, STATUS_VETOED);

//        System.out.println(JSONObject.toJSONString(log));
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        //Scheduler 在 JobDetail 被执行之后调用这个方法。
        QuartzLog log = new QuartzLog(jobExecutionContext, e, STATUS_FINISHED);

//        System.out.println(JSONObject.toJSONString(log));
    }
}
