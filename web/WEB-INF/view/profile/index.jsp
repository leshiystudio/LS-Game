<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: LS
  Date: 17.04.2016
  Time: 4:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${pageInfo.title}</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<div id="content" class="container">
    <div class="header">
        <sec:authorize access="isAuthenticated()" >
            <ul class="nav nav-pills pull-right">
                <li><a href="/">Home</a></li>
                <li class="active"><a href="/profile"><sec:authentication property="principal.username" /></a></li>
                <li><a href="/logout">Exit</a></li>
            </ul>
        </sec:authorize>
        <h2 class="text-muted" style="display:inline-block;color:#212121;">${pageInfo.title} - Login</h2>
    </div>

    <div class="jumbotron">
        <h3></h3>
        <div style="overflow: hidden; text-align: center; width: 360px; height: 410px; background-image: url(&quot;/img/character.gif&quot;); background-position: 41% 96%;"></div>
        <div class="btn-group">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                Fight with:
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a href="#">Dropdown ссылка</a></li>
                <li><a href="#">Dropdown ссылка</a></li>
            </ul>
        </div>
    </div>
    <div class="footer">
        <p>${pageInfo.footer}</p>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="/js/bootstrap.js"></script>
</body>
</html>