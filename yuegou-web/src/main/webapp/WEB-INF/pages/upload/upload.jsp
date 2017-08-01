<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/1
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/user/uploadFile" method="post" enctype="multipart/form-data">
        <input type="file" name="f">
        <input type="text" name="content">
        <input type="submit" value="上传">
    </form>
</body>
</html>
