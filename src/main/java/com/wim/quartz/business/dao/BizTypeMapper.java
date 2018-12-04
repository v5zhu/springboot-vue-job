package com.wim.quartz.business.dao;


import com.wim.quartz.business.entity.BizType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BizTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BizType record);

    int insertSelective(BizType record);

    BizType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BizType record);

    int updateByPrimaryKey(BizType record);

    List<BizType> selectAll();

}