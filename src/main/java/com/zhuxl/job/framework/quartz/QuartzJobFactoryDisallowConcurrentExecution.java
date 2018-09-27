package com.zhuxl.job.framework.quartz;

import com.zhuxl.job.business.entity.ScheduleJobDO;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author zhuxl
 */
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext context) {
        ScheduleJobDO scheduleJob = (ScheduleJobDO) context.getMergedJobDataMap().get("scheduleJob");
        JobUtils.invokMethod(scheduleJob);

    }
}