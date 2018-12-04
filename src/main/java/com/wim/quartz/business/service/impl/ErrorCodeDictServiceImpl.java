package com.wim.quartz.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.dao.ErrorCodeDictMapper;
import com.wim.quartz.business.entity.ErrorCodeDict;
import com.wim.quartz.business.service.ErrorCodeDictService;
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
public class ErrorCodeDictServiceImpl implements ErrorCodeDictService {


    @Autowired
    private ErrorCodeDictMapper errorCodeDictMapper;

    @Override
    public PageInfo<ErrorCodeDict> getErrorCodeDictListForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<ErrorCodeDict> list = errorCodeDictMapper.selectAllForPage();
        PageInfo<ErrorCodeDict> page = new PageInfo<ErrorCodeDict>(list);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addErrorCodeDict(ErrorCodeDict codeDict) {
        errorCodeDictMapper.insert(codeDict);
    }
}
