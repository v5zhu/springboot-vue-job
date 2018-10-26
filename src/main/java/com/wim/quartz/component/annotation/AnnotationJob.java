package com.wim.quartz.component.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author zhuxl
 * @project springboot-vue-job
 * @package com.wim.quartz.component.annotation
 * @description
 * @date 2018/9/21 22:49
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface AnnotationJob {
}
