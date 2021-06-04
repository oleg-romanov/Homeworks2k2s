<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="/js/main.js"></script>
    <script src="/js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/products.css">
    <title>Document</title>
    <script>
        const urlParams = new URLSearchParams(window.location.search);
        var myParam = urlParams.get('q');
        if (myParam != null) {
            myParam = '&q=' + myParam
        } else {
            myParam = ''
        }
        let path = 'http://localhost:8080/categories/${products[0].getCategoryId()}/productsList/search?page=0&size=2' + myParam
    </script>
</head>
<body onload="getProducts(path)">
    <header>
        <ul class="menu-main">
            <li><a href="/">Главная</a> </li>
            <li><a href="/categories" class="current">Каталог</a></li>
            <li><a href="/about">About</a></li>
            <li><a href="/profile">Профиль</a></li>
        </ul>
    </header>

    <h1>Welcome to the products</h1>
    <div class="search">
    <form>
        <input name="q" type="text" id="input" placeholder="Найти товар...">
        <button type="submit" onclick="getProducts('http://localhost:8080/categories/${products[0].getCategoryId()}/productsList/search?page=0&size=2&q=' + document.getElementById('input').value)">Найти</button>
    </form>
    </div>

    <div class="container">
        <h1>Товары</h1>
        <div class="categories"></div>

        <ul class="pagination-widget__pages">
            <li class="pagination-widget__page" data-role="pagination-page" data-page-number="1">
                <a class="pagination-widget__pagr-link"
                   onclick="getProducts('http://localhost:8080/categories/${products[0].getCategoryId()}/productsList/search?page=0&size=2' + myParam)">1</a>
            </li>
            <li class="pagination-widget__page" data-role="pagination-page" data-page-number="2">
                <a class="pagination-widget__pagr-link"
                   onclick="getProducts('http://localhost:8080/categories/${products[0].getCategoryId()}/productsList/search?page=1&size=2' + myParam)">2</a>
            </li>
            <li class="pagination-widget__page" data-role="pagination-page" data-page-number="3">
                <a class="pagination-widget__pagr-link"
                   onclick="getProducts('http://localhost:8080/categories/${products[0].getCategoryId()}/productsList/search?page=2&size=2' + myParam)">3</a>
            </li>
        </ul>
    </div>

</body>
</html>