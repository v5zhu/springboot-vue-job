package com.wim.quartz.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.dao.BizTypeMapper;
import com.wim.quartz.business.entity.BizType;
import com.wim.quartz.business.service.BizTypeService;
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
public class BizTypeServiceImpl implements BizTypeService {

    @Autowired
    private BizTypeMapper bizTypeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addNewBizType(BizType bizType) {
        // 先新增局数据类型基本信息
        bizTypeMapper.insert(bizType);
    }

    @Override
    public PageInfo<BizType> bizTypeList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<BizType> bizTypes = bizTypeMapper.selectAll();
        PageInfo<BizType> pageInfo = new PageInfo(bizTypes);
        return pageInfo;
    }
}
