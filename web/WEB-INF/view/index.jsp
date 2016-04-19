<%--
  Created by IntelliJ IDEA.
  User: LS
  Date: 15.04.2016
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<!-- обратите внимание на spring тэги -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
    <title>Index Page</title>
</head>
<body>

<div class="container">
    <div class="jumbotron" style="margin-top: 20px;">
        <h1>LS</h1>
        <p class="lead">
        </p>
        <sec:authorize access="!isAuthenticated()">
            <p><a class="btn btn-lg btn-success" href="login" role="button">Войти</a></p>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <p>Ваш логин: <sec:authentication property="principal.username" /></p>
            <p><a class="btn btn-lg btn-danger" href="logout" role="button">Выйти</a></p>

        </sec:authorize>
    </div>

    <div class="footer">
        <p></p>
    </div>
</div>

</body>
</html>
