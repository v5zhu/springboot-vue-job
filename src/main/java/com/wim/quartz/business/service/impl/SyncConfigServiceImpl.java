package com.wim.quartz.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.dao.SyncConfigMapper;
import com.wim.quartz.business.entity.SyncConfig;
import com.wim.quartz.business.service.SyncConfigService;
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
public class SyncConfigServiceImpl implements SyncConfigService {


    @Autowired
    private SyncConfigMapper syncConfigMapper;

    @Override
    public PageInfo<SyncConfig> getListForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<SyncConfig> list = syncConfigMapper.selectAllForPage();
        PageInfo<SyncConfig> page = new PageInfo<SyncConfig>(list);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRecord(SyncConfig codeDict) {
        syncConfigMapper.insert(codeDict);
    }
}
