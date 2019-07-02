<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/29
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/main.css">
    <link rel="stylesheet" href="${APP_PATH}/css/doc.min.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }

        select{
            appearance:none;
            -moz-appearance:none;
            -webkit-appearance:none;
        }
    </style>
</head>

<body>

<%@ include file="/WEB-INF/jsp/common/top.jsp"%>

<div class="container-fluid">
    <div class="row">
        
        <%@ include file="/WEB-INF/jsp/common/left.jsp"%>
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">新增</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form role="form" id="form">
                        <div class="form-group">
                            <label for="furl">管理URL</label>
                            <input type="text" class="form-control" id="furl" placeholder="请输入url">
                        </div>

                        <div class="form-group">
                            <label for="furl">选择图标</label>
                            <select class="form-control"  id="ficon">
                                <option value="glyphicon glyphicon-picture" selected>
                                    glyphicon glyphicon-picture
                                </option>
                                <option value="glyphicon glyphicon-blackboard">
                                    glyphicon glyphicon-blackboard
                                </option>
                            </select>

                        </div>

                        <div class="form-group">
                            <label for="fname">权限名称</label>
                            <input type="text" class="form-control"  id="fname" placeholder="请输入权限名称">
                        </div>

                        <button type="button" id="addBtn" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                        <button type="button" id="resetBtn" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题1</h4>
                    <p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题2</h4>
                    <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
                </div>
            </div>
            <!--
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
            -->
        </div>
    </div>
</div>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/script/docs.min.js"></script>
<script src="${APP_PATH}/jquery/layer/layer.js"></script>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });

    });
    $("#addBtn").click(function () {

        var furl= $("#furl");

        var name = $("#fname");

        var icon = $("#ficon")

        $.ajax({
            type:"POST",
            data:{
                "pid":${param.id},
                'url':furl.val(),
                'name':name.val(),
                'icon':icon.val()
            },
            url:"${APP_PATH}/permission/addPermission.do",

            beforeSend:function () {

                if(furl.val()==''){
                    url.focus();
                    layer.tips('url不能为空', '#furl');
                    return false;
                }

                if(name.val()==''){
                    name.focus();
                    layer.tips('权限名不能为空', '#fname');
                    return false;
                }

                return true

            },
            success:function (result) {
                if(result.issuccess){

                    layer.msg("添加成功",{time:1000,icon:6,anim:1})

                    window.location.href="${APP_PATH}/permission/toIndex.htm";

                }else {
                    layer.msg(result.message,{time:3000,icon:5,anim:1})
                }

            },
            error:function () {
                layer.msg("添加错误",{time:3000,icon:5,anim:1})
            }

        })
    });

    $("#resetBtn").click(function () {
        $("#form")[0].reset();
    })

</script>
</body>
</html>
