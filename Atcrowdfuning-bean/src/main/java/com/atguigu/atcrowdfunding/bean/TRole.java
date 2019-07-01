package com.atguigu.atcrowdfunding.bean;

import java.util.List;

public class TRole {
    private Integer id;

    private String name;

    List<TRole> users;

    public List<TRole> getUsers() {
        return users;
    }

    public void setUsers(List<TRole> users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}