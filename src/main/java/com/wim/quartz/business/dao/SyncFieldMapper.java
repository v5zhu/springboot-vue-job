package com.wim.quartz.business.dao;

import com.wim.quartz.business.entity.SyncField;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SyncFieldMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SyncField record);

    int insertSelective(SyncField record);

    SyncField selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SyncField record);

    int updateByPrimaryKey(SyncField record);

    List<SyncField> findListBySyncId(Long syncId);
}