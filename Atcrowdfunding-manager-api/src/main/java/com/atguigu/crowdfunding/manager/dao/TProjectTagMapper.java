package com.atguigu.crowdfunding.manager.dao;


import com.atguigu.atcrowdfunding.bean.TProjectTag;

import java.util.List;

public interface TProjectTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProjectTag record);

    TProjectTag selectByPrimaryKey(Integer id);

    List<TProjectTag> selectAll();

    int updateByPrimaryKey(TProjectTag record);
}