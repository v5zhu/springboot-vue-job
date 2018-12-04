package com.wim.quartz.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.dao.SyncFieldMapper;
import com.wim.quartz.business.entity.SyncField;
import com.wim.quartz.business.service.SyncFieldService;
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
public class SyncFieldServiceImpl implements SyncFieldService {


    @Autowired
    private SyncFieldMapper syncFieldMapper;

    @Override
    public PageInfo<SyncField> getListForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<SyncField> list = syncFieldMapper.selectAllForPage();
        PageInfo<SyncField> page = new PageInfo<SyncField>(list);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRecord(SyncField codeDict) {
        syncFieldMapper.insert(codeDict);
    }
}
