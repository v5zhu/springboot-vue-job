package com.wim.quartz.business.controller;

import com.wim.quartz.business.controller.base.BaseController;
import com.wim.quartz.business.service.AnnotationComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhuxiaolong
 */
@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class AnnotationComponentController extends BaseController {

    @Autowired
    private AnnotationComponentService annotationComponentService;

    @GetMapping("component/class_list.ajax")
    @ResponseBody
    public ResponseEntity classList() {
        List<Map<String, String>> fullname = annotationComponentService.getClassList();
        return new ResponseEntity(fullname, HttpStatus.OK);
    }

    @GetMapping("component/method_list.ajax")
    @ResponseBody
    public ResponseEntity methodList(@RequestParam("clazz") String clazz) {
        List<Map<String, String>> names = annotationComponentService.methods(clazz);
        return new ResponseEntity(names, HttpStatus.OK);
    }
}
