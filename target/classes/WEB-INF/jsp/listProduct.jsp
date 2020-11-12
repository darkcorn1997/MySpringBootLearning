<%--
  Created by IntelliJ IDEA.
  User: xx
  Date: 2020/11/12
  Time: 2:41 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div align="center">

</div>

<div style="width:500px;margin:20px auto;text-align: center">
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>id</td>
            <td>name</td>
            <td>price</td>
            <td>cid</td>
            <td>编辑</td>
            <td>删除</td>
        </tr>
        <!--通过page.getList遍历当前页面的Product对象。
        在分页的时候通过page.pageNum获取当前页面，page.pages获取总页面数。
        注：page.getList会返回一个泛型是Product的集合。-->

        <c:forEach items="${page.list}" var="p" varStatus="st">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.price}</td>
                <td>${p.cid}</td>
                <td><a href="editProduct?id=${p.id}">编辑</a></td>
                <td><a href="deleteProduct?id=${p.id}">删除</a></td>
            </tr>
        </c:forEach>

    </table>
    <br>
    <div>
        <a href="?start=1">[首 页]</a>
        <a href="?start=${page.pageNum-1}">[上一页]</a>
        <a href="?start=${page.pageNum+1}">[下一页]</a>
        <a href="?start=${page.pages}">[末 页]</a>
    </div>
    <br>
    <form action="addProduct" method="post">

        name: <input name="name"> <br>
        price: <input name="price"> <br>
        <button type="submit">提交</button>

    </form>
</div>
