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
        <div class="profileBlock" style="margin:5px; overflow: hidden; text-align: center; width: 360px; height: 410px; background-image: url(&quot;/img/character.gif&quot;); background-position: 41% 96%;"></div>
        <div style="display: inline-block; vertical-align: top; width: 60%;">
            <div class="profileBlock">
                <p class="mylabel">Жизни:</p>
                <div class="progress">
                    <div id="health" class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">0</div>
                </div>
                <p class="mylabel">Броня:</p>
                <div class="progress">
                    <div id="armor" class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">0</div>
                </div>
            </div>
            <div class="form-characteristics">
                <div class="btn" >Очки способностей: <span id="skillPoints"
                                                           rel="popover" class="btn btn-default"
                                                           data-container="body"
                                                           data-toggle="popover"
                                                           data-placement="top"
                                                           data-content="Очки способностей закончились!" >10</span></div>
                <div class="input-group">
                    <span class="input-group-addon left-label">Ловкость:</span>
                    <div class="input-group-addon progress input-progress">
                        <div id="dexterity" class="progress-bar  progress-bar-warning" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="${allSkillPoints}" style="width: 0%;">0</div>
                    </div>
                    <input id="inputDexterity" min="0" type="number" style="visibility: hidden" class="form-control" placeholder="" aria-describedby="dexterity">
                </div>
                <div class="input-group">
                    <span class="input-group-addon left-label">Скорость:</span>
                    <div class="input-group-addon progress input-progress">
                        <div id="speed" class="progress-bar  progress-bar-warning" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="${allSkillPoints}" style="width: 0%;">0</div>
                    </div>
                    <input id="inputSpeed" min="0" type="number" style="visibility: hidden" class="form-control" placeholder="" aria-describedby="speed">
                </div>
                <div class="input-group">
                    <span class="input-group-addon left-label">Сила:</span>
                    <div class="input-group-addon progress input-progress">
                        <div id="power" class="progress-bar  progress-bar-warning" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="${allSkillPoints}" style="width: 0%;">0</div>
                    </div>
                    <input id="inputPower" min="0" type="number" style="visibility: hidden" class="form-control" placeholder="" aria-describedby="power">
                </div>
                <button id="btn-edit" type="button" class="btn btn-default">Редактировать</button>
                <button id="btn-save" style="visibility: hidden" type="button" class="btn btn-success form-control">Сохранить</button>
            </div>

        </div>
        <hr>
        <nav>
            <ul class="pager">
                <li class="previous"><a href="#"><span aria-hidden="true">&larr;</span> Назад</a></li>
                <li class="next"><a href="#">Вперед <span aria-hidden="true">&rarr;</span></a></li>
            </ul>
        </nav>

        <div id="enemy-viewer" class="row">
        </div>
        <div class="col-sm-6 col-md-3 cart hidden" id="pattern-prev-enemy">
            <div class="thumbnail">
                <img alt="100%x200" data-src="holder.js/100%x200" style="height: 200px; width: 100%; display: block;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMjQyIiBoZWlnaHQ9IjIwMCIgdmlld0JveD0iMCAwIDI0MiAyMDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzEwMCV4MjAwCkNyZWF0ZWQgd2l0aCBIb2xkZXIuanMgMi42LjAuCkxlYXJuIG1vcmUgYXQgaHR0cDovL2hvbGRlcmpzLmNvbQooYykgMjAxMi0yMDE1IEl2YW4gTWFsb3BpbnNreSAtIGh0dHA6Ly9pbXNreS5jbwotLT48ZGVmcz48c3R5bGUgdHlwZT0idGV4dC9jc3MiPjwhW0NEQVRBWyNob2xkZXJfMTU0YTRiNjYzYWUgdGV4dCB7IGZpbGw6I0FBQUFBQTtmb250LXdlaWdodDpib2xkO2ZvbnQtZmFtaWx5OkFyaWFsLCBIZWx2ZXRpY2EsIE9wZW4gU2Fucywgc2Fucy1zZXJpZiwgbW9ub3NwYWNlO2ZvbnQtc2l6ZToxMnB0IH0gXV0+PC9zdHlsZT48L2RlZnM+PGcgaWQ9ImhvbGRlcl8xNTRhNGI2NjNhZSI+PHJlY3Qgd2lkdGg9IjI0MiIgaGVpZ2h0PSIyMDAiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSI4OCIgeT0iMTA1LjciPjI0MngyMDA8L3RleHQ+PC9nPjwvZz48L3N2Zz4=" data-holder-rendered="true">
                <div class="caption" >
                    <div class="list-group">
                        <div class="list-group-item">
                            <span class="username">Username</span>
                            <span title="Количество поражений" class="badge badge-warning loss">0</span>
                            <span title="Количество побед" class="badge badge-success win">0</span>
                        </div>
                    </div>
                    <div class="list-group">
                        <div title="Жизни" class="progress health">
                            <div class="progress-bar  progress-bar-danger" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
                            </div>
                        </div>
                        <div title="Броня" class="progress armor">
                            <div class="progress-bar  progress-bar-info" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>
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
                    <p><a role="button" class="btn btn-danger" href="#">Атаковать</a></p>
                </div>
            </div>
        </div>
        <nav>
            <ul class="pager">
                <li class="previous"><a href="#"><span aria-hidden="true">&larr;</span> Назад</a></li>
                <li class="next"><a href="#">Вперед <span aria-hidden="true">&rarr;</span></a></li>
            </ul>
        </nav>
    </div>
    <div class="footer">
        <p>${pageInfo.footer}</p>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="/js/bootstrap.js"></script>
<script src="/js/gameTools.js"></script>
<script type="application/javascript">
    $(function(){
        lsGame.init();

        $("#btn-edit").bind("click",function(){ lsGame.toggleOnOffEditCharacter(); });
        $("#btn-save").bind("click",function(){
            if(lsGame.saveCh()){
                lsGame.toggleOnOffEditCharacter();
            }else{

            }
        });

        lsGame.getPageEnemy(lsGame.currPage);

        var $nextPageBtns = $(".next > a");
        var $backPageBtns = $(".previous > a");
        $nextPageBtns.click(function(e){
            var page = $(this).attr("page");
            lsGame.getPageEnemy(page);
            return false;
        });
        $backPageBtns.click(function(e){
            var page = $(this).attr("page");
            lsGame.getPageEnemy(page);
            return false;
        });

    });
</script>
</body>
</html>