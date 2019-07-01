package com.atguigu.crowdfunding.manager.dao;


import com.atguigu.atcrowdfunding.bean.TCert;

import java.util.List;

public interface TCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCert record);

    TCert selectByPrimaryKey(Integer id);

    List<TCert> selectAll();

    int updateByPrimaryKey(TCert record);
}