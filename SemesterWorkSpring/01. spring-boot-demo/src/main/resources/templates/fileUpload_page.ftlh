<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="/js/jquery.js"></script>
</head>
<body>


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
                url: "/files",
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

    <div>
        <input type="file" multiple='multiple' id="file" name="file" placeholder="Имя файла..."/>
        <button onclick="sendFile()">
            Загрузить файл
        </button>
        <input type="hidden" id="file_hidden">
        <div class="filename"></div>
    </div>
    <div id="photo">
    </div>


</body>
</html>