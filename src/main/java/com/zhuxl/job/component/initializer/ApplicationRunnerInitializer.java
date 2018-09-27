package com.zhuxl.job.component.initializer;

import com.zhuxl.job.business.entity.ScheduleJobDO;
import com.zhuxl.job.business.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhuxl
 */
@Component
public class ApplicationRunnerInitializer implements ApplicationRunner {
    private final JobService jobService;

    @Autowired
    public ApplicationRunnerInitializer(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 这里获取任务信息数据
        List<ScheduleJobDO> jobList = jobService.queryAllJobs();

        for (ScheduleJobDO job : jobList) {
            jobService.addJob(job);
        }
    }
}
