package com.atguigu.crowdfunding.manager.dao;


import com.atguigu.atcrowdfunding.bean.TPermission;

import java.util.List;

public interface TPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPermission record);

    TPermission selectByPrimaryKey(Integer id);

    List<TPermission> selectAll();

    int updateByPrimaryKey(TPermission record);

    List<TPermission> queryPermission();

    TPermission queryPermissionById(Integer id);

    int updatePermission(TPermission tPermission);
}