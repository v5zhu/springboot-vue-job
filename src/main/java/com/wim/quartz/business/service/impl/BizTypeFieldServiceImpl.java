package com.wim.quartz.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.dao.BizTypeFieldMapper;
import com.wim.quartz.business.entity.BizTypeField;
import com.wim.quartz.business.service.BizTypeFieldService;
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
public class BizTypeFieldServiceImpl implements BizTypeFieldService {

    @Autowired
    private BizTypeFieldMapper bizTypeFieldMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBizTypeField(BizTypeField bizType) {
        // 先新增局数据类型基本信息
        bizTypeFieldMapper.insert(bizType);
    }

    @Override
    public PageInfo<BizTypeField> bizTypeFieldList(Long bizId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<BizTypeField> bizTypes = bizTypeFieldMapper.selectAll(bizId);
        PageInfo<BizTypeField> pageInfo = new PageInfo(bizTypes);
        return pageInfo;
    }
}
