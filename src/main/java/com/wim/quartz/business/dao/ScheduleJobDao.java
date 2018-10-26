package com.wim.quartz.business.dao;


import com.wim.quartz.business.entity.ScheduleJobDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScheduleJobDao {
    int deleteByPrimaryKey(Long jobId);

    int insert(ScheduleJobDO record);

    int insertSelective(ScheduleJobDO record);

    ScheduleJobDO selectByPrimaryKey(Long jobId);

    int updateByPrimaryKeySelective(ScheduleJobDO record);

    int updateByPrimaryKey(ScheduleJobDO record);

    List<ScheduleJobDO> getAll();

    List<ScheduleJobDO> getTaskByContent(String content);

    Integer selectByNameGroup(@Param("jobName") String jobName, @Param("jobGroup") String jobGroup);

    Integer selectByNameGroupExceptThis(@Param("id") Long id, @Param("jobName") String jobName, @Param("jobGroup") String jobGroup);
}