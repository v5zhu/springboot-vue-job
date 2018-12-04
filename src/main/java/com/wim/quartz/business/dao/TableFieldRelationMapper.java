package com.wim.quartz.business.dao;

import com.wim.quartz.business.entity.TableFieldRelation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableFieldRelationMapper {
    int deleteByPrimaryKey(TableFieldRelation key);

    int insert(TableFieldRelation record);

    int insertSelective(TableFieldRelation record);

    TableFieldRelation selectByPrimaryKey(TableFieldRelation key);

    int updateByPrimaryKeySelective(TableFieldRelation record);

    int updateByPrimaryKey(TableFieldRelation record);

    List<TableFieldRelation> selectAllForPage();
}