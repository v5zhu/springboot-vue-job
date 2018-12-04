package com.wim.quartz.business.controller;

import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.controller.base.BaseController;
import com.wim.quartz.business.entity.TableFieldRelation;
import com.wim.quartz.business.service.TableFieldRelationService;
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
public class TableFieldRelationController extends BaseController {

    @Autowired
    private TableFieldRelationService tableFieldRelationService;

    @ApiOperation(value = "局数据数据库表与字段的关系列表", notes = "获取局数据数据库表与字段的关系列表")
    @GetMapping("table_field_relation_list.ajax")
    @ResponseBody
    public ResponseEntity getListForPage(@RequestParam(value = "bizId") Long bizId,
                                         @RequestParam(value = "pageNum") int pageNum,
                                         @RequestParam(value = "pageSize") int pageSize) {
        PageInfo<TableFieldRelation> pageInfo = tableFieldRelationService.getListForPage(pageNum, pageSize);
        return new ResponseEntity(pageInfo, HttpStatus.OK);
    }


    @ApiOperation(value = "添加局数据数据库表与字段的关系", notes = "添加局数据数据库表与字段的关系")
    @PostMapping("table_field_relation_add.ajax")
    @ResponseBody
    public ResponseEntity addRecord(@RequestBody TableFieldRelation bizType) {
        tableFieldRelationService.addRecord(bizType);
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
