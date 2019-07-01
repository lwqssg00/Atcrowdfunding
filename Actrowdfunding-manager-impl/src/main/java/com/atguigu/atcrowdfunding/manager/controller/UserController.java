package com.atguigu.atcrowdfunding.manager.controller;


import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.bean.TUser;
import com.atguigu.atcrowdfunding.util.AjaxResultUtil;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.util.StringUtil;
import com.atguigu.crowdfunding.manager.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping("/toIndex")
    public String toIndex(){

        return "user/index";
    }


    @RequestMapping("/toAdd")
    public String toAdd(){

        return "user/add";
    }


    @RequestMapping("/toUpdate")
    public String toUpdate(@Param("id") Integer id, Map map){


        TUser user = null;
        try {
            user = userService.queryUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("user",user);

        return "user/edit";
    }





    @RequestMapping("/toAssignRole")
    public String toAssignRole(Integer id,Map map){

        //根据用户id查询role
       TUser roleLists = userService.queryRoleByid(id);


        //根据用户的roleid 获取没有的其他roleid

        List<TRole> exit_role = roleLists.getRoles();

        List<Integer> ids = new ArrayList<>();
        for (TRole tRole:exit_role) {
            ids.add(tRole.getId());

        }

        List<TRole> notexit_role  =userService.queryNotHasRole(ids);


        map.put("exit_role",exit_role);
        map.put("user_id",id);

        map.put("notexit_role",notexit_role);
        return "user/assignrole";
    }




    @ResponseBody
    @RequestMapping("/index")
    public Object index(@RequestParam(value = "pageno",required = false,defaultValue ="1") Integer pageno,@RequestParam(value = "pagesize",required = false,defaultValue ="10") Integer pagesize,@RequestParam(value = "queryText",required = false)String queryText){

        AjaxResultUtil result = new AjaxResultUtil();
        Map<String,Object> map = new HashMap<>();
        try {

            map.put("pageno",pageno);
            map.put("pagesize",pagesize);

            if(!StringUtil.isEmpty(queryText)){

                if(queryText.contains("%")){

                    queryText =queryText.replace("%","\\\\%");
                }
                map.put("queryText",queryText);
            }

            Page page = userService.queryPage(map);

            result.setPage(page);
            result.setIssuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setIssuccess(false);
            result.setMessage("查询数据失败");
        } finally {
        }
        return result;
    }



    @ResponseBody
    @RequestMapping("/addUser")
    public Object addUser(@Valid TUser user, Errors errors){

        AjaxResultUtil result = new AjaxResultUtil();
        if(errors.hasErrors()){

            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrors = errors.getFieldErrors();

            for (FieldError fieldError:fieldErrors){

                stringBuilder.append("filed :"+ fieldError.getField()+"\t"+"msg :"+fieldError.getDefaultMessage());

            }

            result.setIssuccess(false);
            result.setMessage(stringBuilder.toString());
            return result;
        }

        try {
            boolean b = userService.insertUser(user);

            if(b){
                result.setIssuccess(true);
            }else {
                result.setIssuccess(false);
                result.setMessage("插入数据失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setIssuccess(false);
            result.setMessage("出现异常");
        } finally {
        }
        return result;


    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@Valid TUser user, Errors errors){
        AjaxResultUtil result = new AjaxResultUtil();
        if(errors.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrors = errors.getFieldErrors();
            for (FieldError fieldError:fieldErrors){
                stringBuilder.append(fieldError.getField()+"msg :"+fieldError.getDefaultMessage());

            }
            result.setIssuccess(false);
            result.setMessage(stringBuilder.toString());
            return result;
        }

        try {
            boolean b = userService.updateUser(user);

            if(b){
                result.setIssuccess(true);
            }else {
                result.setIssuccess(false);
                result.setMessage("修改数据失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setIssuccess(false);
            result.setMessage("出现异常");
        } finally {
        }
        return result;


    }

    @ResponseBody
    @RequestMapping("/deleteUser")
    public Object deleteUser(Integer id){
        AjaxResultUtil result = new AjaxResultUtil();
        try {
            boolean b = userService.deleteUserById(id);

            if(b){
                result.setIssuccess(true);
            }else {
                result.setIssuccess(false);
                result.setMessage("删除数据成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setIssuccess(false);
            result.setMessage("出现异常");
        } finally {
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/deleteAllUser")
    public Object deleteUser(@RequestBody List<Integer> ids){

        AjaxResultUtil result = new AjaxResultUtil();
        try {
            boolean b = userService.deleteALLUserById(ids);

            if(b){
                result.setIssuccess(true);
            }else {
                result.setIssuccess(false);
                result.setMessage("删除数据失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setIssuccess(false);
            result.setMessage("出现异常");
        } finally {
        }
        return result;
    }




}
