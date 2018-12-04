package com.wim.quartz.business.dao;

import com.wim.quartz.business.entity.TableField;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableFieldMapper {
    int deleteByPrimaryKey(Long key);

    int insert(TableField record);

    int insertSelective(TableField record);

    TableField selectByPrimaryKey(Long key);

    int updateByPrimaryKeySelective(TableField record);

    int updateByPrimaryKey(TableField record);

    List<TableField> findFieldListByBizId(Long bizId);

    List<TableField> selectAll(Long bizId);

}