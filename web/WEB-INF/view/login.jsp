<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Spring Security</title>
</head>

<body>

<div class="container">

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

    <div class="footer">
        <p></p>
    </div>
</div>
</body>
</html>