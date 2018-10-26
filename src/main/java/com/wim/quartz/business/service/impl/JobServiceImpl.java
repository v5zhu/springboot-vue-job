package com.wim.quartz.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wim.quartz.framework.quartz.QuartzJobFactory;
import com.wim.quartz.framework.quartz.QuartzJobFactoryDisallowConcurrentExecution;
import com.wim.quartz.business.dao.ScheduleJobDao;
import com.wim.quartz.business.entity.ScheduleJobDO;
import com.wim.quartz.business.service.JobService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * @author zhuxiaolong
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class JobServiceImpl implements JobService {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleJobDao scheduleJobDao;

    /**
     * 从数据库中取 区别于getAllJob
     *
     * @return
     */
    @Override
    public PageInfo<ScheduleJobDO> getTasksForPage(Integer pageNum, Integer pageSize) {
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum, pageSize, true);
        List<ScheduleJobDO> jobs = scheduleJobDao.getAll();
        //分页实现
        //或者使用PageInfo类（下面的例子有介绍）
        PageInfo<ScheduleJobDO> pageInfo = new PageInfo(jobs);

        return pageInfo;
    }

    /**
     * 添加到数据库中 区别于addJob
     */
    @Override
    @Transactional
    public void addTask(ScheduleJobDO job) {
        // 检查job名称和分组是否重复
        int count = scheduleJobDao.selectByNameGroup(job.getJobName(), job.getJobGroup());
        if (count > 0) {
            throw new RuntimeException("Job已存在,请检查名称和分组是否有重复");
        }
        job.setGmtCreate(new Date());
        job.setJobStatus("0");
        scheduleJobDao.insertSelective(job);
    }

    @Override
    @Transactional
    public void editTask(ScheduleJobDO jobDO) {

        int count = scheduleJobDao.selectByNameGroupExceptThis(jobDO.getId(), jobDO.getJobName(), jobDO.getJobGroup());
        if (count > 0) {
            throw new RuntimeException("Job已存在,请检查名称和分组是否有重复");
        }

        jobDO.setGmtModified(new Date());
        scheduleJobDao.updateByPrimaryKey(jobDO);
    }

    /**
     * 从数据库中查询job
     */
    @Override
    public ScheduleJobDO getTaskById(Long jobId) {
        return scheduleJobDao.selectByPrimaryKey(jobId);
    }

    @Override
    @Transactional
    public void delTaskById(Long jobId) {
        scheduleJobDao.deleteByPrimaryKey(jobId);
    }

    /**
     * 更改任务状态
     */
    @Override
    @Transactional
    public void changeStatus(Long jobId, String cmd) {
        ScheduleJobDO job = scheduleJobDao.selectByPrimaryKey(jobId);
        if (job == null) {
            return;
        }
        if ("stop".equals(cmd)) {
            deleteJob(job);
            job.setJobStatus(ScheduleJobDO.STATUS_NOT_RUNNING);
        } else if ("start".equals(cmd)) {
            job.setJobStatus(ScheduleJobDO.STATUS_RUNNING);
            addJob(job);
        }
        scheduleJobDao.updateByPrimaryKeySelective(job);
    }

    /**
     * 更改任务 cron表达式
     */
    @Override
    public void updateCron(Long jobId) {
        ScheduleJobDO job = scheduleJobDao.selectByPrimaryKey(jobId);
        if (job == null) {
            return;
        }
//        job.setCronExpression(cron);
        if (ScheduleJobDO.STATUS_RUNNING.equals(job.getJobStatus())) {
            updateJobCron(job);
        }
//        scheduleJobDao.updateByPrimaryKeySelective(job);
    }

    /**
     * 添加任务
     *
     * @param job
     */
    @Override
    public void addJob(ScheduleJobDO job) {
        if (job == null || !ScheduleJobDO.STATUS_RUNNING.equals(job.getJobStatus())) {
            return;
        }
        logger.debug(scheduler + ".......................................................................................add");
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        try {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 不存在，创建一个
            if (null == trigger) {
                Class clazz = ScheduleJobDO.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;

                JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();

                jobDetail.getJobDataMap().put("scheduleJob", job);

                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression()).withMisfireHandlingInstructionDoNothing();

                trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                // Trigger已存在，那么更新相应的定时设置
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression()).withMisfireHandlingInstructionDoNothing();

                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

                // 按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<ScheduleJobDO> queryAllJobs() {
        return scheduleJobDao.getAll();
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     * @throws SchedulerException
     */
    @Override
    public List<ScheduleJobDO> getAllJob() {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        try {
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            List<ScheduleJobDO> jobList = new ArrayList();
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    ScheduleJobDO job = new ScheduleJobDO();
                    job.setJobName(jobKey.getName());
                    job.setJobGroup(jobKey.getGroup());
                    job.setDescription("触发器:" + trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    job.setJobStatus(triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        job.setCronExpression(cronExpression);
                    }
                    jobList.add(job);
                }
            }
            return jobList;
        } catch (SchedulerException e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * 所有正在运行的job
     *
     * @return
     * @throws SchedulerException
     */
    @Override
    public List<ScheduleJobDO> getRunningJob() {
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            List<ScheduleJobDO> jobList = new ArrayList<ScheduleJobDO>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                ScheduleJobDO job = new ScheduleJobDO();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setDescription("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setJobStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
            return jobList;

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 暂停一个job
     *
     * @param jobGroup
     * @param jobName
     * @throws SchedulerException
     */
    @Override
    public void pauseJob(String jobGroup, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobGroup, jobName);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
            String[] infos = {jobGroup, jobName, e.getMessage()};
            logger.error("停止任务:group [{}],authorName [{}] 失败,异常信息[{}]", infos);
            throw new RuntimeException("暂停任务失败");
        }
    }

    /**
     * 暂停一个job
     *
     * @param ScheduleJobDO
     * @throws TipException
     */
    private void pauseJob(ScheduleJobDO ScheduleJobDO) {
        pauseJob(ScheduleJobDO.getJobName(), ScheduleJobDO.getJobGroup());
    }


    /**
     * 恢复一个job
     *
     * @param jobGroup
     * @param jobName
     * @throws SchedulerException
     */
    @Override
    public void resumeJob(String jobGroup, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            //todo throw TipException
            e.printStackTrace();
        }
    }

    /**
     * 删除一个job
     *
     * @param job
     * @throws SchedulerException
     */
    public void deleteJob(ScheduleJobDO job) {
        deleteJob(job.getJobGroup(), job.getJobName());
    }

    /**
     * 删除一个job
     *
     * @param jobGroup
     * @param jobName
     * @throws SchedulerException
     */
    @Override
    public void deleteJob(String jobGroup, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.deleteJob(jobKey);
            logger.info("任务分组[{}],任务名称 = [{}]------------------已停止", jobGroup, jobName);
        } catch (SchedulerException e) {
            //todo throw TipException
            e.printStackTrace();
        }

    }

    /**
     * 立即执行job
     *
     * @param jobGroup
     * @param jobName
     * @throws SchedulerException
     */
    @Override
    public void runAJobNow(String jobGroup, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            //todo throw TipException
            e.printStackTrace();
        }
    }

    /**
     * 更新job时间表达式
     *
     * @param ScheduleJobDO
     * @throws SchedulerException
     */
    public void updateJobCron(ScheduleJobDO ScheduleJobDO) {

        TriggerKey triggerKey = TriggerKey.triggerKey(ScheduleJobDO.getJobName(), ScheduleJobDO.getJobGroup());

        CronTrigger trigger = null;
        try {
            trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(ScheduleJobDO.getCronExpression()).withMisfireHandlingInstructionDoNothing();
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新job时间表达式
     *
     * @param jobGroup
     * @param jobName
     * @param cronExpression
     * @throws SchedulerException
     */
    @Override
    public void updateJobCron(String jobGroup, String jobName, String cronExpression) {

        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

        CronTrigger trigger = null;
        try {
            trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();

            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Boolean verifyCronExpression(String cronExpression) {
        try {
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
        } catch (Exception e) {
            logger.error("cron表达式有误，不能被解析！");
            return false;
        }
        return true;
    }

}
