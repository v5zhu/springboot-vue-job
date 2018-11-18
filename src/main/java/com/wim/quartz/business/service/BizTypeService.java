package com.wim.quartz.business.service;

import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.entity.BizType;

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
}
