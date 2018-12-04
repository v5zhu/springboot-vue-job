package com.wim.quartz.business.dao;

import com.wim.quartz.business.entity.TableName;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableNameMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TableName record);

    int insertSelective(TableName record);

    TableName selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TableName record);

    int updateByPrimaryKey(TableName record);

    List<TableName> selectAllForPage();
}