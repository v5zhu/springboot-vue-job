package com.wim.quartz.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.dao.TableNameMapper;
import com.wim.quartz.business.entity.TableName;
import com.wim.quartz.business.service.TableNameService;
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
public class TableNameServiceImpl implements TableNameService {


    @Autowired
    private TableNameMapper tableNameMapper;

    @Override
    public PageInfo<TableName> getListForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<TableName> list = tableNameMapper.selectAllForPage();
        PageInfo<TableName> page = new PageInfo<TableName>(list);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRecord(TableName codeDict) {
        tableNameMapper.insert(codeDict);
    }
}
