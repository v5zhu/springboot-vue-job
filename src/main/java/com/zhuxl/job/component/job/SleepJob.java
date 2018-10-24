package com.zhuxl.job.component.job;

import com.zhuxl.job.component.annotation.AnnotationJob;
import com.zhuxl.job.framework.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhuxl
 * @project springboot-vue-quartz
 * @package com.zhuxl.job.component.job
 * @description
 * @date 2018/9/21 22:56
 */
@AnnotationJob
public class SleepJob {
    @Autowired
    private RedisClient redisClient;

    public void sleep() {
        try {
            long times = 5000;
            redisClient.isExistKey("13880298929");
            Thread.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
