<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: GEORGY
  Date: 19.05.2020
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/admin/insert" method="post">
    Name:
    <input name="name">
    Password:
    <input name="password">
    Age:
    <input name="age">
    Role:
    <input name="role">
    <input type="submit">
</form>
<c:forEach var="i" items="${userList}">
    <table>
        <tr>
                ${i.id}
        </tr>
        <tr>
            Name:
                ${i.name}
        </tr>
        <tr>
            Age:
                ${i.age}
        </tr>
        <tr>
            Password:
                ${i.password}
        </tr>
        <tr>
            <c:forEach var="w" items="${i.roleSet}">
                Role:
                ${w.role}
            </c:forEach>
        </tr>
        <tr>
            <form action="/admin/delete" method="post">
                <input name="idDelete" type="hidden" value="${i.id}">
                <button type="submit">Delete</button>
            </form>
        </tr>
        <tr>
            <form action="/admin/update" method="get">
                <input name="idUpdate" type="hidden" value="${i.id}">
                <button type="submit">Update</button>
            </form>
        </tr>
    </table>

</c:forEach>
<form action="/logout" method="get">
    <input type="submit" value="Logout" />
</form>
</body>
</html>
