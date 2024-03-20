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
            overflow-y: scroll;
        }

        @media (max-width: 414px) {
            body {
                display: flex;
                font-family: 'Philosopher', cursive;
                background-color: #0d0d0d;
                color: #fff;
                padding: 5px;
                overflow-y: scroll;
            }
        }

        h1, h2, h3, h4, h5, h6 {
            text-align: left;
        }

        .input-field {
        }

        .container-left {
            display: block;
            position: absolute;
            top: 2.5%;
            left: 5%;
            width: 15%;
            height: 50%;
            background-color: #222;
            border-radius: 10px;
            border: solid 1px #444;
        }

        @media (max-width: 414px) {
            .container-left {
                display: block;
                position: absolute;
                left: 2%;
                height: 54%;
                top: 1%;
                width: 96%;
                background-color: #222;
                border-radius: 10px;
                border: solid 1px #444;
            }
        }

        .box-top {
            margin-left: 10px;
            margin-right: 10px;
            border-bottom: solid 1px #444;
        }

        .box-bottom {
            margin-left: 10px;
        }

        .exp-label {
            cursor: pointer;
            transition: color 0.3s;
            border-radius: 5px;
        }

        .exp-label:hover {
            color: orangered;
        }

        .container-left-bottom {
            display: block;
            position: absolute;
            top: 54.5%;
            left: 5%;
            width: 15%;
            height: 18%;
            background-color: #222;
            border-radius: 10px;
            border: solid 1px #444;
        }

        @media (max-width: 414px) {
            .container-left-bottom {
                display: block;
                position: absolute;
                top: 57%;
                left: 2%;
                width: 96%;
                height: 25%;
                background-color: #222;
                border-radius: 10px;
                border: solid 1px #444;
            }
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

        .buttons-class {
            margin-top: 10%;
            margin-left: 4.73%;
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

        .container-left-bottom-bottom {
            display: grid;
            position: absolute;
            top: 74.5%;
            left: 5%;
            width: 51%;
            height: auto;
            background-color: #222;
            border-radius: 10px;
            border: solid 1px #444;
        }

        .abilities-row {
            position: relative;
            padding: 1% 1% 1% 1%;
            margin-left: 2%;
            margin-right: 2%;
            display: inline-grid;
            grid-template-columns: 30% 25% 20% 25%;
            font-size: smaller;
            justify-items: center;
            align-items: center;
        }

        .ability-name {
            transition: color 0.3s;
        }

        .ability-name:hover {
            color: orangered;
        }

        .ability-use-button {
            width: 35%;
            font-size: smaller;
            padding: 0.5em 0.1em 0.5em 0.1em;
            font-family: 'Philosopher', cursive;
            background-color: #666;
            border: none;
            border-radius: 5px;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .ability-use-button:hover {
            background-color: dodgerblue;
        }

        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(68, 68, 68); /* Fallback color */
            background-color: rgba(68, 68, 68, 0.4); /* Black w/ opacity */
        }

        .modal-content {
            position: relative;
            background-color: #222;
            margin: auto;
            padding: 0;
            border: 1px solid #444;
            width: 80%;
            box-shadow: 0 4px 8px 0 rgba(102, 102, 102, 0.2), 0 6px 20px 0 rgba(68, 68, 68, 0.19);
            animation-name: animatetop;
            animation-duration: 0.4s
        }

        .modal-header {
            padding: 2px 16px;
            border-bottom: 1px solid #444;
            background-color: #222;
            color: white;
        }

        .modal-body {
            padding: 2px 16px;
        }

        @keyframes animatetop {
            from {
                top: -300px;
                opacity: 0
            }
            to {
                top: 0;
                opacity: 1
            }
        }

        .container-second-left {
            display: grid;
            position: absolute;
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

        @media (max-width: 414px) {
            .container-second-left {
                display: grid;
                position: absolute;
                grid-template-columns: 50% 50%;
                height: 70%;
                top: 84%;
                left: 2%;
                width: 96%;
                text-align: left;
                background-color: #222;
                border-radius: 10px;
                border: solid 1px #444;
            }
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

        .input-field-attr-withname {
            height: 33px;
            border-bottom: solid 1px #444;
        }

        .input-field-attr {
            height: 115px;
            font-size: medium;
        }

        @media (max-width: 414px) {
            .input-field-attr {
                height: 115px;
                font-size: 14px;
            }
        }

        .savethrow-text {
            text-transform: uppercase;
            color: orangered;
        }

        .container-third-left {
            display: grid;
            padding-left: 1.15%;
            position: absolute;
            grid-template-rows: 15% 25% 15% 25%;
            row-gap: 5%;
            top: 2.5%;
            left: 57%;
            width: 35%;
            height: 35%;
            text-align: left;
            background-color: #222;
            border-radius: 10px;
            border: solid 1px #444;
        }

        .container-third-left-bottom {
            display: grid;
            padding-left: 1.15%;
            position: absolute;
            grid-template-columns: 48% 48%;
            column-gap: 4%;
            top: 39.5%;
            left: 57%;
            width: 35%;
            height: 33%;
            text-align: left;
            background-color: #222;
            border-radius: 10px;
            border: solid 1px #444;
        }

        .conditions-columns {
            display: grid;
            padding-left: 1.15%;
            grid-template-rows: 10% 10% 10% 10% 10% 10% 10%;
            row-gap: 3%;
            text-align: left;
            border-radius: 10px;
            align-content: center;
            margin-right: 5%;
        }

        .conditions-input {
            text-align-last: justify;
            border-bottom: solid 1px #444;
        }

        .weapons-rows {
            display: grid;
            text-align: left;
            grid-template-rows: 35% 31% 30%;
            row-gap: 2%;
            padding-bottom: 1%;
        }

        .weapons-column {
            display: grid;
            text-align: left;
            grid-template-columns: 55% 15% 15% 15%;
            font-size: smaller;
            column-gap: 0;
            justify-items: start;
        }

        .weapons-column-output {
        }

    </style>
</head>
<body>

<div class="container-left">
    <div class="box-top">
        <div class="input-field">
            <p>Имя: ${charName}</p>
            <p id="char-name" hidden>${charName}</p>
        </div>
        <div class="input-field">
            <p>Раса: ${charRace}</p>
        </div>
        <div class="input-field">
            <p>Класс: ${charClass}</p>
            <p id="char-class" hidden>${charClass}</p>
        </div>
    </div>
    <div class="box-top">
        <div class="input-field">
            <p>Уровень: ${level}</p>
        </div>
        <div class="input-field">
            <output id="exp-button" class="exp-label" onclick="expShow()">Опыт:</output>
            <output id="expNumber">${experience}</output>
        </div>
        <div class="input-field">
            <p>Мастерство: ${proficiency}</p>
        </div>
    </div>
    <div class="box-bottom">
        <div class="input-field">
            <p>КД: ${ac}</p>
        </div>
        <div class="input-field">
            <p>Скорость: ${speed}фт</p>
        </div>
        <div class="input-field">
            <p>Темное зрение: ${darkvision}</p>
        </div>
    </div>
</div>
<div class="modal" id="expModal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>${experience}</h2>
        </div>
        <div class="modal-body">
            <input type="number" id="incoming-exp">
            <button class="heal-button" id="sec-exp-button" name="sec-heal-button">ОПЫТ</button>
        </div>
    </div>
</div>
<script>
    var charName = document.getElementById("char-name").innerText;

    var expModal = document.getElementById("expModal");
    var expButton = document.getElementById("sec-exp-button");

    var incomingExp;
    var currentExperience = document.getElementById("expNumber");

    function expShow() {
        expModal.style.display = "block";
    }

    window.onclick = function (event) {
        if (event.target === expModal) {
            expModal.style.display = "none";
        }
    }

    expButton.addEventListener("click", function () {
        incomingExp = document.getElementById("incoming-exp").value;
        expModal.style.display = "none";
        var ourRequest = new XMLHttpRequest();
        ourRequest.open('PUT', '/charsheet/calcExp');
        ourRequest.setRequestHeader("Content-Type", "application/json; charset=UTF-8")
        const body = JSON.stringify({
            charName: charName,
            modifier: incomingExp
        });
        ourRequest.onload = function () {
            var ourData = JSON.parse(ourRequest.responseText);
            console.log(ourData);
            renderThis(ourData);
        }
        console.log(body)
        ourRequest.send(body);
    })

    function renderThis(data) {
        currentExperience.innerText = data.experience;
    }

</script>

<div class="container-left-bottom">
    <div class=box-hp-top>
        <div class="input-field">
            <output id="currentHP" class="savethrow-text">${curHitPoints}</output>
        </div>
    </div>
    <div class=box-hp-bottom>
        <div class="input-field">
            <output id="max-hit-points" class="savethrow-text">${maxHitPoints}</output>
        </div>
    </div>
    <div class="buttons-class">
        <button class="heal-button" id="heal-button" name="heal-button" onclick="hpHealShow()">ЛЕЧЕНИЕ</button>
        <button class="rest-button" id="rest-button" name="rest-button">ОТДЫХ</button>
        <button class="dmg-button" id="dmg-button" name="dmg-button" onclick="hpDmgShow()">УРОН</button>
    </div>
</div>

<div class="modal" id="healModal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>${curHitPoints}</h2>
        </div>
        <div class="modal-body">
            <input type="number" id="incoming-heal">
            <button class="heal-button" id="sec-heal-button" name="sec-heal-button">ЛЕЧЕНИЕ</button>
        </div>
    </div>
</div>
<script>
    var charName = document.getElementById("char-name").innerText;

    var healModal = document.getElementById("healModal");
    var healButton = document.getElementById("sec-heal-button");

    var incomingHeal;
    var currentHP = document.getElementById("currentHP");

    function hpHealShow() {
        healModal.style.display = "block";
    }

    window.onclick = function (event) {
        if (event.target === healModal) {
            healModal.style.display = "none";
        }
    }

    healButton.addEventListener("click", function () {
        incomingHeal = document.getElementById("incoming-heal").value;
        healModal.style.display = "none";
        var ourRequest = new XMLHttpRequest();
        ourRequest.open('PUT', '/charsheet/calcHeal');
        ourRequest.setRequestHeader("Content-Type", "application/json; charset=UTF-8")
        const body = JSON.stringify({
            charName: charName,
            modifier: incomingHeal
        });
        ourRequest.onload = function () {
            var ourData = JSON.parse(ourRequest.responseText);
            console.log(ourData)
            renderHTML(ourData);
        }
        console.log(body)
        ourRequest.send(body);
    })

    function renderHTML(data) {
        currentHP.innerText = data.currentHP;
    }

</script>

<div class="modal" id="dmgModal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>${curHitPoints}</h2>
        </div>
        <div class="modal-body">
            <input type="number" id="incoming-damage">
            <button class="dmg-button" id="sec-dmg-button" name="sec-dmg-button">УРОН</button>
        </div>
    </div>
</div>
<script>
    var charName = document.getElementById("char-name").innerText;

    var dmgModal = document.getElementById("dmgModal");
    var dmgButton = document.getElementById("sec-dmg-button");

    var incomingDamage;
    var currentHP = document.getElementById("currentHP");

    function hpDmgShow() {
        dmgModal.style.display = "block";
    }

    window.onclick = function (event) {
        if (event.target === dmgModal) {
            dmgModal.style.display = "none";
        }
    }

    dmgButton.addEventListener("click", function () {
        incomingDamage = document.getElementById("incoming-damage").value;
        dmgModal.style.display = "none";
        var ourRequest = new XMLHttpRequest();
        ourRequest.open('PUT', '/charsheet/calcDmg');
        ourRequest.setRequestHeader("Content-Type", "application/json; charset=UTF-8")
        const body = JSON.stringify({
            charName: charName,
            modifier: incomingDamage
        });
        ourRequest.onload = function () {
            var ourData = JSON.parse(ourRequest.responseText);
            console.log(ourData)
            renderHTML(ourData);
        }
        console.log(body)
        ourRequest.send(body);
    })

    function renderHTML(data) {
        currentHP.innerText = data.currentHP;
    }

</script>

<div class="container-left-bottom-bottom">
    <div style="border-bottom: 1px #444 solid; font-size: medium" class="abilities-row">
        <output>Название</output>
        <output>Стоимость</output>
        <output>Заряды</output>
        <output>Восстановление</output>
    </div>
    <div class="abilities-row">
        <output id="ability-name-1" class="ability-name" onclick="descriptionShow()">${abilName1}</output>
        <output>${abilCost1}</output>
        <button class="ability-use-button" id="${abilUseButton1}"
                name="ability-use-button" onclick="minusCharge()">${abilCharges1}</button>
        <output>${abilRest1}</output>
    </div>
</div>

<script>
    var charName = document.getElementById("char-name").innerText;
    var abilName = document.getElementById("ability-name-1").innerText;
    var charClass = document.getElementById("char-class").innerText;

    var button = document.querySelector('#${abilUseButton1}');
    var currentCharge = ${abilCharges1};

    function minusCharge() {
        if (currentCharge > 0) {
            currentCharge--;
            button.innerText = currentCharge;
            var ourRequest = new XMLHttpRequest();
            ourRequest.open('PUT', '/charsheet/abilCharge');
            ourRequest.setRequestHeader("Content-Type", "application/json; charset=UTF-8")
            const body = JSON.stringify({
                charName: charName,
                charClass: charClass,
                abilName: abilName,
                modifier: button.innerText
            });
            ourRequest.onload = function () {
                var ourData = JSON.parse(ourRequest.responseText);
                console.log(ourData)
            }
            console.log(body)
            ourRequest.send(body);
        } else {
            currentCharge = 0;
        }
    }
</script>

<div class="modal" id="descModal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>${abilName1}</h2>
        </div>
        <div class="modal-body">
            <p>${abilDesc1}</p>
        </div>
    </div>
</div>

<script>
    var modal = document.getElementById("descModal");

    function descriptionShow() {
        modal.style.display = "block";
    }

    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
</script>

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
                <br>
                <output> ${initiative} Инициатива</output>
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

<div class="container-third-left">
    <div class="input-field-attr-withname">
        <p>Оружие ближнего боя:</p>
    </div>
    <div class="weapons-rows">
        <div class="weapons-column">
            <div class="weapons-column-output">
            </div>
            <div class="weapons-column-output">
                Кости
            </div>
            <div class="weapons-column-output">
                Атака
            </div>
            <div class="weapons-column-output">
                Урон
            </div>
        </div>
        <div class="weapons-column">
            <div class="weapons-column-output">
                Секира безбашенности
            </div>
            <div class="weapons-column-output">
                2к6
            </div>
            <div class="weapons-column-output">
                +7
            </div>
            <div class="weapons-column-output">
                +3
            </div>
        </div>
        <div class="weapons-column">
            <div class="weapons-column-output">
                Лоботряска
            </div>
            <div class="weapons-column-output">
                2к8
            </div>
            <div class="weapons-column-output">
                +7
            </div>
            <div class="weapons-column-output">
                +3
            </div>
        </div>
    </div>
    <div class="input-field-attr-withname">
        <p>Оружие дальнего боя:</p>
    </div>
    <div class="weapons-rows">
        <div class="weapons-column">
            <div class="weapons-column-output">
            </div>
            <div class="weapons-column-output">
                Кости
            </div>
            <div class="weapons-column-output">
                Атака
            </div>
            <div class="weapons-column-output">
                Урон
            </div>
        </div>
        <div class="weapons-column">
            <div class="weapons-column-output">
                Просто лук
            </div>
            <div class="weapons-column-output">
                2к8
            </div>
            <div class="weapons-column-output">
                +7
            </div>
            <div class="weapons-column-output">
                +3
            </div>
        </div>
        <div class="weapons-column">
            <div class="weapons-column-output">
                Лобострелка
            </div>
            <div class="weapons-column-output">
                2к8
            </div>
            <div class="weapons-column-output">
                +7
            </div>
            <div class="weapons-column-output">
                +3
            </div>
        </div>
    </div>
</div>

<div class="container-third-left-bottom">
    <div class="conditions-columns">
        <div class="conditions-input">
            <output style="display: inline-block;align-self: center">Без сознания</output>
            <input type="checkbox">
        </div>
        <div class="conditions-input">
            Невидимость
            <input type="checkbox">
        </div>
        <div class="conditions-input">
            Окаменение
            <input type="checkbox">
        </div>
        <div class="conditions-input">
            Отравление
            <input type="checkbox">
        </div>
        <div class="conditions-input">
            Паралич
            <input type="checkbox">
        </div>
        <div class="conditions-input">
            Испуг
            <input type="checkbox">
        </div>
        <div class="conditions-input">
            Недееспособность
            <input type="checkbox">
        </div>
    </div>
    <div class="conditions-columns">
        <div class="conditions-input">
            Путы
            <input type="checkbox">
        </div>
        <div class="conditions-input">
            Очарование
            <input type="checkbox">
        </div>
        <div class="conditions-input">
            Ничком
            <input type="checkbox">
        </div>
        <div class="conditions-input">
            Глухота
            <input type="checkbox">
        </div>
        <div class="conditions-input">
            Слепота
            <input type="checkbox">
        </div>
        <div class="conditions-input">
            Ошеломление
            <input type="checkbox">
        </div>
        <div class="conditions-input">
            <output style="display: inline-block;align-self: center">В захвате</output>
            <input type="checkbox">
        </div>
    </div>
</div>

</body>
</html>