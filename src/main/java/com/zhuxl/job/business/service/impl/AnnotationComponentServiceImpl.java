package com.zhuxl.job.business.service.impl;

import com.zhuxl.job.component.annotation.AnnotationJob;
import com.zhuxl.job.business.service.AnnotationComponentService;
import com.zhuxl.job.util.SpringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class AnnotationComponentServiceImpl implements AnnotationComponentService {


    @Override
    public List<String> listBeanNames() {
        String[] names = SpringUtils.listBeanIds(AnnotationJob.class);
        return Arrays.asList(names);
    }

    @Override
    public String getClassFullName(String beanId) {
        Object object = SpringUtils.getBean(beanId);
        if (object != null) {
            String clazz = object.getClass().getName();
            return clazz.split("\\$\\$")[0];
        }
        return null;
    }

    @Override
    public List<String> methods(String beanId) {
        Object object = SpringUtils.getBean(beanId);
        if (object == null) {
            return null;
        }
        String clazz = object.getClass().getName();
        try {
            //@Transactional注解加上后使用了CGLIB增强技术，截取类
            Class c = Class.forName(clazz.split("\\$\\$")[0]);
            Method[] methods = c.getDeclaredMethods();
            List<String> methodNames = new ArrayList<String>();
            for (Method method : methods) {
                methodNames.add(method.getName());
            }
            return methodNames;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }
}
