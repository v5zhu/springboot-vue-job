package com.wim.quartz.business.service;


import java.util.List;


public interface AnnotationComponentService {

    List<String> listBeanNames();

    String getClassFullName(String beanId);

    List<String> methods(String beanId);

}
