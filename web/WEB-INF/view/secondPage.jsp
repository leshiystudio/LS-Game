<%--
  Created by IntelliJ IDEA.
  User: LS
  Date: 15.04.2016
  Time: 19:25
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
<div class="container">

    <div class="jumbotron" style="margin-top: 20px;">
        <h2>ONLY ADMIN</h2>
        Введенное имя: ${userJSP.username};
        <br/>
        Введенный пароль: ${userJSP.password};
        <br/>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="/js/bootstrap.js"></script>
</body>
</html>