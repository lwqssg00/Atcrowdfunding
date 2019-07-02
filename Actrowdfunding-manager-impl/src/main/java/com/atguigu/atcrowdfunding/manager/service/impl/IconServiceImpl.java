package com.atguigu.atcrowdfunding.manager.service.impl;


import com.atguigu.atcrowdfunding.bean.TIcon;
import com.atguigu.crowdfunding.manager.dao.IconMapper;
import com.atguigu.crowdfunding.manager.service.IconService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IconServiceImpl implements IconService {


    @Resource
    private IconMapper iconMapper;

    @Override
    public List<TIcon> queryAll() {


        List<TIcon> icons = iconMapper.selectAll();

        return icons;
    }
}
