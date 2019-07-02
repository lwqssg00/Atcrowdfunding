package com.atguigu.atcrowdfunding.manager.controller;


import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.util.AjaxPageResult;
import com.atguigu.atcrowdfunding.util.AjaxResultUtil;
import com.atguigu.atcrowdfunding.util.RoleNode;
import com.atguigu.crowdfunding.manager.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @RequestMapping("/toIndex")
    public String toIndex(){


        return "role/index";
    }


    @RequestMapping("/index")
    @ResponseBody
    public AjaxPageResult index(Integer pageno,Integer pagesize){

        AjaxPageResult<TRole> ajaxPageResult = new AjaxPageResult<>();

        PageInfo page = null;
        try {
            page = roleService.queryAll(pageno,pagesize);

            if(page!=null){
                ajaxPageResult.setIssuccess(true);
                ajaxPageResult.setMessage("查询成功");
                ajaxPageResult.setPageInfo(page);
            }else{
                ajaxPageResult.setIssuccess(false);
                ajaxPageResult.setMessage("查询失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            ajaxPageResult.setIssuccess(false);
            ajaxPageResult.setMessage("查询失败");
        }
        return ajaxPageResult;
    }




    @RequestMapping("/updateRoleMsg")
    @ResponseBody
    public AjaxResultUtil updateRoleMsg(Integer userid, RoleNode node){

        AjaxResultUtil ajaxResultUtil = new AjaxResultUtil();
        boolean b = false;
        try {
            b = roleService.insertRoseById(userid,node.getNodes());

            if(b){
                ajaxResultUtil.setIssuccess(b);
                ajaxResultUtil.setMessage("分配成功");
            }else {
                ajaxResultUtil.setIssuccess(b);
                ajaxResultUtil.setMessage("分配失败");
            }
        }catch (Exception e){
            ajaxResultUtil.setIssuccess(b);
            ajaxResultUtil.setMessage("出现异常");
        }

        return ajaxResultUtil;

    }




    @RequestMapping("/deleteRoleMsg")
    @ResponseBody
    public AjaxResultUtil deleteRoleMsg(Integer userid, RoleNode node){

        AjaxResultUtil ajaxResultUtil = new AjaxResultUtil();
        boolean b = false;
        try {
            b = roleService.deleteRoleById(userid,node.getNodes());

            if(b){
                ajaxResultUtil.setIssuccess(b);
                ajaxResultUtil.setMessage("删除角色分配成功");
            }else {
                ajaxResultUtil.setIssuccess(b);
                ajaxResultUtil.setMessage("删除分配失败");
            }
        }catch (Exception e){
            ajaxResultUtil.setIssuccess(b);
            ajaxResultUtil.setMessage("出现异常");
        }

        return ajaxResultUtil;

    }


}
