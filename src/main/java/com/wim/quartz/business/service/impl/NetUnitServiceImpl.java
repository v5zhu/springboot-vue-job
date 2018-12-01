package com.wim.quartz.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.dao.NetUnitMapper;
import com.wim.quartz.business.entity.NetUnit;
import com.wim.quartz.business.service.NetUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuxiaolong
 * @project springboot-vue-quartz
 * @package com.wim.quartz.business.service.impl
 * @description
 * @time 2018/12/1 9:57
 */
@Service
public class NetUnitServiceImpl implements NetUnitService {

    @Autowired
    private NetUnitMapper netUnitMapper;

    @Override
    public PageInfo<NetUnit> getNetUnitListForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<NetUnit> netUnits = netUnitMapper.selectAllForPage();
        PageInfo<NetUnit> pageInfo = new PageInfo<NetUnit>(netUnits);
        return pageInfo;
    }
}
