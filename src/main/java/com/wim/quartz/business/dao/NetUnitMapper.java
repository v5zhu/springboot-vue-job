package com.wim.quartz.business.dao;


import com.wim.quartz.business.entity.NetUnit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NetUnitMapper {
    int deleteByPrimaryKey(String systemCode);

    int insert(NetUnit record);

    int insertSelective(NetUnit record);

    NetUnit selectByPrimaryKey(String systemCode);

    int updateByPrimaryKeySelective(NetUnit record);

    int updateByPrimaryKey(NetUnit record);

    NetUnit findByNetUnitCode(String netUnitCode);
}