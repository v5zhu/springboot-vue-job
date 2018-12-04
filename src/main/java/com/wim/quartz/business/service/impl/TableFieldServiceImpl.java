package com.wim.quartz.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.dao.TableFieldMapper;
import com.wim.quartz.business.entity.TableField;
import com.wim.quartz.business.service.TableFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business.service.impl
 * @description
 * @time 2018/11/17 22:17
 */
@Service
public class TableFieldServiceImpl implements TableFieldService {

    @Autowired
    private TableFieldMapper tableFieldMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBizTypeField(TableField bizType) {
        // 先新增局数据类型基本信息
        tableFieldMapper.insert(bizType);
    }

    @Override
    public PageInfo<TableField> bizTypeFieldList(Long bizId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<TableField> bizTypes = tableFieldMapper.selectAll(bizId);
        PageInfo<TableField> pageInfo = new PageInfo(bizTypes);
        return pageInfo;
    }
}
