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

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
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
                <li class="active"><a href="/">Home</a></li>
                <li><a href="/profile"><sec:authentication property="principal.username" /></a></li>
                <li><a href="/logout">Exit</a></li>
            </ul>
        </sec:authorize>
        <h2 class="text-muted" style="display:inline-block;color:#212121;">${pageInfo.title} - Login</h2>
    </div>

    <div class="jumbotron" style="margin-top: 20px;">
    <p class="lead">
    <sec:authorize access="isAuthenticated()">
    <p>Welcome, <sec:authentication property="principal.username" />!</p>
    <p><a class="btn btn-lg btn-danger" href="logout" role="button">Выйти</a></p>
    </sec:authorize>
    <p><a href="profile" role="button" class="btn btn-primary btn-lg">Let's go!</a></p>
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
