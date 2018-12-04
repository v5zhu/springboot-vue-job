package com.wim.quartz.business.dao;

import com.wim.quartz.business.entity.SyncConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SyncConfigMapper {
    int insert(SyncConfig record);

    int insertSelective(SyncConfig record);

    List<SyncConfig> findListByBizId(@Param("bizId") Long bizId);

    List<SyncConfig> selectAllForPage();
}