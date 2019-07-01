package com.atguigu.crowdfunding.manager.dao;


import com.atguigu.atcrowdfunding.bean.TAccountTypeCert;

import java.util.List;

public interface TAccountTypeCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAccountTypeCert record);

    TAccountTypeCert selectByPrimaryKey(Integer id);

    List<TAccountTypeCert> selectAll();

    int updateByPrimaryKey(TAccountTypeCert record);
}