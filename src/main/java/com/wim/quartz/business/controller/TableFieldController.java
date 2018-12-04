package com.wim.quartz.business.controller;

import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.controller.base.BaseController;
import com.wim.quartz.business.entity.TableField;
import com.wim.quartz.business.service.TableFieldService;
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
public class TableFieldController extends BaseController {

    @Autowired
    private TableFieldService tableFieldService;

    @ApiOperation(value = "局数据字段列表", notes = "获取数据库指定局数据字段列表")
    @GetMapping("biz_type_field_list.ajax")
    @ResponseBody
    public ResponseEntity bizTypeFieldList(@RequestParam(value = "bizId") Long bizId,
                                           @RequestParam(value = "pageNum") int pageNum,
                                           @RequestParam(value = "pageSize") int pageSize) {
        PageInfo<TableField> pageInfo = tableFieldService.bizTypeFieldList(bizId, pageNum, pageSize);
        return new ResponseEntity(pageInfo, HttpStatus.OK);
    }


    @ApiOperation(value = "添加局数据字段", notes = "添加局数据字段")
    @PostMapping("biz_type_field_add.ajax")
    @ResponseBody
    public ResponseEntity addBizTypeField(@RequestBody TableField bizType) {
        tableFieldService.addBizTypeField(bizType);
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
