package com.atguigu.crowdfunding.manager.dao;


import com.atguigu.atcrowdfunding.bean.TAdvertisement;

import java.util.List;

public interface TAdvertisementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAdvertisement record);

    TAdvertisement selectByPrimaryKey(Integer id);

    List<TAdvertisement> selectAll();

    int updateByPrimaryKey(TAdvertisement record);
}