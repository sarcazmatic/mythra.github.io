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

        .summary-box {
            display: grid;
            box-sizing: content-box;
            width: 65%;
            grid-template-columns: 33% 66%;
            border: 2px solid #444;
            border-radius: 10px;
            padding: 10px;
            margin-bottom: 1%;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            background-color: #222;
        }

        .summary-box .character-box {
            display: block;
            box-sizing: border-box;
            width: auto;
            padding: 10px;
            margin-bottom: 1%;
            border-right: 1px solid #444;
        }

        .summary-box .atrb-container {
            display: block;
            justify-items: center;
            box-sizing: border-box;
            width: auto;
            padding: 10px;
            margin-bottom: 1%;
            border: none;
        }

        .summary-box .atrb-container .atrb-collumn {
            text-align: center;
            display: grid;
            grid-template-columns: 50% 50%;
            box-sizing: border-box;
            width: auto;
            padding: 10px;
        }

        .summary-box .atrb-container .atrb-collumn .atrbs {
            box-sizing: border-box;
            width: auto;
            padding: 10px;
        }

        .summary-box .atrb-container .atrb-collumn .input-field {
            padding: 8px;
        }

        .summary-box .atrb-container .atrb-collumn .input-field label {
            font-weight: bold;
        }

        .summary-box .atrb-container .atrb-collumn .input-field input {
            width: 75%;
            padding: 8px;
            margin-bottom: 2%;
            border: 1px solid #444;
            border-radius: 0.25rem;
            background-color: #666;
            color: #fff;
        }

        .summary-box .atrb-container .submit-button {
            margin-top: 5%;
            position: relative;
            left: 50%;
            transform: translateX(-50%);
            width: 75%;
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

        .summary-box .atrb-container .submit-button:hover {
            background-color: #444;
        }

        .summary-box .atrb-container .atrb-collumn .input-field .messageBox {
            background-color: #444;
        }

    </style>
</head>

<body>
<div class="summary-box">
    <div class="character-box">
        <h2>Твой персонаж</h2>
        <br>
        <h3>Имя: ${charName}</h3>
        <br>
        <h3>Раса: ${charRace}</h3>
        <br>
        <h3>Класс: ${charClass}</h3>
        <br>
    </div>
    <form id="atrb-container" class="atrb-container">
        <h2>Выберем характеристики</h2>
        <div class="atrb-collumn">
            <div class="atrbs">
                <div class="input-field">
                    <label>Сила: ${strength}</label>
                    <br>
                    <input class="messageBox" type="checkbox" id="strength1" name="attrs" value="strength">
                    <input class="messageBox" type="checkbox" id="strength2" name="attrs" value="strength">
                </div>
                <div class="input-field">
                    <label>Ловкость: ${dexterity}</label>
                    <br>
                    <input class="messageBox" type="checkbox" id="dexterity1" name="attrs" value="dexterity">
                    <input class="messageBox" type="checkbox" id="dexterity2" name="attrs" value="dexterity">
                </div>
                <div class="input-field">
                    <label>Телосложение: ${constitution}</label>
                    <br>
                    <input class="messageBox" type="checkbox" id="constitution1" name="attrs" value="constitution">
                    <input class="messageBox" type="checkbox" id="constitution2" name="attrs" value="constitution">
                </div>
            </div>
            <div class="atrbs">
                <div class="input-field">
                    <label>Интеллект: ${intelligence}</label>
                    <br>
                    <input class="messageBox" type="checkbox" id="intelligence1" name="attrs" value="intelligence">
                    <input class="messageBox" type="checkbox" id="intelligence2" name="attrs" value="intelligence">

                </div>
                <div class="input-field">
                    <label>Мудрость: ${wisdom}</label>
                    <br>
                    <input class="messageBox" type="checkbox" id="wisdom1" name="attrs" value="wisdom">
                    <input class="messageBox" type="checkbox" id="wisdom2" name="attrs" value="wisdom">
                </div>
                <div class="input-field">
                    <label>Харизма: ${charisma}</label>
                    <br>
                    <input class="messageBox" type="checkbox" id="charisma1" name="attrs" value="charisma">
                    <input class="messageBox" type="checkbox" id="charisma2" name="attrs" value="charisma">
                </div>
            </div>
        </div>
        <button type="submit" class="submit-button" onclick="raiseAttributes()">Дальше</button>
        <p id="char-name" hidden>${charName}</p>
        <p id="char-id" hidden>${charId}</p>
        <input type="text" id="charRace" value="${charRace}" hidden>
        <input type="text" id="charClass" value="${charClass}" hidden>
        <input type="number" name="strength" value="${strength}" hidden>
        <input type="number" name="dexterity" value="${dexterity}" hidden>
        <input type="number" name="constitution" value="${constitution}" hidden>
        <input type="number" name="intelligence" value="${intelligence}" hidden>
        <input type="number" name="wisdom" value="${wisdom}" hidden>
        <input type="number" name="charisma" value="${charisma}" hidden>
    </form>
</div>
</div>

<script>
    var charName = document.getElementById("char-name").innerText;
    var charId = document.getElementById("char-id").innerText;

    function raiseAttributes() {
        let inputElements = document.getElementsByClassName('messageBox')
        var attrsArray = [];
        for (var i = 0; i < inputElements.length; ++i) {
            if (inputElements[i].checked) {
                checkedValue = inputElements[i].value;
                attrsArray.push(checkedValue)
            }
        }
        if (attrsArray.length != 2) {
            alert("Либо одну хар-ку на два очка, либо две на одно.")
            return;
        }
        var uriText = "/api/updateAttributes";
        var ourRequest = new XMLHttpRequest();
        ourRequest.open('PUT', uriText);
        ourRequest.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
        const newBody = JSON.stringify({
            charId: charId,
            charName: charName,
            attrsArray: attrsArray
        });
        ourRequest.send(newBody);
        document.getElementById('atrb-container').method = "get";
        document.getElementById('atrb-container').action = "charsheet";

    }
</script>

</body>
</html>