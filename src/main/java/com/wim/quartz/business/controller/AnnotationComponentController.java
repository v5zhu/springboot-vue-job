package com.wim.quartz.business.controller;

import com.wim.quartz.business.controller.base.BaseController;
import com.wim.quartz.business.service.AnnotationComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zhuxiaolong
 */
@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class AnnotationComponentController extends BaseController {

    @Autowired
    private AnnotationComponentService annotationComponentService;

    @GetMapping("component/list.ajax")
    @ResponseBody
    public ResponseEntity listMfxcomponent(HttpServletRequest request, HttpServletResponse response) {
        List<String> names = annotationComponentService.listBeanNames();
        return new ResponseEntity(names, HttpStatus.OK);
    }

    @GetMapping("component/class_full_name.ajax")
    @ResponseBody
    public ResponseEntity getClassFullName(@RequestParam("beanId") String beanId) {
        String fullname = annotationComponentService.getClassFullName(beanId);
        return new ResponseEntity(fullname, HttpStatus.OK);
    }

    @GetMapping("component/method_list.ajax")
    @ResponseBody
    public ResponseEntity listClassMethods(@RequestParam("beanId") String beanId) {
        List<String> names = annotationComponentService.methods(beanId);
        return new ResponseEntity(names, HttpStatus.OK);
    }
}
