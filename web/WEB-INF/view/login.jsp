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
        <ul class="nav nav-tabs">
            <li class="active"><a href="#login-form" data-toggle="tab">Вход</a></li>
            <li><a href="#registration-form" data-toggle="tab">Регистрация</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="login-form">
            <form action="/j_spring_security_check" method="post">
                <h2 class="form-signin-heading">Авторизация</h2>
                <div class="input-group">
                    <span class="input-group-addon">@</span>
                    <input type="text" class="form-control" name="j_username" placeholder="Username" required autofocus value="">
                </div>
                <div class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                    <input type="password" class="form-control" name="j_password" placeholder="Password" required value="">
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
            </form>
            </div>
            <div class="tab-pane" id="registration-form">
                <form  method="post">
                    <h2 class="form-signin-heading">Регистрация</h2>
                    <div class="input-group">
                        <span class="input-group-addon">@</span>
                        <input type="text" class="form-control" name="username" placeholder="Username" required autofocus value="">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                        <input type="password" class="form-control" name="password" placeholder="Password" required value="">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                        <input type="password" class="form-control" name="password-retry" placeholder="Password retry" required value="">
                    </div>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Зарегистрироваться</button>
                </form>
            </div>
        </div>
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
<script type="application/javascript">


    function regQuery(username,password){
        var user = JSON.stringify({
            "username":username,
            "password":password
        })

        $.ajax({
            url:"/auth/reg",
            contentType:"application/json",
            type:"POST",
            data:user,
            complete:function(indata){
                data=indata.responseJSON;
                console.log(data);
                if(data.username==null)
                {
                    alert("Пользователь с таким логином уже существует!");
                    return false;
                }else{
                    alert("Пользователь с логином "+data.username+" успешно создан!");
                    return true;
                }

            }
        })
    }

    function checkFormReg() {
        var $username = $("input.form-control[name='username']");
        var $pass = $("input.form-control[name='password']");
        var $pass2 = $("input.form-control[name='password-retry']");
        var msg = "";
        var error = false;

        if ($pass.val() != $pass2.val()) {
            msg += "Пароли не совпадают!\n";
            error = true;
        }

        if (error) {
            alert(msg);
            return false;
        } else {
            return regQuery($username.val(), $pass.val());
        }
    }

    function existUser(username){
        var res=false;
        $.ajax({
            url:"/auth/existUser",
            dataType:"JSON",
            type:"POST",
            data:{username:username},
            complete:function(inputData){
                var data=inputData.responseJSON;
                if(data.exist){
                    console.log(data.exist);
                    alert("Пользователь с таким логином уже существует!");
                    return true;
                }
            }
        });
    }


    $(function(){
        var formReg = $("#registration-form form");
        formReg.submit(function(e){
            if(checkFormReg()){
                $('a[href="#login-form"]').tab('show');
            }else{

            };
            return false;
        });
    });
</script>
</body>
</html>