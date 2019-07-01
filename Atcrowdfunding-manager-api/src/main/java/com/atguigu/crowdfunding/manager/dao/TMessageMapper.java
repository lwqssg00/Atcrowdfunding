package com.atguigu.crowdfunding.manager.dao;


import com.atguigu.atcrowdfunding.bean.TMessage;

import java.util.List;

public interface TMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMessage record);

    TMessage selectByPrimaryKey(Integer id);

    List<TMessage> selectAll();

    int updateByPrimaryKey(TMessage record);
}