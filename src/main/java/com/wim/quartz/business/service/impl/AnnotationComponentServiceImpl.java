package com.wim.quartz.business.service.impl;

import com.wim.quartz.business.service.AnnotationComponentService;
import com.wim.quartz.component.annotation.AnnotationJob;
import com.wim.quartz.util.ClassUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;


@Service
public class AnnotationComponentServiceImpl implements AnnotationComponentService {


    @Override
    public List<Map<String, String>> getClassList() {
        List<Map<String, String>> mapList = new ArrayList();
        try {
            Set<Class<?>> classes = ClassUtils.getClasses("com.wim");
            classes.parallelStream()
                    .filter(e -> e.getAnnotation(AnnotationJob.class) != null)
                    .forEach(c -> {
                        Map map = new HashMap();
                        map.put("label", c.getName());
                        map.put("value", c.getName());
                        mapList.add(map);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapList;
    }

    @Override
    public List<Map<String, String>> methods(String clazz) {
        List<Map<String, String>> mapList = new ArrayList();

        try {
            //@Transactional注解加上后使用了CGLIB增强技术，截取类
            Class c = Class.forName(clazz);
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                Map map = new HashMap(8);
                map.put("label", method.getName());
                map.put("name", method.getName());
                mapList.add(map);
            }
            return mapList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mapList;
    }
}
