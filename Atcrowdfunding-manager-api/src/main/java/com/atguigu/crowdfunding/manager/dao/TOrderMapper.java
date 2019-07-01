package com.atguigu.crowdfunding.manager.dao;


import com.atguigu.atcrowdfunding.bean.TOrder;

import java.util.List;

public interface TOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TOrder record);

    TOrder selectByPrimaryKey(Integer id);

    List<TOrder> selectAll();

    int updateByPrimaryKey(TOrder record);
}