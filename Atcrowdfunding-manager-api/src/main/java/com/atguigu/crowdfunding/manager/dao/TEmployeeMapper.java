package com.atguigu.crowdfunding.manager.dao;


import com.atguigu.atcrowdfunding.bean.TEmployee;

import java.util.List;

public interface TEmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TEmployee record);

    TEmployee selectByPrimaryKey(Integer id);

    List<TEmployee> selectAll();

    int updateByPrimaryKey(TEmployee record);
}