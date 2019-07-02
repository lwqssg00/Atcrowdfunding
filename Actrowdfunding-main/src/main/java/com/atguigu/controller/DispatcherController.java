package com.atguigu.controller;

import com.atguigu.atcrowdfunding.bean.TUser;
import com.atguigu.atcrowdfunding.util.AjaxResultUtil;
import com.atguigu.atcrowdfunding.util.ConstUtils;
import com.atguigu.crowdfunding.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class DispatcherController {


    private Logger logger = Logger.getLogger("log");

    @Autowired
    private UserService userService;



    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }



    @RequestMapping("/reg")
    public String reg(){
        return "reg";
    }


    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){

        session.invalidate();

        return "redirect: ./index.htm";
    }
    @ResponseBody
    @RequestMapping("/doLogin")
    public Object doLogin(String type,String loginacct, String userpswd, HttpSession session){

        //封装一个ajax的返回值对象  位于common

        AjaxResultUtil ajaxResultUtil = new AjaxResultUtil();

        try {
            //是否存在此盐值
            String salt = userService.queryUserByName(loginacct,userpswd);


            if (salt==null||salt.equals("")){
                ajaxResultUtil.setIssuccess(false);
                ajaxResultUtil.setMessage("密码错误");
            }else{

                Map<String ,Object> map = new HashMap<>();
                map.put("loginacct",loginacct);
                map.put("userpswd",userpswd);
                map.put("type",type);

                //根据用户名匹配密码

                TUser tUser  = userService.queryUserLogin(map,salt);

                session.setAttribute(ConstUtils.LOGIN_USER,tUser);

                ajaxResultUtil.setIssuccess(true);
                logger.info(tUser.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            ajaxResultUtil.setIssuccess(false);
            ajaxResultUtil.setMessage("登录失败");

        } finally {
        }


        return ajaxResultUtil;
    }



    //同步请求
//    @RequestMapping("/doLogin")
//    public String doLogin(String loginacct, String userpawd, String type, HttpSession session){
//
//        //封装一个ajax的返回值对象  位于common
//
//        AjaxResultUtil ajaxResultUtil = new AjaxResultUtil();
//
//        try {
//            Map<String ,Object> map = new HashMap<>();
//
//            map.put("loginacct",loginacct);
//            map.put("userpawd",userpawd);
//            map.put("type",type);
//
//            TUser tUser  = userService.queryUserLogin(map);
//
//            session.setAttribute(ConstUtils.LOGIN_USER,tUser);
//
//            ajaxResultUtil.setIssuccess(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//        }
//
//        return "redirect:/main.htm";
//    }

    @RequestMapping("/doRegister")
    public String doReqister(TUser user, Model model){


        System.out.println(user);
        boolean b = userService.insertUser(user);

        if (!b){

            model.addAttribute("reg_msg",ConstUtils.REG_FAIL_MSG);

            //提示界面
            return "redirect: ./reg_fail.htm";
        }
        model.addAttribute("reg_msg",ConstUtils.REG_SUCCESS_MSG);
        return "redirect: ./login.htm";
    }


    @RequestMapping("/reg_fail")
    public String reg_fail(){


        //这是注册是否成功的页面
        return "registerMsg";
    }




}
