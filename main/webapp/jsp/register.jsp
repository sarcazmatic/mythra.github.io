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
            margin-top: 10%;
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

        .creation-box .form-container .submit-button:hover {
            background-color: #444;
        }

    </style>
</head>
<body>
<div class="creation-box">
    <h2>Создадим твоего персонажа</h2>
    <div class="form-container">
        <form id="myForm" action="attributes" method="post">
            <label for="charName">Имя:</label>
            <div class="input-field">
                <input type="text" id="charName" name="charName">
                <br>
            </div>
            <label for="charClass">Класс:</label>
            <div class="input-field">
                <select id="charClass" name="charClass">
                    <option>Бард</option>
                    <option>Варвар</option>
                    <option>Воин</option>
                    <option>Волшебник</option>
                    <option>Друид</option>
                    <option>Колдун</option>
                    <option value="Кровавый-охотник">Кровавый охотник</option>
                    <option>Монах</option>
                    <option>Изобретатель</option>
                    <option>Паладин</option>
                    <option>Плут</option>
                    <option>Следопыт</option>
                    <option>Чародей</option>
                </select>
                <br>
            </div>
            <label for="charRace">Раса:</label>
            <div class="input-field">
                <select id="charRace" name="charRace" onchange="raceSelect()">
                    <option>Ааракокра</option>
                    <option>Аасимар</option>
                    <option value="Гит">Гит</option>
                    <option value="Гном">Гном</option>
                    <option>Голиаф</option>
                    <option value="Дварф">Дварф</option>
                    <option>Драконорожденный</option>
                    <option>Полуорк</option>
                    <option value="Полурослик">Полурослик</option>
                    <option>Полуэльф</option>
                    <option>Тифлинг</option>
                    <option>Человек</option>
                    <option value="Эльф">Эльф</option>
                </select>
                <br>
                <select id="githSubrace" name="charSubrace" disabled hidden>
                    <option value="0">Выбери подрасу</option>
                    <option value="Гитьянки">Гитьянки</option>
                    <option value="Гитцерай">Гитцерай</option>
                </select>
                <select id="gnomeSubrace" name="charSubrace" disabled hidden>
                    <option value="0">Выбери подрасу</option>
                    <option value="Лесной-гном">Лесной гном</option>
                    <option value="Скальный-гном">Скальный гном</option>
                </select>
                <select id="dwarfSubrace" name="charSubrace" disabled hidden>
                    <option value="0">Выбери подрасу</option>
                    <option value="Горный-дварф">Горный дварф</option>
                    <option value="Холмовой-дварф">Холмовой дварф</option>
                </select>
                <select id="elfSubrace" name="charSubrace" disabled hidden>
                    <option value="0">Выбери подрасу</option>
                    <option value="Высший-эльф">Высший эльф</option>
                    <option value="Лесной-эльф">Лесной эльф</option>
                    <option value="Дроу">Дроу</option>
                </select>
                <select id="halflingSubrace" name="charSubrace" disabled hidden>
                    <option value="0">Выбери подрасу</option>
                    <option value="Коренастый-полурослик">Коренастый полурослик</option>
                    <option value="Легконогий-полурослик">Легконогий полурослик</option>
                </select>
                <br>
            </div>
            <button id="submit-character" type="submit" class="submit-button">Дальше</button>
        </form>
    </div>
</div>

<script>
        document.getElementById('submit-character').addEventListener('click', function() {
        document.getElementById('myForm').action = document.getElementById('charName').value.toString()+"/attributes";
        stop()
    })

    function raceSelect() {
        var charRace = document.getElementById("charRace");
        var githSubrace = document.getElementById("githSubrace");
        var gnomeSubrace = document.getElementById("gnomeSubrace");
        var dwarfSubrace = document.getElementById("dwarfSubrace");
        var elfSubrace = document.getElementById("elfSubrace");
        var halflingSubrace = document.getElementById("halflingSubrace");

        if (charRace.value === "Гит") {
            githSubrace.hidden = false;
            gnomeSubrace.hidden = true;
            dwarfSubrace.hidden = true;
            elfSubrace.hidden = true;
            halflingSubrace.hidden = true;
            githSubrace.disabled = false;
            gnomeSubrace.disabled = true;
            dwarfSubrace.disabled = true;
            elfSubrace.disabled = true;
            halflingSubrace.disabled = true;
        } else if (charRace.value === "Гном") {
            githSubrace.hidden = true;
            gnomeSubrace.hidden = false;
            dwarfSubrace.hidden = true;
            elfSubrace.hidden = true;
            halflingSubrace.hidden = true;
            githSubrace.disabled = true;
            gnomeSubrace.disabled = false;
            dwarfSubrace.disabled = true;
            elfSubrace.disabled = true;
            halflingSubrace.disabled = true;
        } else if (charRace.value === "Дварф") {
            githSubrace.hidden = true;
            gnomeSubrace.hidden = true;
            dwarfSubrace.hidden = false;
            elfSubrace.hidden = true;
            halflingSubrace.hidden = true;
            githSubrace.disabled = true;
            gnomeSubrace.disabled = true;
            dwarfSubrace.disabled = false;
            elfSubrace.disabled = true;
            halflingSubrace.disabled = true;
        } else if (charRace.value === "Эльф") {
            githSubrace.hidden = true;
            gnomeSubrace.hidden = true;
            dwarfSubrace.hidden = true;
            elfSubrace.hidden = false;
            halflingSubrace.hidden = true;
            githSubrace.disabled = true;
            gnomeSubrace.disabled = true;
            dwarfSubrace.disabled = true;
            elfSubrace.disabled = false;
            halflingSubrace.disabled = true;
        } else if (charRace.value === "Полурослик") {
            githSubrace.hidden = true;
            gnomeSubrace.hidden = true;
            dwarfSubrace.hidden = true;
            elfSubrace.hidden = true;
            halflingSubrace.hidden = false;
            githSubrace.disabled = true;
            gnomeSubrace.disabled = true;
            dwarfSubrace.disabled = true;
            elfSubrace.disabled = true;
            halflingSubrace.disabled = false;
        } else {
            githSubrace.hidden = true;
            gnomeSubrace.hidden = true;
            dwarfSubrace.hidden = true;
            elfSubrace.hidden = true;
            halflingSubrace.hidden = true;
        }
    }
</script>

</body>

</html>