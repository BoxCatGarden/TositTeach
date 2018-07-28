<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<body>
<h2>Hello World!</h2>
<form action="/uploadtest" method="post" enctype="multipart/form-data">
    <input type="file" id="file" name="file" placeholder="请选择一个文件"/>
    <br>
    <button type="submit">提交</button>
</form>
</body>
</html>
