package com.wim.quartz.business.controller;

import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.controller.base.BaseController;
import com.wim.quartz.business.entity.NetUnit;
import com.wim.quartz.business.service.NetUnitService;
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
public class NetUnitController extends BaseController {

    @Autowired
    private NetUnitService netUnitService;

    @ApiOperation(value = "网元列表", notes = "网元列表")
    @GetMapping("net_unit_list.ajax")
    @ResponseBody
    public ResponseEntity getListForPage(@RequestParam(value = "pageNum") int pageNum,
                                         @RequestParam(value = "pageSize") int pageSize) {
        PageInfo<NetUnit> pageInfo = netUnitService.getListForPage(pageNum, pageSize);
        return new ResponseEntity(pageInfo, HttpStatus.OK);
    }


    @ApiOperation(value = "新增网元记录", notes = "新增网元记录")
    @PostMapping("net_unit_add.ajax")
    @ResponseBody
    public ResponseEntity addRecord(@RequestBody NetUnit record) {
        netUnitService.addRecord(record);
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
