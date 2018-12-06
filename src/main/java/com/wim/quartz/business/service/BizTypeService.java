package com.wim.quartz.business.service;

import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.entity.BizType;

import java.util.List;
import java.util.Map;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business.service
 * @description
 * @time 2018/11/17 22:08
 */
public interface BizTypeService {

    void addNewBizType(BizType bizType);

    PageInfo<BizType> bizTypeList(Integer pageNum, Integer pageSize);

    List<Map> dropdown();
}
