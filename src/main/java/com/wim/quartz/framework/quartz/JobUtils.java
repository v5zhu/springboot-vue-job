package com.wim.quartz.framework.quartz;

import com.alibaba.fastjson.JSONObject;
import com.wim.quartz.business.entity.ScheduleJobDO;
import com.wim.quartz.util.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;


/**
 * @author zhuxl
 */
public class JobUtils {
    public final static Logger log = LoggerFactory.getLogger(JobUtils.class);

    /**
     * 通过反射调用scheduleJob中定义的方法
     *
     * @param scheduleJob
     */
    public static void invokMethod(ScheduleJobDO scheduleJob) {
        Object object = null;
        Class clazz;
        if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
            object = SpringUtils.getBean(scheduleJob.getSpringId());
        } else if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
            try {
                clazz = Class.forName(scheduleJob.getBeanClass());
                object = clazz.newInstance();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        if (object == null) {
            log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
            return;
        }
        clazz = object.getClass();
        Method method = null;
        try {
            if (scheduleJob.getParameters() == null) {
                // 无参调用
                method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
            } else {
                // 带有Map参数
                method = clazz.getDeclaredMethod(scheduleJob.getMethodName(), Map.class);
            }
        } catch (NoSuchMethodException e) {
            log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (method != null) {
            try {
                if (scheduleJob.getParameters() == null) {
                    // 无参调用
                    method.invoke(object);
                } else {
                    // 带有Map参数
                    JSONObject parameters = JSONObject.parseObject(scheduleJob.getParameters());
                    method.invoke(object, (Map) parameters);
                }
            } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}
