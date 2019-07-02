package com.atguigu.crowdfunding.manager.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {



    boolean insertRoseById(Integer id,List<Integer> ids);

    boolean deleteRoleById(Integer userid, List<Integer> nodes);

    PageInfo queryAll(Integer pageNum, Integer pageSize);
}
