<%--
  Created by IntelliJ IDEA.
  User: LS
  Date: 17.04.2016
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

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
        <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.username" /></p>
        </sec:authorize>
        <h1>ACCESS DENIED!</h1>
        </p>
    </div>
    <div class="footer">
        <p>${pageInfo.footer}</p>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="/js/bootstrap.js"></script>
</body>
</html>
