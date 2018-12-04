package com.wim.quartz.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.dao.FieldConvertDictMapper;
import com.wim.quartz.business.entity.FieldConvertDict;
import com.wim.quartz.business.service.FieldConvertDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhuxiaolong
 * @project springboot-vue-quartz
 * @package com.wim.quartz.business.service.impl
 * @description
 * @time 2018/12/3 11:21
 */
@Service
public class FieldConvertDictServiceImpl implements FieldConvertDictService {


    @Autowired
    private FieldConvertDictMapper fieldConvertDictMapper;

    @Override
    public PageInfo<FieldConvertDict> getListForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<FieldConvertDict> list = fieldConvertDictMapper.selectAllForPage();
        PageInfo<FieldConvertDict> page = new PageInfo<FieldConvertDict>(list);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRecord(FieldConvertDict codeDict) {
        fieldConvertDictMapper.insert(codeDict);
    }
}
