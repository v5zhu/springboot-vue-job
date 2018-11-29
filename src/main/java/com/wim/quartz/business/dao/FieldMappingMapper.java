package com.wim.quartz.business.dao;


import com.wim.quartz.business.entity.FieldMapping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FieldMappingMapper {
    int deleteByPrimaryKey(FieldMapping key);

    int insert(FieldMapping record);

    int insertSelective(FieldMapping record);

    FieldMapping selectByPrimaryKey(FieldMapping key);

    int updateByPrimaryKeySelective(FieldMapping record);

    int updateByPrimaryKey(FieldMapping record);

    List<FieldMapping> findByBizTypeAndFieldName(@Param("bizType") String bizType, @Param("fieldName") String fieldName);
}