package com.wim.quartz.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.dao.SyncRecordErrorMapper;
import com.wim.quartz.business.entity.SyncRecordError;
import com.wim.quartz.business.service.SyncRecordErrorService;
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
public class SyncRecordErrorServiceImpl implements SyncRecordErrorService {


    @Autowired
    private SyncRecordErrorMapper syncRecordErrorMapper;

    @Override
    public PageInfo<SyncRecordError> getListForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<SyncRecordError> list = syncRecordErrorMapper.selectAllForPage();
        PageInfo<SyncRecordError> page = new PageInfo<SyncRecordError>(list);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRecord(SyncRecordError codeDict) {
        syncRecordErrorMapper.insert(codeDict);
    }
}
