package com.wim.quartz.business.dao;


import com.wim.quartz.business.entity.FieldConvertDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FieldConvertDictMapper {
    int deleteByPrimaryKey(FieldConvertDict key);

    int insert(FieldConvertDict record);

    int insertSelective(FieldConvertDict record);

    FieldConvertDict selectByPrimaryKey(FieldConvertDict key);

    int updateByPrimaryKeySelective(FieldConvertDict record);

    int updateByPrimaryKey(FieldConvertDict record);

    List<FieldConvertDict> findByDictField(@Param("params") Map params);

    List<FieldConvertDict> selectAllForPage();
}