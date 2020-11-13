<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<div style="margin:0px auto; width:500px">

    <form action="../categories/${c.id}" method="post">

        <!--form 下增加 filed, 虽然这个form的method是post, 但是springmvc看到这个_method的值是put后，会把其修改为put.-->
        <input name="_method" type="hidden" value="PUT">
        name: <input name="name" value="${c.name}"> <br>

        <button type="submit">提交</button>

    </form>
</div>