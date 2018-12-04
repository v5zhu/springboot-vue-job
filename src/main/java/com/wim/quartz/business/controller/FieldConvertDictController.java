package com.wim.quartz.business.controller;

import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.controller.base.BaseController;
import com.wim.quartz.business.entity.FieldConvertDict;
import com.wim.quartz.business.service.FieldConvertDictService;
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
public class FieldConvertDictController extends BaseController {

    @Autowired
    private FieldConvertDictService fieldConvertDictService;

    @ApiOperation(value = "字段转义字典列表", notes = "字段转义字典列表")
    @GetMapping("field_convert_dict_list.ajax")
    @ResponseBody
    public ResponseEntity getListForPage(@RequestParam(value = "pageNum") int pageNum,
                                         @RequestParam(value = "pageSize") int pageSize) {
        PageInfo<FieldConvertDict> pageInfo = fieldConvertDictService.getListForPage(pageNum, pageSize);
        return new ResponseEntity(pageInfo, HttpStatus.OK);
    }


    @ApiOperation(value = "添加新的字段转义字典记录", notes = "添加新的字段转义字典记录")
    @PostMapping("field_convert_dict_add.ajax")
    @ResponseBody
    public ResponseEntity addRecord(@RequestBody FieldConvertDict record) {
        fieldConvertDictService.addRecord(record);
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
