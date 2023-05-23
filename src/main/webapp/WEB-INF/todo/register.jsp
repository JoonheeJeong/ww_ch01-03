<%--
  Created by IntelliJ IDEA.
  User: joonheejeong
  Date: 2023/05/17
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>To-Do Register</title>
</head>
<body>
    <form method="post" action="/todo/register">
        <input name="title" type="text" placeholder="INSERT TITLE"><br/>
        <input name="dueDate" type="date"><br/>
        <button type="reset">RESET</button>
        <button type="submit">REGISTER</button>
    </form>
</body>
</html>
