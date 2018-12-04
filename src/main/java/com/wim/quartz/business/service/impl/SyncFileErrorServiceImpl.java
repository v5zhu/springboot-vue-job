package com.wim.quartz.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.dao.SyncFileErrorMapper;
import com.wim.quartz.business.entity.SyncFileError;
import com.wim.quartz.business.service.SyncFileErrorService;
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
public class SyncFileErrorServiceImpl implements SyncFileErrorService {


    @Autowired
    private SyncFileErrorMapper syncFileErrorMapper;

    @Override
    public PageInfo<SyncFileError> getListForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<SyncFileError> list = syncFileErrorMapper.selectAllForPage();
        PageInfo<SyncFileError> page = new PageInfo<SyncFileError>(list);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRecord(SyncFileError codeDict) {
        syncFileErrorMapper.insert(codeDict);
    }
}
