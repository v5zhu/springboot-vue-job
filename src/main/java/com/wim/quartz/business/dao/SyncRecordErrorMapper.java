package com.wim.quartz.business.dao;

import com.wim.quartz.business.entity.SyncField;
import com.wim.quartz.business.entity.SyncRecordError;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SyncRecordErrorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SyncRecordError record);

    int insertSelective(SyncRecordError record);

    SyncRecordError selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SyncRecordError record);

    int updateByPrimaryKey(SyncRecordError record);

    List<SyncRecordError> findListBySyncId(Long syncId);

}