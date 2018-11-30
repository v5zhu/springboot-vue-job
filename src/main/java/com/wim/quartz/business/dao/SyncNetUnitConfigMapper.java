package com.wim.quartz.business.dao;

import com.wim.quartz.business.entity.SyncNetUnitConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SyncNetUnitConfigMapper {
    int insert(SyncNetUnitConfig record);

    int insertSelective(SyncNetUnitConfig record);

    List<SyncNetUnitConfig> findListByBizType(@Param("bizType") String bizType);
}