package com.wim.quartz.business.service;

import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.entity.NetUnit;

/**
 * @author zhuxiaolong
 * @project springboot-vue-quartz
 * @package com.wim.quartz.business.service
 * @description
 * @time 2018/12/1 9:57
 */
public interface NetUnitService {

    PageInfo<NetUnit> getListForPage(Integer pageNum, Integer pageSize);

    void addRecord(NetUnit record);
}
