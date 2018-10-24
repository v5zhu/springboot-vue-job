package com.zhuxl.job.component.job;

import com.zhuxl.job.component.annotation.AnnotationJob;

/**
 * @author zhuxl
 * @project springboot-vue-quartz
 * @package com.zhuxl.job.component.job
 * @description
 * @date 2018/9/21 22:56
 */
@AnnotationJob
public class SleepJob {

    public void sleep() {
        try {
            long times=5000;
            Thread.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
