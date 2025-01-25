<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Simple Form</title>
</head>
<body>

<h2 id="login">${login}</h2>
<output id="output" name="output">Asdasd</output>
<input type="text" id="name" name="name" value="${inputField}" required>
<button id="btn" type="submit">Submit</button>

<script>
    var login = document.getElementById("login");
    var output = document.getElementById("output");
    let name = document.getElementById("name");
    var inputValue;
    var btn = document.getElementById("btn");

    btn.addEventListener("click", function () {
        inputValue = name.value;
        var ourRequest = new XMLHttpRequest();
        ourRequest.open('POST', '/json');
        ourRequest.setRequestHeader("Content-Type", "application/json; charset=UTF-8")
        const body = JSON.stringify({
            name: login.innerText,
            email: output.innerText,
            password: inputValue
        });
        ourRequest.onload = function () {
            var ourData = JSON.parse(ourRequest.responseText);
            console.log(ourData)
            renderHTML(ourData);
        }
        ourRequest.send(body);
    })

    function renderHTML(data) {
        login.innerText = inputValue;
        output.innerText = data.name;
        btn.innerText = "asdsa";
    }

</script>

<form action="http://localhost:8080/file/asd/asd/upload" method="post" enctype="multipart/form-data">
    <input name="file" type="file" multiple>
    <button type="submit">Upload</button>
</form>

</body>
</html>