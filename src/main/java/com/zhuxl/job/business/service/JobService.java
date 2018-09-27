package com.zhuxl.job.business.service;


import com.github.pagehelper.PageInfo;
import com.zhuxl.job.business.entity.ScheduleJobDO;

import java.util.List;

public interface JobService {

    /**
     * 从数据库中取 区别于getAllJob
     *
     * @return
     */
    PageInfo<ScheduleJobDO> getTasksForPage(Integer pageNum, Integer pageSize);

    /**
     * 添加到数据库中 区别于addJob
     *
     * @param job
     */
    void addTask(ScheduleJobDO job);

    /**
     * 修改任务并保持到数据库
     *
     * @param job
     */
    void editTask(ScheduleJobDO job);

    /**
     * 从数据库中查询job
     *
     * @param jobId
     */
    ScheduleJobDO getTaskById(Long jobId);

    /**
     * 根据ID删除定时任务
     *
     * @param jobId
     */
    void delTaskById(Long jobId);

    /**
     * 更改任务状态
     */
    void changeStatus(Long jobId, String cmd);

    /**
     * 更改任务 cron表达式
     *
     * @param jobId
     * @param
     */
    void updateCron(Long jobId);

    /**
     * 添加任务
     *
     * @param job
     */
    void addJob(ScheduleJobDO job);

    List<ScheduleJobDO> queryAllJobs();
    /**
     * 获取所有计划中的任务列表
     *
     * @return
     */
    List<ScheduleJobDO> getAllJob();

    /**
     * 所有正在运行的job
     *
     * @return
     */
    List<ScheduleJobDO> getRunningJob();


    /**
     * 暂停一个job
     *
     * @param jobGroup
     * @param jobName
     */
    void pauseJob(String jobGroup, String jobName);

    /**
     * 恢复一个job
     *
     * @param jobGroup
     * @param jobName
     */
    void resumeJob(String jobGroup, String jobName);

    /**
     * 删除一个job
     *
     * @param jobGroup
     * @param jobName
     */
    void deleteJob(String jobGroup, String jobName);


    /**
     * 立即执行job
     *
     * @param jobGroup
     * @param jobName
     */
    void runAJobNow(String jobGroup, String jobName);

    /**
     * 更新job时间表达式
     *
     * @param jobGroup
     * @param jobName
     * @param cronExpression
     */
    void updateJobCron(String jobGroup, String jobName, String cronExpression)
    ;

    /**
     * 检查表达式
     *
     * @return
     */

    Boolean verifyCronExpression(String cronExpression);
}
