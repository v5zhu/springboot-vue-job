package com.wim.quartz.business.dao;

import com.wim.quartz.business.entity.BizTypeField;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BizTypeFieldMapper {
    int deleteByPrimaryKey(BizTypeField key);

    int insert(BizTypeField record);

    int insertSelective(BizTypeField record);

    BizTypeField selectByPrimaryKey(BizTypeField key);

    int updateByPrimaryKeySelective(BizTypeField record);

    int updateByPrimaryKey(BizTypeField record);

    List<BizTypeField> findByBizType(String bizType);
}