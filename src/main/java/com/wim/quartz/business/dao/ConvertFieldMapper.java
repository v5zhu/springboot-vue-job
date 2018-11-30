package com.wim.quartz.business.dao;


import com.wim.quartz.business.entity.ConvertField;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConvertFieldMapper {
    int deleteByPrimaryKey(ConvertField key);

    int insert(ConvertField record);

    int insertSelective(ConvertField record);

    ConvertField selectByPrimaryKey(ConvertField key);

    int updateByPrimaryKeySelective(ConvertField record);

    int updateByPrimaryKey(ConvertField record);

    List<ConvertField> findByBizTypeAndFieldName(@Param("params") Map params);
}