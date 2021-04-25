<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign In</title>
    <link rel="stylesheet" type="text/css" href="/css/signUp.css">
</head>
<body>
<div class="container">
    <form action="/signIn" method="post">
        <div class="inputs">
            <span class="heading">ВХОД</span>
            <input name="email" type="email" class="field" placeholder="E-mail" required aria-placeholder="Введите вашу почту">
            <input name="password" type="password" class="field" placeholder="Password" required aria-placeholder="Укажите ваш пароль">
            <input type="submit" value="SIGN IN" class="submit">
            <a href="/signUp" class="btn-left">Sign Up</a>
            <div>
    </form>
</div>
</body>
</html>