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
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" type="text" id="queryText" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" id="queryBtn" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" id="alldeleteBtn"style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/user/toAdd.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">

                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input id="allcheckbox" type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                            <tfoot>
                            <tr >
                                <td colspan="6" align="center">
                                    <ul class="pagination">

                                    </ul>
                                </td>
                            </tr>

                            </tfoot>
                        </table>
                    </div>
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
        queryPageUser(1);
        selectBar();
    });

    var jsonObj={
        "pageno":1,
        "pagesize":10
    }

    function pageChange(pageno) {

        queryPageUser(pageno)
    }

    function queryPageUser(pageno) {

        jsonObj.pageno=pageno;

        var loadingIndex=-1;
        $.ajax({
            type:"POST",
            data:jsonObj,
            url:"${APP_PATH}/user/index.do",

            beforeSend:function () {
                loadingIndex = layer.load(2,{time:10*1000});
                return true;
            },
            success:function (result) {

                layer.close(loadingIndex);
                if(result.issuccess){

                    var page = result.page;

                    var data =page.datas;

                    var pre_content="";
                    $.each(data,function (i,n) {

                        pre_content+='<tr>';
                        pre_content+='	<td>'+(i+1)+'</td>';
                        pre_content+='	<td><input type="checkbox" id="'+n.id+'"></td>';
                        pre_content+='	<td>'+n.loginacct+'</td>';
                        pre_content+='	<td>'+n.username+'</td>';
                        pre_content+='	<td>'+n.email+'</td>';
                        pre_content+='	<td>';
                        pre_content+='		<button type="button" class="btn btn-success btn-xs" onclick="window.location.href=\''+'${APP_PATH}/user/toAssignRole.htm?id='+n.id+'\'"><i class=" glyphicon glyphicon-check"></i></button>';
                        pre_content+='		<button type="button" class="btn btn-primary btn-xs" onclick="window.location.href=\''+'${APP_PATH}/user/toUpdate.do?id='+n.id+'\'"><i class=" glyphicon glyphicon-pencil"></i></button>';
                        pre_content+='		<button type="button" class="btn btn-danger btn-xs" onclick="deleteUser('+n.id+','+n.loginacct+')"><i class=" glyphicon glyphicon-remove"></i></button>';
                        pre_content+='	</td>';
                        pre_content+='</tr>';

                    })
                    $("tbody").html(pre_content);


                    var tar = "";

                    if(page.pageno==1){
                        tar+='<li class="disabled"><a href="#">上一页</a></li>';
                        console.log(tar);
                    }else{
                        tar+='<li><a href="#" onclick="pageChange('+(page.pageno - 1)+')">上一页</a></li>';
                    }

                    for(var i=1;i<=page.totalno;i++){

                        tar+='<li ';

                        if (i==page.pageno){
                             tar +='class="active"'
                        }
                        tar+='> <a href="#" onclick="pageChange('+i+')">'+i+'</a></li>'

                    }
                    if(page.pageno==page.totalno){
                        tar+='<li class="disabled"><a href="#">下一页</a></li>'
                    }else{
                        tar+=' <li><a href="#" onclick="pageChange('+(page.pageno + 1)+')">下一页</a></li>';
                    }

                    $(".pagination").html(tar);

                }else{

                    layer.msg(result.message,{time:1000,icon:5,shift:6})

                }
            },
            error:function () {
                layer.msg("加载数据失败",{time:1000,icon:5,shift:6})
            }
        })

    }


    $("#queryBtn").click(function () {

        var qeuryText=$("#queryText").val();

        jsonObj.queryText = qeuryText;

        queryPageUser(1);
    })


    function deleteUser(id,loginacct){

        layer.confirm('确认删除用户'+loginacct+'?', {
            btn: ['确定','取消'] //按钮
        }, function(){
            layer.msg('正在删除', {icon: 1});

            $.ajax({
                type:"POST",
                data:{
                    "id":id
                },
                url:"${APP_PATH}/user/deleteUser.do",

                beforeSend:function () {

                    return true;
                },
                success:function (result) {

                    if(result.issuccess){
                        layer.msg('删除成功', {icon: 1});
                        window.location.href="${APP_PATH}/user/toIndex.htm"
                    }else {
                        layer.msg('删除失败', {icon: 5});
                    }
                },
                error:function () {
                }
            })
        }, function(){
            layer.msg('已取消', {
                time: 2000 //20s后自动关闭
            });
        });

    }

    $("#allcheckbox").click(function () {
        var checkStatus =this.checked;
        $("tbody tr td input[type='checkbox']").prop("checked",checkStatus);


    })


    $("#alldeleteBtn").click(function () {

        //找到所有钩中的checkbox

        var allchecked = $("tbody tr td input[type='checkbox']:checked");

        if (allchecked==null &&allchecked==undefined){
            layer.msg('没有选中要删除的元素，请选择', {time:2000,icon: 5});
        }else{


            layer.confirm('确认删除用户?', {
                btn: ['确定','取消'] //按钮
            }, function(){
                var ids =[];
                //拿到对应的id
                $.each(allchecked,function (i,n) {

                    ids.push(n.id);
                });

                var index = -1;

                $.ajax({
                    type:"POST",
                    contentType:"application/json",
                    data:JSON.stringify(ids),
                    url:"${APP_PATH}/user/deleteAllUser.do",

                    beforeSend:function () {
                        index = layer.load(1);
                        return true;
                    },
                    success:function (result) {
                        if(result.issuccess){
                            layer.close(index);
                            layer.msg('删除成功', {icon: 6});
                            window.location.href="${APP_PATH}/user/toIndex.htm"
                        }else {
                            layer.close(index);
                            layer.msg('删除失败', {icon: 5});
                        }
                    },
                    error:function () {
                        layer.close(index);
                        layer.msg('出现错误', {icon: 5});
                    }
                })
            }, function(){
                layer.msg('已取消', {
                    time: 2000 //20s后自动关闭
                });
            });
        }

    })






</script>
</body>
</html>

