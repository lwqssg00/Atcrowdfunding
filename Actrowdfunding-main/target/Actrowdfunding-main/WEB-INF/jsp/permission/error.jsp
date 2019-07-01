<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/2
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${APP_APTH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/jquery/layer/layer.js"></script>
</head>
<body>

<script>

    $(function () {
        layer.msg("添加失败",{time:3000,icon:5});
        setTimeout(function () {
            window.location.replace("${APP_PATH}/permission/toIndex.htm");
        },3000)
    })
</script>
</body>

</html>
