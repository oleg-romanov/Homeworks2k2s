<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="/js/jquery.js"></script>
    <title>Document</title>
</head>
<body>
    <header>
        <ul class="menu-main">
            <li><a href="/">Главная</a> </li>
            <li><a href="/categories" class="current">Каталог</a></li>
            <li><a href="/about">About</a></li>
            <li><a href="/profile">Профиль</a></li>
        </ul>
    </header>

    <div class="container">
        <h1>Категории</h1>
        <div class="categories">
            <#list categories as categories>
                <div class="category">
                    <div class="category-photo">
                        <img style="width: 200px" src="/files/${categories.getImageName()}" alt="img"/>
                    </div>
                    <a class="categoryName" href="/categories/${categories.getId()}">${categories.getName()}</a>
                </div>
            </#list>
        </div>
    </div>
</body>
</html>