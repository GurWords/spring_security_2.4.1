<%--
  Created by IntelliJ IDEA.
  User: GEORGY
  Date: 09.06.2020
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <li>${user.id}</li>
    <li>${user.name}</li>
    <li>${user.age}</li>
    <li>${user.password}</li>
    <li>${user.role.role}</li>
</ul>
<form action="/logout" method="get">
    <input type="submit" value="Logout" />
</form>
</body>
</html>
