<%--
  Created by IntelliJ IDEA.
  User: joonheejeong
  Date: 2023/05/17
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <title>To-Do List</title>
</head>
<body>
  <h1>Todo List</h1>
  <h2>${appName}</h2>
  <h2>${loginInfo}</h2>
  <h3>${loginInfo.name}</h3>
  <ul>
  <c:forEach var="dto" items="${todoList}">
    <li>
      <a href="/todo/read?tno=${dto.tno}">${dto.tno}</a>
      <span> ${dto.title} ${dto.dueDate} ${dto.finished ? "DONE" : "NOT YET"}</span>
    </li>
  </c:forEach>
  </ul>
  <form action="/logout" method="get">
    <button type="submit">LOGOUT</button>
  </form>
</body>
</html>
