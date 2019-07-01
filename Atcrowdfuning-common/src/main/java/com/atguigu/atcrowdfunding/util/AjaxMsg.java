package com.atguigu.atcrowdfunding.util;

import com.atguigu.atcrowdfunding.bean.TPermission;

import java.util.ArrayList;
import java.util.List;

public class AjaxMsg {

    List<TPermission> permissions = new ArrayList<>();

    public List<TPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<TPermission> permissions) {
        this.permissions = permissions;
    }
}
