package com.wim.quartz.business.controller;

import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.controller.base.BaseController;
import com.wim.quartz.business.entity.ErrorCodeDict;
import com.wim.quartz.business.service.ErrorCodeDictService;
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
public class ErrorCodeDictController extends BaseController {

    @Autowired
    private ErrorCodeDictService errorCodeDictService;

    @ApiOperation(value = "错误码字典列表", notes = "错误码字典列表")
    @GetMapping("error_code_dict_list.ajax")
    @ResponseBody
    public ResponseEntity getListForPage(@RequestParam(value = "pageNum") int pageNum,
                                         @RequestParam(value = "pageSize") int pageSize) {
        PageInfo<ErrorCodeDict> pageInfo = errorCodeDictService.getErrorCodeDictListForPage(pageNum, pageSize);
        return new ResponseEntity(pageInfo, HttpStatus.OK);
    }


    @ApiOperation(value = "添加新的错误码字典记录", notes = "添加新的错误码字典记录")
    @PostMapping("error_code_dict_add.ajax")
    @ResponseBody
    public ResponseEntity addRecord(@RequestBody ErrorCodeDict bizType) {
        errorCodeDictService.addErrorCodeDict(bizType);
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
