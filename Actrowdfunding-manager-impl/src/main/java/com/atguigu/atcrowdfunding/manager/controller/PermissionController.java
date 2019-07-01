package com.atguigu.atcrowdfunding.manager.controller;


import com.atguigu.atcrowdfunding.bean.TPermission;
import com.atguigu.atcrowdfunding.util.AjaxMsg;
import com.atguigu.atcrowdfunding.util.AjaxResultUtil;
import com.atguigu.crowdfunding.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;



    @RequestMapping("/toIndex")
    public String toPermission(){


        return "permission/index";

    }



    @RequestMapping("/toUpdate")
    public String toUpdate(){


        return "permission/update";

    }


    @RequestMapping("/toAdd")
    public String toAdd(){


        return "permission/add";

    }




    @RequestMapping("/addPermission")
    @ResponseBody
    public AjaxResultUtil  addPermission(TPermission tPermission){

        //定义一个可以保存所有数据的root对象
        AjaxResultUtil root = new AjaxResultUtil();

        //找到所有的permission
        try {

            boolean b=  permissionService.insertPermission(tPermission);

            if(b) {

                root.setMessage("添加成功");
                root.setIssuccess(true);
            }else{
                root.setMessage("添加失败");
                root.setIssuccess(false);
            }

        } catch (Exception e){
            root.setMessage("添加异常");
            root.setIssuccess(false);
        }
        return root;
    }





    @RequestMapping("/queryPermission")
    @ResponseBody
    public AjaxResultUtil queryPermission(){

        //定义一个可以保存所有数据的root对象
        AjaxResultUtil root = new AjaxResultUtil();


        //找到所有的permission
        try {

            AjaxMsg ajaxMsg = new AjaxMsg();
            List<TPermission> permissions =  permissionService.queryPermission();

            //分装成对应的数据

            Map<Integer,TPermission> map  = new HashMap<>();


            for (TPermission p:permissions){
                map.put(p.getId(),p);

            }
            for (TPermission permission:permissions){

                TPermission children = permission;
                if(children.getPid()==0){
                    children.setOpen(true);
                    ajaxMsg.getPermissions().add(children);
                }else{

                    TPermission parent = map.get(children.getPid());
                    parent.getChildren().add(children);

                }
            }
            root.setAjaxMsg(ajaxMsg);
            root.setMessage("查询成功");
            root.setIssuccess(true);
        } catch (Exception e){
            root.setMessage("查询异常");
            root.setIssuccess(false);
        }
        return root;
    }


}
