<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/30
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    </style>
</head>

<body>

<jsp:include page="/WEB-INF/jsp/common/top.jsp"/>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="/WEB-INF/jsp/common/left.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form role="form" class="form-inline">
                        <div class="form-group">
                            <label for="leftUserRoleList">未分配角色列表</label><br>
                            <select id="leftUserRoleList" class="form-control" multiple size="10" style="width:250px;overflow-y:auto;">
                                <c:forEach items="${notexit_role}" var="obj">
                                    <option value="${obj.id}">${obj.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li class="btn btn-default glyphicon glyphicon-chevron-right" id="leftAddUserBtn"></li>
                                <br>
                                <li class="btn btn-default glyphicon glyphicon-chevron-left" id="rightAddUserBtn" style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="rightUserRoleList">已分配角色列表</label><br>
                            <select class="form-control" id="rightUserRoleList" multiple size="10" style="width:250px;overflow-y:auto;">
                                <c:forEach items="${exit_role}" var="obj">
                                    <option value="${obj.id}">${obj.name}</option>
                                </c:forEach>
                            </select>
                        </div>
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

        </div>
    </div>
</div>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/jquery/layer/layer.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/script/docs.min.js"></script>
<script src="${APP_PATH}/jquery/menu.js"></script>
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
        selectBar();
    });

    
    $("#leftAddUserBtn").click(function () {


        var leftNode = $("#leftUserRoleList option:selected");

        var jsonObj={
            userid:"${user_id}"
        };

        $.each(leftNode,function (i,n) {

            jsonObj["nodes["+i+"]"]=this.value;

        });

        console.log(jsonObj);
        $.ajax({
            type:"POST",
            data:jsonObj,
            url:"${APP_PATH}/role/updateRoleMsg.do",
            
            beforeSend:function () {
                return true;
            },
            success:function (result) {

                if(result.issuccess){
                    $("#rightUserRoleList").append(leftNode);

                    layer.msg(result.message,{time:1000,icon:6,anim:1})
                    setTimeout(function(){
                        window.location.href="${APP_PATH}/user/toAssignRole.htm?id="+${user_id};
                    },2000);
                }else{
                    layer.msg(result.message,{time:1000,icon:5,anim:1})
                }

            },
            error:function () {
                layer.msg("分配角色失败",{time:1000,icon:5,anim:1})
            }

        })
        

    });

    $("#rightAddUserBtn").click(function () {

        var rigthNode = $("#rightUserRoleList option:selected");

        var jsonObj={
            userid:"${user_id}"
        };

        $.each(rigthNode,function (i,n) {

            jsonObj["nodes["+i+"]"]=this.value;

        });
        console.log(jsonObj);
        $.ajax({
            type:"POST",
            data:jsonObj,
            url:"${APP_PATH}/role/deleteRoleMsg.do",

            beforeSend:function () {
                return true;
            },
            success:function (result) {

                if(result.issuccess){
                    $("#leftUserRoleList").append(rigthNode);
                    layer.msg(result.message,{time:2000,icon:6,anim:1})
                    setTimeout(function(){
                        window.location.href="${APP_PATH}/user/toAssignRole.htm?id="+${user_id};
                    },2000);
                }else{
                    layer.msg(result.message,{time:2000,icon:5,anim:1})
                }

            },
            error:function () {
                layer.msg("删除分配角色失败",{time:2000,icon:5,anim:1})
            }

        })



    });




</script>
</body>
</html>

