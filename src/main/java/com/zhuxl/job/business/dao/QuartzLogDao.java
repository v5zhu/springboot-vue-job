package com.zhuxl.job.business.dao;


import com.zhuxl.job.business.entity.QuartzLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhuxl
 */
@Mapper
public interface QuartzLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(QuartzLog record);

    int insertSelective(QuartzLog record);

    QuartzLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuartzLog record);

}