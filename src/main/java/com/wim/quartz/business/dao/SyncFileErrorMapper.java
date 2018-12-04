package com.wim.quartz.business.dao;

import com.wim.quartz.business.entity.SyncField;
import com.wim.quartz.business.entity.SyncFileError;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SyncFileErrorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SyncFileError record);

    int insertSelective(SyncFileError record);

    SyncFileError selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SyncFileError record);

    int updateByPrimaryKey(SyncFileError record);

    List<SyncFileError> findListBySyncId(Long syncId);

    List<SyncFileError> selectAllForPage();
}