package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.TPermission;
import com.atguigu.crowdfunding.manager.dao.TPermissionMapper;
import com.atguigu.crowdfunding.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {



    @Autowired
    private TPermissionMapper tPermissionMapper;


    @Override
    public List<TPermission> queryPermission() {



        List<TPermission> tPermissions= tPermissionMapper.queryPermission();

        return tPermissions;
    }

    @Override
    public boolean insertPermission(TPermission tPermission) {


        int insert = tPermissionMapper.insert(tPermission);

        return insert>0?true:false;
    }
}
