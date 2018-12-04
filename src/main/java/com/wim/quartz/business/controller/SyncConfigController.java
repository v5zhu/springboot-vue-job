package com.wim.quartz.business.controller;

import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.controller.base.BaseController;
import com.wim.quartz.business.entity.SyncConfig;
import com.wim.quartz.business.service.SyncConfigService;
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
public class SyncConfigController extends BaseController {

    @Autowired
    private SyncConfigService syncConfigService;

    @ApiOperation(value = "局数据同步配置列表", notes = "局数据同步配置列表")
    @GetMapping("sync_config_list.ajax")
    @ResponseBody
    public ResponseEntity getListForPage(@RequestParam(value = "pageNum") int pageNum,
                                         @RequestParam(value = "pageSize") int pageSize) {
        PageInfo<SyncConfig> pageInfo = syncConfigService.getListForPage(pageNum, pageSize);
        return new ResponseEntity(pageInfo, HttpStatus.OK);
    }


    @ApiOperation(value = "添加新的局数据同步配置记录", notes = "添加新的局数据同步配置记录")
    @PostMapping("sync_config_add.ajax")
    @ResponseBody
    public ResponseEntity addRecord(@RequestBody SyncConfig record) {
        syncConfigService.addRecord(record);
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
