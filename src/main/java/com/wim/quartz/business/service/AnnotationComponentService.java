package com.wim.quartz.business.service;


import java.util.List;
import java.util.Map;


public interface AnnotationComponentService {

    List<Map<String, String>> getClassList();

    List<Map<String, String>> methods(String beanId);

}
