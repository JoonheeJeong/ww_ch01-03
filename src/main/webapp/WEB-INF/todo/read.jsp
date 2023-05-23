<%--
  Created by IntelliJ IDEA.
  User: joonheejeong
  Date: 2023/05/17
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>To-Do Read</title>
</head>
<body>
    <form>
        <input type="text" name="tno" value="${dto.tno}" readonly><br/>
        <input type="text" name="title" value="${dto.title}" readonly><br/>
        <input type="checkbox" name="isFinished" ${dto.finished ? "checked" : ""} readonly><br/>
        <input type="date" name="dueDate" value="${dto.dueDate}" readonly><br/>
    </form>
    <a href="/todo/modify?tno=${dto.tno}">Modify/Remove</a>
    <a href="/todo/list">List</a>
</body>
</html>
