<%--
  Created by IntelliJ IDEA.
  User: joonheejeong
  Date: 2023/05/19
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>To-Do Modify</title>
</head>
<body>
    <form id="form-modify" method="post" action="/todo/modify">
        <input name="tno" type="number" value="${dto.tno}" readonly><br/>
        <input name="title" type="text" value="${dto.title}" placeholder="INSERT TITLE"><br/>
        <input name="dueDate" type="date" value="${dto.dueDate}"><br/>
        <input name="finished" type="checkbox" ${dto.finished ? "checked" : ""}><br/>
        <button type="submit">Modify</button>
    </form>
    <br/>
    <form id="form-remove" method="post" action="/todo/remove">
        <input name="tno" type="hidden" value="${dto.tno}" readonly>
        <button type="submit">Remove</button>
    </form>
</body>
</html>
