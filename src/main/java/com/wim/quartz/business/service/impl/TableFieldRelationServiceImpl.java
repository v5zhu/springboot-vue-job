package com.wim.quartz.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.dao.TableFieldRelationMapper;
import com.wim.quartz.business.entity.TableFieldRelation;
import com.wim.quartz.business.service.TableFieldRelationService;
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
public class TableFieldRelationServiceImpl implements TableFieldRelationService {


    @Autowired
    private TableFieldRelationMapper tableFieldRelationMapper;

    @Override
    public PageInfo<TableFieldRelation> getListForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<TableFieldRelation> list = tableFieldRelationMapper.selectAllForPage();
        PageInfo<TableFieldRelation> page = new PageInfo<TableFieldRelation>(list);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRecord(TableFieldRelation codeDict) {
        tableFieldRelationMapper.insert(codeDict);
    }
}
