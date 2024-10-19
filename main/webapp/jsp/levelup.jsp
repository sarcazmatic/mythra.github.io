<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Epic D&D Character Sheet</title>
    <link href="https://fonts.googleapis.com/css2?family=Philosopher&display=swap" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center;
            background-color: #0d0d0d;
            color: #fff;
            font-family: 'Philosopher', cursive;
            padding: 10px;
        }

        h1, h2, h3 {
            text-align: center;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.8);
        }

        .creation-box {
            box-sizing: content-box;
            text-align: center;
            align-items: center;
            width: 65%;
            border: 2px solid #444;
            border-radius: 10px;
            padding: 10px;
            margin-bottom: 1%;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            background-color: #222;
        }

        .creation-box .form-container {
            box-sizing: border-box;
            width: auto;
            padding: 10px;
            border-radius: 8px;
        }

        .one, .two, .three {
            display: none;
        }

        .creation-box .form-container .input-field {
            padding: 8px;
        }

        .creation-box .form-container .input-field label {
            font-weight: bold;
        }

        .creation-box .form-container .input-field input {
            width: 72%;
            padding: 0.7em 0.7em 0.7em 1em;
            border: 1px solid #444;
            border-radius: 0.25rem;
            background-color: #666;
            color: #fff;
        }

        .creation-box .form-container .input-field select {
            -moz-appearance: none; /* Firefox */
            -webkit-appearance: none; /* Safari and Chrome */
            appearance: none;
            position: center;
            width: 75%;
            font-size: 0.8em;
            padding: 0.7em 0.7em 0.7em 1em;
            background: url(https://parspng.com/wp-content/uploads/2021/11/arrowpng.parspng.com-13.png) 99% / 20% no-repeat #666;
            border: 1px solid #444;
            border-radius: 0.25rem;
            color: #fff;
            cursor: pointer;
        }

        .creation-box .form-container .submit-button {
            margin-top: 2%;
            width: 25%;
            font-size: 0.8em;
            padding: 0.7em 0.7em 0.7em 1em;
            font-family: 'Philosopher', cursive;
            background-color: #666;
            border: none;
            border-radius: 5px;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .creation-box .form-container .submit-button:hover {
            background-color: #444;
        }

    </style>
</head>
<body>
<div class="creation-box">
    <h2>Новый уровень!</h2>
    <div class="form-container">
        <form id="class-choice-form">
            <label>Выбери класс:</label>
            <div id="put-classes-here" class="input-field">
            </div>
            <br>
            <label>Мультикласс:</label>
            <div id="put-multiclasses-here" class="input-field">
            </div>
        </form>
        <output id="amount-of-classes" hidden>${size}</output>
    </div>
</div>

<script>
    var numOfClasses = document.getElementById("amount-of-classes").innerText;
    let arrayWithClassesAndLevels = ${array};


    function loadLine() {
        for (let i = 0; i < numOfClasses; i = i + 2) {
            var newDiv2 = document.createElement("button");
            newDiv2.className = "submit-button";
            let classText = arrayWithClassesAndLevels[i];
            let levelText = arrayWithClassesAndLevels[i + 1];
            let levelUpText = parseInt(levelText) + 1;
            newDiv2.id = classText;
            newDiv2.innerText = classText + " : " + levelText + " -> " + levelUpText;
            newDiv2.setAttribute("onclick", "setValue(" + i + ")");
            document.getElementById('put-classes-here').appendChild(newDiv2);
            console.log(newDiv2.onclick);
            console.log(classText);
        }
    }

    function setValue(i) {
        var popp = arrayWithClassesAndLevels[i];
        var ourRequest = new XMLHttpRequest();
        ourRequest.open('PUT', 'charsheet/updCharacterLevel');
        ourRequest.setRequestHeader("Content-Type", "application/json; charset=UTF-8")
        const newBody = JSON.stringify({
            "charClassToLevelUp": popp
        });
        console.log(newBody);
        ourRequest.send(newBody);
        document.getElementById('class-choice-form').method = "get";
        document.getElementById('class-choice-form').action = "charsheet";
    }

    window.onload = loadLine();

</script>

</body>

</html>