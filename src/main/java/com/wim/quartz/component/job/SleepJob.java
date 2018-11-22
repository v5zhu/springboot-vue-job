package com.wim.quartz.component.job;

import com.wim.quartz.component.annotation.AnnotationJob;

/**
 * @author zhuxl
 * @project springboot-vue-quartz
 * @package com.wim.quartz.component.job
 * @description
 * @date 2018/9/21 22:56
 */
@AnnotationJob
public class SleepJob {

    public void sleep() {
        System.out.println("任务执行.................................................");
    }
}
