<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Профиль</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script src="/js/jquery.js"></script>
</head>
<body>

<header>
    <ul class="menu-main">
        <li><a href="/">Главная</a> </li>
        <li><a href="http://localhost/html/categories.html">Каталог</a></li>
        <li><a href="/about">About</a></li>
        <li><a href="/profile" class="current">Профиль</a></li>
    </ul>
</header>

<script>
    function sendUser() {
        let email = $('#newEmail').val();
        let firstName = $('#newFirstName').val();
        let lastName = $('#newLastName').val();

        let data = {
            "email": email,
            "firstName": firstName,
            "lastName": lastName
        };

        let request = $.ajax({
            type: "PUT", // метод запроса
            url: "/profile/${userDto.id}", //урл запроса
            data: JSON.stringify(data), // данные для отправки из JSON-объекта превращаем в JSON-строку
            //тип данных, который мы отпралвяем
            dataType: "json",
            contentType: "application/json"
        });

        request.done(function () {
            if (email !== '') {
                $('#emailLabel').html(email);
                $('#newEmail').val('');
            }
            if (firstName !== '') {
                $('#nameLabel').html(firstName);
            }
            if (lastName !== '') {
                $('#surnameLabel').html(lastName);
            }
        });

        request.fail(function () {
            alert("Fail")
        });
    }

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
            url: "/${userDto.id}/files",
            data: formData,
            processData: false,
            contentType: false
        })
            .done(function (response) {
                let fileUrl = 'http://localhost:8080/files/' + response
                $('#photo').attr('src', fileUrl)
            })
            .fail(function () {
                alert('Error')
            });
    }


</script>



<div id="settings" class="container">
    <h1>Настройки</h1>

    <div>
        <img id="photo" style="width: 400px; border-radius: 40px; margin: 40px 0;" alt="avatar" src="/files/${userDto.imageName}"/>
        <input type="file" multiple='multiple' id="file" name="file" placeholder="Имя файла..."/>
        <button onclick="sendFile()">
            Загрузить файл
        </button>
        <input type="hidden" id="file_hidden">
        <div class="filename"></div>
    </div>

    <div class="email">
        <h3>Ваш текущий адрес электронной почты:</h3>
        <h4 id="emailLabel">${userDto.email}</h4>
        <h3>Если вы хотите изменить электронную почту, укажите в поле ниже новый адрес электронной почты:</h3>
        <input type="text" id="newEmail" placeholder="Новый адрес электронной почты">
    </div>

    <div class="fio">
        <h3>Ваше имя</h3>
        <h4 id="nameLabel">${userDto.firstName}</h4>
        <h3>Если вы хотите изменить имя, введите новое имя в поле ниже:</h3>
        <input type="text" id="newFirstName" placeholder="Обновленное имя">

        <h3>Ваша фамилия</h3>
        <h4 id="surnameLabel">${userDto.lastName}</h4>
        <h3>Если вы хотите изменить имя, введите новое имя в поле ниже:</h3>
        <input type="text" id="newLastName" placeholder="Обновленная фамилия">
    </div>
    <button onclick="sendUser()">Изменить</button>
</div>
</body>
</html>