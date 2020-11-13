<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<div style="margin:0px auto; width:500px">

    <form action="updateProduct" method="post">

        name: <input name="name" value="${p.name}"> <br>
        price: <input name="price" value="${p.price}"> <br>

        <input name="id" type="hidden" value="${p.id}">
        <button type="submit">提交</button>

    </form>
</div>
