<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/28
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

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
    <link rel="stylesheet" href="${APP_PATH}/ztree/zTreeStyle.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
        table tbody tr:nth-child(odd){background:#F4F4F4;}
        table tbody td:nth-child(even){color:#C00;}
    </style>
</head>

<body>

<%@include file="/WEB-INF/jsp/common/top.jsp"%>

<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/jsp/common/left.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree"></ul>
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
<script src="${APP_PATH}/ztree/jquery.ztree.all-3.5.min.js"></script>
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

        var setting = {

            view:{
                addDiyDom:addDiyDom,
                addHoverDom: addHoverDom,
                removeHoverDom: removeHoverDom

            }

        };
        function addHoverDom(treeId, treeNode) {
            var aObj = $("#" + treeNode.tId + "_a");
            if ($("#btn_group_"+treeNode.id).length>0) return;

            var spangroup = "<span id='btn_group_"+treeNode.id+"'>";

            if(treeNode.level==0){
                spangroup+="<button class='btn btn-default' onclick='window.location.href=\"${APP_PATH}/permission/toUpdate.htm?id="+treeNode.id+"\"'><span class='glyphicon glyphicon-edit'></span></button>";
                spangroup+="<button class='btn btn-default' onclick='window.location.href=\"${APP_PATH}/permission/toAdd.htm?id="+treeNode.id+"\"'><span class='glyphicon glyphicon glyphicon-plus'></span></button>";

            }else if(treeNode.level==1){

                spangroup+="<button class='btn btn-default' onclick='window.location.href=\"${APP_PATH}/permission/toUpdate.htm?id="+treeNode.id+"\"'><span class='glyphicon glyphicon-edit'></span></button>";
                if(treeNode.children.length==0){
                    spangroup+="<button class='btn btn-default'><span class='glyphicon glyphicon-remove'></span></button>";
                }
                spangroup+="<button class='btn btn-default' onclick='window.location.href=\"${APP_PATH}/permission/toAdd.htm?id="+treeNode.id+"\"'><span class='glyphicon glyphicon glyphicon-plus'></span></button>";

            }else if(treeNode.level==2){

                spangroup+="<button class='btn btn-default' onclick='window.location.href=\"${APP_PATH}/permission/toUpdate.htm?id="+treeNode.id+"\"'><span class='glyphicon glyphicon-edit'></span></button>";

                spangroup+="<button class='btn btn-default'><span class='glyphicon glyphicon-remove'></span></button>";
                spangroup+="<button class='btn btn-default' onclick='window.location.href=\"${APP_PATH}/permission/toAdd.htm?id="+treeNode.id+"\"'><span class='glyphicon glyphicon glyphicon-plus'></span></button>";
            }
            spangroup+="</span>"
            aObj.append(spangroup);

        };
        function removeHoverDom(treeId, treeNode) {
            $("#btn_group_"+treeNode.id).unbind().remove();
            // $("#diyBtn_space_" +treeNode.id).unbind().remove();
        };



        function addDiyDom(treeId, treeNode) {

            var aObj = $("#" + treeNode.tId + "_ico");

            if(treeNode.icon){
                aObj.removeClass("button ico_docu").addClass(treeNode.icon).css({"background":""});
            }
        }

        $.ajax({

            type:"post",

            url:"${APP_PATH}/permission/queryPermission.do",

            beforeSend:function () {

            },
            success:function (result) {
                if(result.issuccess){


                    var zNodes =result.ajaxMsg.permissions;
                    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                    layer.msg(result.message,{time:2000,icon:6})
                }else {

                    layer.msg(result.message,{time:2000,icon:5})
                }

            },
            error:function () {
                layer.msg("出现异常",{time:2000,icon:5})
            }

        })














    });

</script>
</body>
</html>

