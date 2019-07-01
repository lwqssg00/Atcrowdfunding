<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/24
  Time: 22:27
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

    <form class="form-signin" role="form" id="registerForm" action="${APP_PATH}/doRegister.do">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户注册</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="floginacct" placeholder="请输入登录账号" name="loginacct" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
            <span class="label label-danger from_tx" id="login_tx"></span>
        </div>

        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="fuserpswd" name="userpswd" placeholder="请输入登录密码" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            <span class="label label-danger from_tx" id="userpswd_tx"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="email" name="email" class="form-control" id="femail" placeholder="请输入邮箱地址" style="margin-top:10px;">
            <span class="glyphicon glyphicon glyphicon-envelope form-control-feedback"></span>
            <span class="label label-danger from_tx" id="email_tx"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <select class="form-control" name="type" id="ftype">
                <option value="0">企业</option>
                <option value="1">个人</option>
            </select>
        </div>
        <div class="checkbox">
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="${APP_PATH}/login.htm">我有账号</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="doRegister()"> 注册</a>
    </form>
</div>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>

    <script>

        function doRegister(){
            var floginacct = $("#floginacct");
            var fuserpswd = $("#fuserpswd");
            var femail = $("#femail");

            if($.trim(floginacct.val())==''){
                $("#login_tx").text("账号不能为空");
                $("#userpswd_tx").text("");
                $("#email_tx").text("");
                floginacct.focus();
                return false;
            }
            if($.trim(fuserpswd.val())==''){

                $("#userpswd_tx").text("密码不能为空");
                $("#login_tx").text("");
                $("#email_tx").text("");
                fuserpswd.focus()
                return false;
            }
            if($.trim(femail.val())==''){

                $("#email_tx").text("邮箱不能为空");
                $("#login_tx").text("");
                $("#userpswd_tx").text("");
                femail.focus();
                return false;
            }

            $("#registerForm").submit();
        }


    </script>


</body>
</html>

