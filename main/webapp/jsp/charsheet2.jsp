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
            font-family: 'Philosopher', cursive;
            background-color: #0d0d0d;
            color: #fff;
            padding: 10px;
        }

        .container-left {
            display: block;
            position: fixed;
            top: 2.5%;
            left: 5%;
            width: 15%;
            height: 50%;
            background-color: #222;
            border-radius: 10px;
            border: solid 1px #444;
        }

        .container-left-bottom {
            display: block;
            position: fixed;
            top: 54.5%;
            left: 5%;
            width: 15%;
            height: 18%;
            background-color: #222;
            border-radius: 10px;
            border: solid 1px #444;
        }

        .container-second-left {
            display: grid;
            position: fixed;
            grid-template-columns: 50% 50%;
            column-gap: 0;
            top: 2.5%;
            left: 21%;
            width: 35%;
            height: 70%;
            text-align: left;
            background-color: #222;
            border-radius: 10px;
            border: solid 1px #444;
        }

        .box-top {
            margin-left: 10px;
            margin-right: 10px;
            border-bottom: solid 1px #444;
        }

        .box-bottom {
            margin-left: 10px;
        }

        .box-hp-top {
            margin-top: 10%;
            margin-left: 25px;
            margin-right: 25px;
            text-align: center;
            border-bottom: solid 1px #444;
        }

        .box-hp-bottom {
            text-align: center;
        }

        .atrbs-rows-left {
            margin-left: 10px;
            margin-right: 15px;
            display: grid;
            grid-template-rows: 20% 80%;
            row-gap: 0;
        }

        .atrbs-rows-right {
            margin-left: 0;
            margin-right: 10px;
            display: grid;
            grid-template-rows: 20% 80%;
            row-gap: 0;
        }

        .atrbs-collumns {
            display: grid;
            grid-template-columns: 20% 80%;
            column-gap: 0;
        }

        h1, h2, h3, h4, h5, h6 {
            text-align: left;
        }

        .input-field {
        }

        .input-field-attr-withname {
            height: 33px;
            border-bottom: solid 1px #444;
        }

        .input-field-attr {
            height: 115px;
            font-size: medium;
        }

        .savethrow-text {
            text-transform: uppercase;
            color: orangered;
        }

        .buttons-class {
            margin-top: 10%;
            margin-left: 4.73%;
        }

        .exp-label {
            cursor: pointer;
            transition: color 0.3s;
            border-radius: 5px;
        }

        .exp-label:hover {
            color: orangered;
        }

        .heal-button {
            width: 30%;
            font-size: 0.7em;
            padding: 0.5em 0.1em 0.5em 0.1em;
            font-family: 'Philosopher', cursive;
            background-color: #666;
            border: none;
            border-radius: 5px;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .heal-button:hover {
            background-color: forestgreen;
        }

        .rest-button {
            width: 30%;
            font-size: 0.7em;
            padding: 0.5em 0.1em 0.5em 0.1em;
            font-family: 'Philosopher', cursive;
            background-color: #666;
            border: none;
            border-radius: 5px;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .rest-button:hover {
            background-color: dodgerblue;
        }

        .dmg-button {
            width: 30%;
            font-size: 0.7em;
            padding: 0.5em 0.1em 0.5em 0.1em;
            font-family: 'Philosopher', cursive;
            background-color: #666;
            border: none;
            border-radius: 5px;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .dmg-button:hover {
            background-color: orangered;
        }

    </style>
</head>
<body>

<div class="container-left">
    <div class="box-top">
        <div class="input-field">
            <p>Имя: ${charName}</p>
        </div>
        <div class="input-field">
            <p>Раса: ${charRace}</p>
        </div>
        <div class="input-field">
            <p>Класс: ${charClass}</p>
        </div>
    </div>
    <div class="box-top">
        <div class="input-field">
            <p>Уровень: ${level}</p>
        </div>
        <div class="input-field">
            <p id="exp-label" class="exp-label">Опыт: ${experience}</p>
        </div>
        <div class="input-field">
            <p>Мастерство: +${proficiency}</p>
        </div>
    </div>
    <div class="box-bottom">
        <div class="input-field">
            <p>КД: ${ac}</p>
        </div>
        <div class="input-field">
            <p>Инициатива: ${initiative}</p>
        </div>
        <div class="input-field">
            <p>Скорость: ${speed}фт</p>
        </div>
    </div>
</div>

<div class="container-left-bottom">
    <div class=box-hp-top>
        <div class="input-field">
            <output class="savethrow-text">${hitPoints}</output>
        </div>
    </div>
    <div class=box-hp-bottom>
        <div class="input-field">
            <output class="savethrow-text">${hitPoints}</output>
        </div>
    </div>
    <div class="buttons-class">
        <button class="heal-button" id="heal-button" name="heal-button">ЛЕЧЕНИЕ</button>
        <button class="rest-button" id="rest-button" name="rest-button">ОТДЫХ</button>
        <button class="dmg-button" id="dmg-button" name="dmg-button">УРОН</button>
    </div>
</div>

<div class="container-second-left">
    <div class="atrbs-rows-left">
        <div class="input-field-attr-withname">
            <p>Сила: ${strength}</p>
        </div>
        <div class="atrbs-collumns">
            <div class="input-field-attr">
                <h2>${strengthmod}</h2>
            </div>
            <div class="input-field-attr">
                <br>
                <output class="savethrow-text">${strengthsave} Спасбросок</output>
                <br>
                <output>${athletics} Атлетика</output>
                <br>
            </div>
        </div>
    </div>
    <div class="atrbs-rows-right">
        <div class="input-field-attr-withname">
            <p>Интеллект: ${intelligence}</p>
        </div>
        <div class="atrbs-collumns">
            <div class="input-field-attr">
                <h2>${intelligencemod}</h2>
            </div>
            <div class="input-field-attr">
                <br>
                <output class="savethrow-text">${intelligencesave} Спасбросок</output>
                <br>
                <output>${arcana} Аркана</output>
                <br>
                <output>${history} История</output>
                <br>
                <output>${investigation} Расследование</output>
                <br>
                <output>${nature} Природа</output>
                <br>
                <output>${religion} Религия</output>
                <br>
            </div>
        </div>
    </div>
    <div class="atrbs-rows-left">
        <div class="input-field-attr-withname">
            <p>Ловкость: ${dexterity}</p>
        </div>
        <div class="atrbs-collumns">
            <div class="input-field-attr">
                <h2>${dexteritymod}</h2>
            </div>
            <div class="input-field-attr">
                <br>
                <output class="savethrow-text">${dexteritysave} Спасбросок</output>
                <br>
                <output>${acrobatics} Акробатика</output>
                <br>
                <output>${sleight_of_hand} Ловкость рук</output>
                <br>
                <output>${stealth} Скрытность</output>
                <br>
            </div>
        </div>
    </div>
    <div class="atrbs-rows-right">
        <div class="input-field-attr-withname">
            <p>Мудрость: ${wisdom}</p>
        </div>
        <div class="atrbs-collumns">
            <div class="input-field-attr">
                <h2>${wisdommod}</h2>
            </div>
            <div class="input-field-attr">
                <br>
                <output class="savethrow-text">${wisdomsave} Спасбросок</output>
                <br>
                <output>${insight} Проницательность</output>
                <br>
                <output>${medicine} Медицина</output>
                <br>
                <output>${perception} Восприятие</output>
                <br>
                <output>${survival} Выживание</output>
                <br>
                <output>${animal_handling} Уход за животными</output>
                <br>
            </div>
        </div>
    </div>
    <div class="atrbs-rows-left">
        <div class="input-field-attr-withname">
            <p>Телосложение: ${constitution}</p>
        </div>
        <div class="atrbs-collumns">
            <div class="input-field-attr">
                <h2>${constitutionmod}</h2>
            </div>
            <div class="input-field-attr">
                <br>
                <output class="savethrow-text">${constitutionsave} Спасбросок</output>
                <br>
            </div>
        </div>
    </div>
    <div class="atrbs-rows-right">
        <div class="input-field-attr-withname">
            <p>Харизма: ${charisma}</p>
        </div>
        <div class="atrbs-collumns">
            <div class="input-field-attr">
                <h2>${charismamod}</h2>
            </div>
            <div class="input-field-attr">
                <br>
                <output class="savethrow-text">${charismasave} Спасбросок</output>
                <br>
                <output>${deception} Обман</output>
                <br>
                <output>${intimidation} Запугивание</output>
                <br>
                <output>${performance} Выступление</output>
                <br>
                <output>${persuasion} Убеждение</output>
                <br>
            </div>
        </div>
    </div>
</div>

</body>
</html>