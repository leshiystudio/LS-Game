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
                <li class="active"><a href="/">Home</a></li>
                <li><a href="/profile"><sec:authentication property="principal.username" /></a></li>
                <li><a href="/logout">Exit</a></li>
            </ul>
        </sec:authorize>
        <h2 class="text-muted" style="display:inline-block;color:#212121;">${pageInfo.title} - Login</h2>
    </div>

    <div class="jumbotron" style="margin-top: 20px;">
        <p class="lead"></p>
        <sec:authorize access="isAuthenticated()">
        <p>Welcome, <sec:authentication property="principal.username" />!</p>
        <p><a class="btn btn-lg btn-danger" href="logout" role="button">Выйти</a></p>
        </sec:authorize>
        <p><a href="profile" role="button" class="btn btn-primary btn-lg">Начать!</a></p>
        <hr>
        <h3> История поединков:</h3>
        <div id="fight-history" class="thumbnail fight-history hidden">
            <div class="row thumbnail tittle">
                <div><span class="date">time: </span> Был проведен бой между: <span class="char">char1</span> и <span class="char">char2</span></div></div>
            <div class="row chars">
                <div class="col-sm-6 col-md-4 cart">
                    <div class="thumbnail">
                        <div class="caption">
                            <div class="list-group">
                                <div class="list-group-item">
                                    <span class="username">Mouse</span>
                                    <span title="Количество поражений" class="badge badge-warning loss">0</span>
                                    <span title="Количество побед" class="badge badge-success win">0</span>
                                </div>
                            </div>
                            <div class="list-group">
                                <div title="Жизни" class="progress health">
                                    <div class="progress-bar  progress-bar-danger" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
                                    </div>
                                </div>
                                <div title="Броня" class="progress armor">
                                    <div class="progress-bar  progress-bar-info" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 100%;"></div>
                                </div>
                            </div>
                            <div class="input-group-addon  form-characteristics">
                                <div title="Ловкость" class="progress dexterity">
                                    <div style="width: 0%;" aria-valuemax="10" aria-valuemin="0" aria-valuenow="0" role="progressbar" class="progress-bar  progress-bar-warning"></div>
                                </div>
                                <div title="Скорость" class="progress speed">
                                    <div style="width: 0%;" aria-valuemax="10" aria-valuemin="0" aria-valuenow="0" role="progressbar" class="progress-bar  progress-bar-warning"></div>
                                </div>
                                <div title="Сила" class="progress power">
                                    <div style="width: 0%;" aria-valuemax="10" aria-valuemin="0" aria-valuenow="0" role="progressbar" class="progress-bar  progress-bar-warning"></div>
                                </div>
                            </div>
                            <br>
                        </div>
                    </div>
                </div><div class="col-sm-6 col-md-4 ">
                <div class="caption">
                    <br>
                    <p>VS</p>
                </div>
            </div>
            <div class="col-sm-6 col-md-4 cart">
                <div class="thumbnail">
                    <div class="caption">
                        <div class="list-group">
                            <div class="list-group-item">
                                <span class="username">test</span>
                                <span class="badge badge-warning loss" title="Количество поражений">0</span>
                                <span class="badge badge-success win" title="Количество побед">0</span>
                            </div>
                        </div>
                        <div class="list-group">
                            <div class="progress health" title="Жизни">
                                <div style="width: 100%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="0" role="progressbar" class="progress-bar  progress-bar-danger">
                                </div>
                            </div>
                            <div class="progress armor" title="Броня">
                                <div style="width: 19%;" aria-valuemax="100" aria-valuemin="0" aria-valuenow="0" role="progressbar" class="progress-bar  progress-bar-info"></div>
                            </div>
                        </div>
                        <div class="input-group-addon  form-characteristics">
                            <div class="progress dexterity" title="Ловкость">
                                <div class="progress-bar  progress-bar-warning" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="10" style="width: 40%;"></div>
                            </div>
                            <div class="progress speed" title="Скорость">
                                <div class="progress-bar  progress-bar-warning" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="10" style="width: 20%;"></div>
                            </div>
                            <div class="progress power" title="Сила">
                                <div class="progress-bar  progress-bar-warning" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="10" style="width: 40%;"></div>
                            </div>
                        </div>
                        <br>
                    </div>
                </div>
            </div></div></div>
        <div id="history">
        </div>
    </div>
    <div class="footer">
        <p>${pageInfo.footer}</p>
    </div>
</div>
<script type="application/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="application/javascript" src="/js/bootstrap.js"></script>
<script type="application/javascript" src="/js/gameTools.js"></script>
<script type="application/javascript">
    $(function() {
        lsGame.getHistory();
    });
</script>
</body>
</html>
