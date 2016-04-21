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
<div class="jumbotron" style="margin-top: 20px;">
    <h1>LS</h1>
    <p class="lead">
    </p>
    <sec:authorize access="!isAuthenticated()">
        <form action="/j_spring_security_check" method="post">
            <h2 class="form-signin-heading">Please sign in</h2>
            <input type="text" class="form-control" name="j_username" placeholder="username" required autofocus value="username">
            <input type="password" class="form-control" name="j_password" placeholder="Password" required value="1234">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
        </form>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <p>Ваш логин: <sec:authentication property="principal.username" /></p>
        <p><a class="btn btn-lg btn-danger" href="logout" role="button">Выйти</a></p>

    </sec:authorize>
</div>

</body>
</html>
