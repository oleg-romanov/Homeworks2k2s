<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Регистрация</title>
    <link rel="stylesheet" type="text/css" href="/css/signUp.css">
</head>
<body>
    <div class="container">
        <form action="/signUp" method="post">
            <div class="inputs">
                <span class="heading">РЕГИСТРАЦИЯ</span>
                <input name="firstName" placeholder="Name" class="field" required aria-placeholder="Укажите ваше имя">
                <input name="lastName" placeholder="Surname" class="field" required aria-placeholder="Укажите вашу фамилию">
                <input name="email" type="email" class="field" placeholder="E-mail" required aria-placeholder="Введите вашу почту">
                <input name="password" type="password" class="field" placeholder="Password" required aria-placeholder="Укажите ваш пароль">
<#--                <button type="submit" class="btn-right" value="SignIn">РЕГИСТРАЦИЯ</button>-->
<#--                <a href="/signIn" class="btn-left">ВОЙТИ</a>-->
                <input type="submit" value="SIGN UP" class="submit">
            <div>
        </form>
    </div>
</body>
</html>

