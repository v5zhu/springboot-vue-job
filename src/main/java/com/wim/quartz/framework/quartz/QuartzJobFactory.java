package com.wim.quartz.framework.quartz;

import com.wim.quartz.business.entity.ScheduleJobDO;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuxl
 */
public class QuartzJobFactory implements Job {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext context) {
        ScheduleJobDO scheduleJob = (ScheduleJobDO) context.getMergedJobDataMap().get("scheduleJob");
        JobUtils.invokMethod(scheduleJob);
    }
}