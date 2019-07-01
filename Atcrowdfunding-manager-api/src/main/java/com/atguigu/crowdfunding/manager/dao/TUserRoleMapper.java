package com.atguigu.crowdfunding.manager.dao;


import com.atguigu.atcrowdfunding.bean.TUserRole;

import java.util.List;

public interface TUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserRole record);

    TUserRole selectByPrimaryKey(Integer id);

    List<TUserRole> selectAll();

    int updateByPrimaryKey(TUserRole record);
}