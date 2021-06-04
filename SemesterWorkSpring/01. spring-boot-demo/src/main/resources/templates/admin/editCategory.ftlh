<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Редактировать</title>
    <link rel="stylesheet" type="text/css" href="../css/categories.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script>
        function sendFile() {
            // данные для отправки
            let formData = new FormData();
            // забрал файл из input
            let files = ($('#file'))[0]['files'];
            // добавляю файл в formData
            [].forEach.call(files, function (file, i, files) {
                formData.append("file", file);
            });

            $.ajax({
                type: "POST",
                // url: "/files",
                url: "/adminEditCategories",
                data: formData,
                processData: false,
                contentType: false
            })
                .done(function (response) {
                    let fileUrl = 'http://localhost:8080/files/' + response;
                    $('#photo').append('<img src = "' + fileUrl + '"/>');
                })
                .fail(function () {
                    alert('Error')
                });
        }
    </script>
</head>
<body>
<header>
    <ul class="menu-main">
        <li><a href="/">На сайт</a> </li>
        <li><a href="/admin">Все категории</a> </li>
    </ul>
</header>

<div class="container">
    <h1>Админ - Панель. Редактировать категорию ${category.getName()}</h1>
    <form action="/adminEditCategories/${category.getId()}" enctype="multipart/form-data" method="post">
        <input type="text" name="name" value="${category.getName()}" required>
        <input type="hidden" name="imageId" value="${category.getImageName()}"/>
        <input type="file" multiple='multiple' id="file" name="file" placeholder="Имя файла..."/>
        <input type="submit" onclick="sendFile()">
    </form>
</div>
</body>
</html>