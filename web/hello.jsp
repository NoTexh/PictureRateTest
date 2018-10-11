<%--
  Created by IntelliJ IDEA.
  User: xNoTe
  Date: 11.10.2018
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="upload" method="post" enctype="multipart/form-data">
    <input type="text" name="description">
    <input type="text" name="name">
    <input type="file" name="file">
    <input type="submit">
</form>

</body>
</html>
