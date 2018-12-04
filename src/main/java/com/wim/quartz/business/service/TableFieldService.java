package com.wim.quartz.business.service;

import com.github.pagehelper.PageInfo;
import com.wim.quartz.business.entity.TableField;

/**
 * @author zhuxiaolong
 * @project Project Collections
 * @package com.wim.quartz.business.service
 * @description
 * @time 2018/11/17 22:08
 */
public interface TableFieldService {

    void addBizTypeField(TableField field);

    PageInfo<TableField> bizTypeFieldList(Long bizId, Integer pageNum, Integer pageSize);
}
