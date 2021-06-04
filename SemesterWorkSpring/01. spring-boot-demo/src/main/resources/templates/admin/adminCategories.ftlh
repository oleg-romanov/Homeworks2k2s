<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Админ категории</title>
    <link rel="stylesheet" type="text/css" href="../css/categories.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script src="../js/jquery.js"></script>
    <script>
        function sendDeleteCategoryRequest(name, id) {
            if(confirm("Удалить категорию " + name + "?")) {
                $.ajax({
                    url: "/adminCategories/" + id,
                    method: "POST"
                }).done(function() {
                    let categoryTag = $('#category' + id);
                    categoryTag.hide(300, function() {
                        categoryTag.remove();
                    });
                });
            }
        }
    </script>
</head>
<body>
<header>
    <ul class="menu-main">
        <li><a href="/">На сайт</a> </li>
        <li><a href="/addCategories">Добавить категорию</a> </li>
    </ul>
</header>

<div class="container">
    <h1>Админ - Панель. Категории</h1>
    <div class="categories">
        <#list categories as categories>
            <div id="category${categories.getId()}" class="category">
                <div class="category-photo">
                    <img style="width: 200px" src="/files/${categories.getImageName()}" alt="img"/>
                </div>
                <a class="categoryName" href="/adminCategories/${categories.getId()}">${categories.getName()}</a>
                <a href="/adminEditCategories/${categories.getId()}">EDIT</a>
                <button onclick="sendDeleteCategoryRequest('${categories.getName()}', '${categories.getId()}')" style="margin: 40px; color: #b03333">DELETE</button>
            </div>
        </#list>
    </div>
</div>
</body>
</html>