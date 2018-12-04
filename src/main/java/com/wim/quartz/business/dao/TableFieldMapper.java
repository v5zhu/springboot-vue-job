package com.wim.quartz.business.dao;

import com.wim.quartz.business.entity.TableField;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TableFieldMapper {
    int deleteByPrimaryKey(TableField key);

    int insert(TableField record);

    int insertSelective(TableField record);

    TableField selectByPrimaryKey(TableField key);

    int updateByPrimaryKeySelective(TableField record);

    int updateByPrimaryKey(TableField record);
}