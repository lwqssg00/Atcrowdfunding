package com.atguigu.crowdfunding.manager.dao;


import com.atguigu.atcrowdfunding.bean.TRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    TRole selectByPrimaryKey(Integer id);

    List<TRole> selectAll();

    int updateByPrimaryKey(TRole record);

    List<TRole> queryNotHasRole(List<Integer> ids);

    int insertRoseById(@Param("user_id") Integer id,@Param("role_id") Integer i);

    int deleteRoleById(@Param("user_id") Integer id,@Param("role_id") Integer i);

    List<TRole> queryAll();
}