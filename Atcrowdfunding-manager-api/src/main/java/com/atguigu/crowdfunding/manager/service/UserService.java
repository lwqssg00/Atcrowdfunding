package com.atguigu.crowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.bean.TUser;
import com.atguigu.atcrowdfunding.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    TUser queryUserLogin(Map<String, Object> map,String salt);


    boolean insertUser(TUser user);

    String queryUserByName(String loginacct,String userpswd);


//    Page queryPage(Integer pageno, Integer pagesize);


    Page queryPage(Map<String,Object> map);

    TUser queryUserById(@Param("id") Integer id);

    boolean updateUser(TUser user);

    boolean deleteUserById(Integer id);


    boolean deleteALLUserById(List<Integer> ids);

    TUser queryRoleByid(Integer id);

    List<TRole> queryNotHasRole(List<Integer> ids);
}
