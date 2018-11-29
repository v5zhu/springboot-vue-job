package com.wim.quartz.business.dao;


import com.wim.quartz.business.entity.NetworkElement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NetworkElementMapper {
    int deleteByPrimaryKey(String systemCode);

    int insert(NetworkElement record);

    int insertSelective(NetworkElement record);

    NetworkElement selectByPrimaryKey(String systemCode);

    int updateByPrimaryKeySelective(NetworkElement record);

    int updateByPrimaryKey(NetworkElement record);

    List<NetworkElement> findByCodes(String codes);
}