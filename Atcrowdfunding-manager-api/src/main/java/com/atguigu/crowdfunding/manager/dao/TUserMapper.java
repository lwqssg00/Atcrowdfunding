package com.atguigu.crowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.TPswdSalt;
import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.bean.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    List<TUser> selectAll();

    int updateByPrimaryKey(TUser record);

    TUser queryUserLogin(Map<String, Object> map);


    List<TPswdSalt> queryUserByName(@Param("loginacct") String loginacct);

//    List<TUser> queryList(@Param("startindex") Integer startindex,@Param("pagesize") Integer pagesize);
//
//    Integer queryCount();

    List<TUser> queryList(Map<String,Object> map);

    Integer queryCount(Map<String,Object> map);

    TUser queryRoleByid(Integer id);

    List<TRole> queryNotHasRole(List<Integer> ids);

    TUser queryUserById(Integer id);
}