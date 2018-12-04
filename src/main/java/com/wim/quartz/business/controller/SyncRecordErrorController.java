package com.wim.quartz.business.controller;

import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.controller.base.BaseController;
import com.wim.quartz.business.entity.SyncRecordError;
import com.wim.quartz.business.service.SyncRecordErrorService;
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
public class SyncRecordErrorController extends BaseController {

    @Autowired
    private SyncRecordErrorService syncRecordErrorService;

    @ApiOperation(value = "局数据同步记录级错误码配置列表", notes = "局数据同步记录级错误码配置列表")
    @GetMapping("sync_record_error_list.ajax")
    @ResponseBody
    public ResponseEntity getListForPage(@RequestParam(value = "pageNum") int pageNum,
                                         @RequestParam(value = "pageSize") int pageSize) {
        PageInfo<SyncRecordError> pageInfo = syncRecordErrorService.getListForPage(pageNum, pageSize);
        return new ResponseEntity(pageInfo, HttpStatus.OK);
    }


    @ApiOperation(value = "添加新的局数据同步记录级错误码配置记录", notes = "添加新的局数据同步记录级错误码配置记录")
    @PostMapping("sync_record_error_add.ajax")
    @ResponseBody
    public ResponseEntity addRecord(@RequestBody SyncRecordError record) {
        syncRecordErrorService.addRecord(record);
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
