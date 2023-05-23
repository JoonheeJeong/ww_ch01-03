<%--
  Created by IntelliJ IDEA.
  User: joonheejeong
  Date: 2023/05/22
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:if test="${param.result == 'error'}">
    <h1>로그인 에러</h1>
</c:if>
    <form action="/login" method="post">
        <div>
            <input name="id" type="text" placeholder="INSERT ID">
        </div>
        <div>
            <input name="pw" type="text" placeholder="INSERT PASSWORD">
        </div>
        <div>
            <input name="auto" type="checkbox">
        </div>
        <div>
            <button type="submit">LOGIN</button>
        </div>
    </form>
</body>
</html>
