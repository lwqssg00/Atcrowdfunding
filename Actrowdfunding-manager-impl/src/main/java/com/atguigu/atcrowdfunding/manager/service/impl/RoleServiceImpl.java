package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.crowdfunding.manager.dao.TRoleMapper;
import com.atguigu.crowdfunding.manager.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private TRoleMapper tRoleMapper;


    @Override
    public boolean insertRoseById(Integer id,List<Integer> ids) {
        int cout = 0;

        for (Integer i:ids){
            int count  =tRoleMapper.insertRoseById(id,i);
            cout+=count;

        }
        return cout==ids.size()?true:false;
    }

    @Override
    public boolean deleteRoleById(Integer id, List<Integer> ids) {

        int cout = 0;

        for (Integer i:ids){
            int count  =tRoleMapper.deleteRoleById(id,i);
            cout+=count;
        }
        return cout==ids.size()?true:false;

    }

    @Override
    public PageInfo queryAll(Integer pageNum,Integer pageSize) {


        PageHelper.startPage(pageNum,pageSize);
        List<TRole> list = tRoleMapper.queryAll();

        PageInfo page = new PageInfo(list);

        return page;
    }
}
