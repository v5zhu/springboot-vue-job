package com.wim.quartz.business.controller;

import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.controller.base.BaseController;
import com.wim.quartz.business.entity.BizType;
import com.wim.quartz.business.service.BizTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author cnlm.me@qq.com
 * @date 2017/7/22
 */
@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class BizTypeController extends BaseController {

    @Autowired
    private BizTypeService bizTypeService;

    @ApiOperation(value = "局数据列表", notes = "获取数据库所有局数据列表")
    @GetMapping("biz_type_list.ajax")
    @ResponseBody
    public ResponseEntity bizTypeList(@RequestParam(value = "bizType") String bizType,
                                      @RequestParam(value = "pageNum") int pageNum,
                                      @RequestParam(value = "pageSize") int pageSize) {
        PageInfo<BizType> pageInfo = bizTypeService.bizTypeList(pageNum, pageSize);
        return new ResponseEntity(pageInfo, HttpStatus.OK);
    }


    @ApiOperation(value = "添加局数据", notes = "添加一类新的局数据")
    @PostMapping("biz_type_add.ajax")
    @ResponseBody
    public ResponseEntity addBizType(@RequestBody BizType bizType) {
        bizTypeService.addNewBizType(bizType);
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
