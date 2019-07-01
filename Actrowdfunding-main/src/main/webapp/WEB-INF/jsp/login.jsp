<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/24
  Time: 22:19
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
    <meta name="keys" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/login.css">
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form  id="loginForm" class="form-signin" role="form" action="${APP_PATH}/doLogin.do" method="post">

        <span class="label label-danger">${exception.message}</span>

        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" name="loginacct" class="form-control" id="floginacct" placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" name="userpawd" id="fuserpawd" placeholder="请输入登录密码" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <select class="form-control " name="type" id="ftype" >
                <option value="member" >会员</option>
                <option value="user" selected >管理</option>
            </select>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me" checked> 记住我
            </label>
            <br>
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="${APP_PATH}/reg.htm">我要注册</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
    </form>
</div>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/jquery/layer/layer.js"></script>
<script>
    function dologin() {

        // 获取登录的信息
        var type = $("#ftype");
        var loginacct = $("#floginacct");
        var userpawd = $("#fuserpawd");
        if($.trim(loginacct.val())==""){

            layer.msg('账号不能为空', {icon: 5,anim:6,time:2000},function () {

                loginacct.val("")
                loginacct.focus();

            });
            return false;
        }
        if($.trim(userpawd.val())==""){

            layer.alert('密码不能为空', {icon: 5,anim:6,time:2000},function () {

                userpawd.val("")
                userpawd.focus();
            });
            return false;
        }

        $.ajax({
            type:"post",
            url:"${APP_PATH}/doLogin.do",
            data:{
                'type':type.val(),
                'loginacct':loginacct.val(),
                'userpswd':userpawd.val()
            },
            beforeSend:function () {
                  //验证表单数据

                layer.load(1);

            },
            success:function (result) {

                if(result.issuccess){

                    setTimeout(function () {
                        layer.closeAll('loading');
                        window.location.replace("${APP_PATH}/main.htm");
                    },2000)
                }else {

                    setTimeout(function () {
                        layer.closeAll('loading');

                    },2000)
                    layer.msg("not ok "+result.message,{time:2000,icon:5,anim:1});
                }
            },
            error:function () {
                layer.closeAll('loading');
                layer.msg("登录失败",{time:2000,icon:5});

            }
        })


    }
</script>
</body>
</html>