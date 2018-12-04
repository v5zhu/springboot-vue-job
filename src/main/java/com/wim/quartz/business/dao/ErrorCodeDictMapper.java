package com.wim.quartz.business.dao;

import com.wim.quartz.business.entity.ErrorCodeDict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ErrorCodeDictMapper {
    int deleteByPrimaryKey(String code);

    int insert(ErrorCodeDict record);

    int insertSelective(ErrorCodeDict record);

    ErrorCodeDict selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ErrorCodeDict record);

    int updateByPrimaryKey(ErrorCodeDict record);

    List<ErrorCodeDict> selectAllForPage();

}