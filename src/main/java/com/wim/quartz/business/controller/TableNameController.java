package com.wim.quartz.business.controller;

import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.controller.base.BaseController;
import com.wim.quartz.business.entity.TableName;
import com.wim.quartz.business.service.TableNameService;
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
public class TableNameController extends BaseController {

    @Autowired
    private TableNameService tableNameService;

    @ApiOperation(value = "局数据数据库表列表", notes = "获取局数据数据库表列表")
    @GetMapping("table_name_list.ajax")
    @ResponseBody
    public ResponseEntity getListForPage(@RequestParam(value = "bizId") Long bizId,
                                         @RequestParam(value = "pageNum") int pageNum,
                                         @RequestParam(value = "pageSize") int pageSize) {
        PageInfo<TableName> pageInfo = tableNameService.getListForPage(pageNum, pageSize);
        return new ResponseEntity(pageInfo, HttpStatus.OK);
    }


    @ApiOperation(value = "添加局数据数据库表", notes = "添加局数据数据库表")
    @PostMapping("table_name_add.ajax")
    @ResponseBody
    public ResponseEntity addRecord(@RequestBody TableName bizType) {
        tableNameService.addRecord(bizType);
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
