package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.TPswdSalt;
import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.bean.TUser;
import com.atguigu.atcrowdfunding.exception.LoginFailExecption;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.util.StringUtil;
import com.atguigu.crowdfunding.manager.dao.TRoleMapper;
import com.atguigu.crowdfunding.manager.dao.TUserMapper;
import com.atguigu.crowdfunding.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserMapper tUserMapper;


    @Autowired
    private TRoleMapper tRoleMapper;

    @Override
    public TUser queryUserLogin(Map<String, Object> map,String salt) {

        String userpswd = MD5Util.generate2((String) map.get("userpswd"), salt);
        System.out.println(userpswd);
        map.put("userpswd",userpswd);

        TUser tUser = tUserMapper.queryUserLogin(map);

        System.out.println(tUser);

        if (tUser==null){
            throw new LoginFailExecption("用户账号或密码不正确");
        }
        return tUser;
    }

    @Override
    public boolean insertUser(TUser user) {

        //补充user信息 对密码加入，创建时间

        List<String> generate = MD5Util.generate((user.getUserpswd()));

        user.setUserpswd(generate.get(1));
        user.setSalt(generate.get(0));
        if(StringUtil.isEmpty(user.getUsername())){
            user.setUsername("普通用户");
        }
        Date d = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String format = simpleDateFormat.format(d);
        user.setCreatetime(format);

        user.setHash(generate.get(1));
        int insert = tUserMapper.insert(user);

        return insert>0?true:false;

    }

    @Override
    public String queryUserByName(String loginacct,String userpswd) {

        //要先对密码进行加密，所以要先查询盐值,

        List<TPswdSalt> pswdList = tUserMapper.queryUserByName(loginacct);

        //对密码加密
        String  sale ="";
        for (TPswdSalt tPswdSalt:pswdList) {

            //获得加密后的结果
            String s = MD5Util.generate2(userpswd, tPswdSalt.getSalt());
            System.out.println(s);
            if (s.equals(tPswdSalt.getPassword())) {
                sale= tPswdSalt.getSalt();
                break;
            }
        }

        return sale;
    }

//    @Override
//    public Page queryPage(Integer pageno, Integer pagesize) {
//
//        Page<TUser> page = new Page<>(pageno,pagesize);
//
//        //设置从数据开始索引
//
//        Integer startindex =page.getStartIndex();
//
//
//        List<TUser> users =  tUserMapper.queryList(startindex,pagesize);
//
//        page.setDatas(users);
//
//        Integer count = tUserMapper.queryCount();
//
//        page.setTotalsize(count);
//        return page;
//    }
    @Override
    public Page queryPage(Map<String,Object> map) {


        Page<TUser> page = new Page<>((Integer) map.get("pageno"),(Integer)map.get("pagesize"));

        //设置从数据开始索引

        Integer startindex =page.getStartIndex();

        map.put("startindex",startindex);



//        List<TUser> users =  tUserMapper.queryList(startindex,pagesize);

        List<TUser> users =  tUserMapper.queryList(map);
        page.setDatas(users);

        Integer count = tUserMapper.queryCount(map);

        page.setTotalsize(count);
        return page;
    }

    @Override
    public TUser queryUserById(Integer id) {


        TUser user = tUserMapper.queryUserById(id);

        return user;
    }

    @Override
    public boolean updateUser(TUser user) {


        int i = tUserMapper.updateByPrimaryKey(user);

        return i>0?true:false;
    }

    @Override
    public boolean deleteUserById(Integer id) {

        int i = tUserMapper.deleteByPrimaryKey(id);
        return i>0?true:false;
    }

    @Override
    public boolean deleteALLUserById(List<Integer> ids) {
        int count=0;
        for (int i =0;i<ids.size();i++){

            Integer id = ids.get(i);
            int c = tUserMapper.deleteByPrimaryKey(id);
            count+=c;
        }

        return count==(ids.size())?true:false;
    }

    @Override
    public TUser queryRoleByid(Integer id) {

        //先根据id 找到
        TUser user = tUserMapper.queryRoleByid(id);

        System.out.println(user);

        return user;
    }

    @Override
    public List<TRole> queryNotHasRole(List<Integer> ids) {

        List<TRole> notexit_role = tRoleMapper.queryNotHasRole(ids);
        return notexit_role;
    }
}
