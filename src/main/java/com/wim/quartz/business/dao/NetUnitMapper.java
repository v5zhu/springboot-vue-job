package com.wim.quartz.business.dao;

import com.wim.quartz.business.entity.NetUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NetUnitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NetUnit record);

    int insertSelective(NetUnit record);

    NetUnit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NetUnit record);

    int updateByPrimaryKey(NetUnit record);

    NetUnit findByNetUnitCode(String netUnitCode);

    NetUnit findByNetUnitId(Long netUnitId);

    List<NetUnit> selectAllForPage();
}