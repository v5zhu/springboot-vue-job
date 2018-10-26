package com.wim.quartz.component.job;

import com.wim.quartz.component.annotation.AnnotationJob;
import com.wim.component.snowflake.SnowflakeIdWorker;
import com.wim.quartz.framework.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhuxl
 * @project springboot-vue-quartz
 * @package com.wim.quartz.component.job
 * @description
 * @date 2018/9/21 22:56
 */
@AnnotationJob
public class SleepJob {
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    public void sleep() {
        try {
            long times = 5000;
//            redisClient.isExistKey("13880298929");
//            Long id = redisClient.nextDistributeUniqueId(BusinessEnum.BUSINESS_QUARTZ_LOG.getCode());
            for (int i = 0; i < 10; i++) {
                System.out.println(snowflakeIdWorker.nextId());
            }
            Thread.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
