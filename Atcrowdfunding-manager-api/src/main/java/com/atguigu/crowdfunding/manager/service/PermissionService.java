package com.atguigu.crowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.TPermission;

import java.util.List;

public interface PermissionService {
    List<TPermission> queryPermission();

    boolean insertPermission(TPermission tPermission);

    TPermission queryPermissionById(Integer id);

    boolean updatePermission(TPermission tPermission);

    boolean deletePermission(Integer id);
}
