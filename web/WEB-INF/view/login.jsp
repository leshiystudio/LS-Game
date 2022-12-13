<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must- revalidate">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>${pageInfo.title}</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="header">
        <sec:authorize access="isAuthenticated()">
        <ul class="nav nav-pills pull-right">
            <li><a href="/">Home</a></li>
            <li><a href="/profile"><sec:authentication property="principal.username" /></a></li>
            <li><a href="/logout">Exit</a></li>
        </ul>
        </sec:authorize>
        <h2 class="text-muted" style="display:inline-block;color:#212121;">${pageInfo.title} - Login</h2>
    </div>
    <div class="jumbotron" style="margin-top: 20px;">
        <p class="lead">
        </p>
        <sec:authorize access="!isAuthenticated()">
            <form action="/j_spring_security_check" method="post">
                <h2 class="form-signin-heading">Please sign in</h2>
                <div class="input-group">
                    <span class="input-group-addon">@</span>
                    <input type="text" class="form-control" name="j_username" placeholder="Username" required autofocus value="admin">
                </div>
                <div class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                    <input type="password" class="form-control" name="j_password" placeholder="Password" required value="pass">
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
            </form>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <p>Ваш логин: <sec:authentication property="principal.username" /></p>
            <p><a class="btn btn-lg btn-danger" href="logout" role="button">Выйти</a></p>

        </sec:authorize>
    </div>

    <div class="footer">
        <p>${pageInfo.footer}</p>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="/js/bootstrap.js"></script>
</body>
</html>