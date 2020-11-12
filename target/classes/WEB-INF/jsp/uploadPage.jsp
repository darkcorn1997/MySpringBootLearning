<%--
  Created by IntelliJ IDEA.
  User: xx
  Date: 2020/11/12
  Time: 6:08 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<form action="upload" method="post" enctype="multipart/form-data">
    选择图片:<input type="file" name="file" accept="image/*" /> <br>
    <input type="submit" value="上传">
</form>
