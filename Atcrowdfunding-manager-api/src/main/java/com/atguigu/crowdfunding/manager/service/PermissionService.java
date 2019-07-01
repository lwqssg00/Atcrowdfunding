package com.atguigu.crowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.TPermission;

import java.util.List;

public interface PermissionService {
    List<TPermission> queryPermission();

    boolean insertPermission(TPermission tPermission);
}
