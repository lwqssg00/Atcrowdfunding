package com.atguigu.crowdfunding.manager.dao;


import com.atguigu.atcrowdfunding.bean.TParam;

import java.util.List;

public interface TParamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TParam record);

    TParam selectByPrimaryKey(Integer id);

    List<TParam> selectAll();

    int updateByPrimaryKey(TParam record);
}