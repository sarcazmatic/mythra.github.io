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

        h1, h2, h3, h5, h6 {
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

        .summary-box .character-atrb-box {
            display: block;
            box-sizing: border-box;
            width: auto;
            padding: 10px;
            margin-bottom: 1%;
            border-right: 1px solid #444;
        }

        .summary-box .skills-container {
            display: block;
            justify-items: center;
            box-sizing: border-box;
            width: auto;
            padding: 10px;
            margin-bottom: 1%;
            border: none;
        }

        .summary-box .skills-container .input-field-hp {
            box-sizing: border-box;
            justify-self: center;
            justify-content: center;
            justify-items: center;
            text-align: center;
            width: 30%;
            left: -117%;
            transform: translateX(117%);
            background-color: #222;
            border: solid 1px #444;
            padding: 8px;
        }

        .summary-box .skills-container .input-field-hp input {
            box-sizing: border-box;
            width: 25%;
            color: #fff;
            background-color: #666;
            padding: 0.7em 0.7em 0.7em 1em;
        }

        .summary-box .skills-container .skills-collumn {
            text-align: center;
            display: grid;
            grid-template-columns: 33% 33% 33%;
            box-sizing: border-box;
            width: auto;
            padding: 10px;
        }

        .summary-box .skills-container .skills-collumn .skills {
            box-sizing: border-box;
            width: auto;
            padding: 10px;
        }

        .summary-box .skills-container .skills-collumn .input-field {
            padding: 8px;
        }

        .summary-box .skills-container .skills-collumn .input-field label {
            font-weight: bold;
            font-size: small;
        }

        .summary-box .skills-container .skills-collumn .input-field {
            font-weight: bold;
            text-align: center;
        }

        .summary-box .skills-container .skills-collumn .input-field input[type="checkbox"] {
            width: auto;
            padding: 8px;
            background-color: #666;
            color: #666;
        }


        .summary-box .skills-container .skills-collumn .input-field input {
            width: 75%;
            padding: 8px;
            margin-bottom: 2%;
            border: 1px solid #444;
            border-radius: 0.25rem;
            background-color: #666;
            color: #fff;
        }

        .summary-box .skills-container .submit-button {
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

        .summary-box .skills-container .submit-button:hover {
            background-color: #444;
        }

    </style>
</head>

<body>
<div class="summary-box">
    <div class="character-atrb-box">
        <h2>Твой персонаж</h2>
        <br>
        <h3>Имя: ${charName}</h3>
        <br>
        <h3>Раса: ${charRace}</h3>
        <br>
        <h3>Класс: ${charClass}</h3>
        <br>
        <h5>Сила: ${strength}</h5>
        <h5>Ловкость: ${dexterity}</h5>
        <h5>Телосложение: ${constitution}</h5>
        <h5>Интеллект: ${intelligence}</h5>
        <h5>Мудрость: ${wisdom}</h5>
        <h5>Харизма: ${charisma}</h5>
    </div>
    <div class="skills-container">
        <h2>Твои хит поинты:</h2>
        <form action="charsheet" method="post">
            <div class="input-field-hp">
                <output id="hitPoints">${hitPoints}</output>
            </div>
            <br>
            <h2>Выберем твои навыки:</h2>
            <div class="skills-collumn">
                <div class="skills">
                    <div class="input-field">
                        <label for="acrobatics">Акробатика:</label>
                        <input name="acrobatics" value="${dexterityMod}" readonly>
                        <input type="checkbox" id="acrobatics" name="profs" value="acrobatics">
                    </div>
                    <div class="input-field">
                        <label for="athletics">Атлетика:</label>
                        <input name="athletics" value="${strengthMod}" readonly>
                        <input type="checkbox" id="athletics" name="profs" value="athletics">
                    </div>
                    <div class="input-field">
                        <label for="arcana">Магия:</label>
                        <input name="arcana" value="${intelligenceMod}" readonly>
                        <input type="checkbox" id="arcana" name="profs" value="arcana">
                    </div>
                    <div class="input-field">
                        <label for="deception">Обман:</label>
                        <input name="deception" value="${charismaMod}" readonly>
                        <input type="checkbox" id="deception" name="profs" value="deception">
                    </div>
                    <div class="input-field">
                        <label for="history">История:</label>
                        <input name="history" value="${intelligenceMod}" readonly>
                        <input type="checkbox" id="history" name="profs" value="history">
                    </div>
                    <div class="input-field">
                        <br>
                        <label for="insight">Проницательность:</label>
                        <input name="insight" value="${wisdomMod}" readonly>
                        <input type="checkbox" id="insight" name="profs" value="insight">
                    </div>
                </div>
                <div class="skills">
                    <div class="input-field">
                        <label for="intimidation">Запугивание:</label>
                        <input name="intimidation" value="${charismaMod}" readonly>
                        <input type="checkbox" id="intimidation" name="profs" value="intimidation">
                    </div>
                    <div class="input-field">
                        <label for="investigation">Расследование:</label>
                        <input name="investigation" value="${intelligenceMod}" readonly>
                        <input type="checkbox" id="investigation" name="profs" value="investigation">
                    </div>
                    <div class="input-field">
                        <label for="medicine">Медицина:</label>
                        <input name="medicine" value="${wisdomMod}" readonly>
                        <input type="checkbox" id="medicine" name="profs" value="medicine">
                    </div>
                    <div class="input-field">
                        <label for="nature">Природа:</label>
                        <input name="nature" value="${intelligenceMod}" readonly>
                        <input type="checkbox" id="nature" name="profs" value="nature">
                    </div>
                    <div class="input-field">
                        <label for="perception">Восприятие:</label>
                        <input name="perception" value="${wisdomMod}" readonly>
                        <input type="checkbox" id="perception" name="profs" value="perception">
                    </div>
                    <div class="input-field">
                        <br>
                        <label for="performance">Выступление:</label>
                        <input name="performance" value="${charismaMod}" readonly>
                        <input type="checkbox" id="performance" name="profs" value="performance">
                    </div>
                </div>
                <div class="skills">
                    <div class="input-field">
                        <label for="persuasion">Убеждение:</label>
                        <input name="persuasion" value="${charismaMod}" readonly>
                        <input type="checkbox" id="persuasion" name="profs" value="persuasion">
                    </div>
                    <div class="input-field">
                        <label for="religion">Религия:</label>
                        <input name="religion" value="${intelligenceMod}" readonly>
                        <input type="checkbox" id="religion" name="profs" value="religion">
                    </div>
                    <div class="input-field">
                        <label for="sleight-of-hand">Ловкость рук:</label>
                        <input name="sleight-of-hand" value="${dexterityMod}" readonly>
                        <input type="checkbox" id="sleight-of-hand" name="profs" value="sleight-of-hand">
                    </div>
                    <div class="input-field">
                        <label for="stealth">Скрытность:</label>
                        <input name="stealth" value="${dexterityMod}" readonly>
                        <input type="checkbox" id="stealth" name="profs" value="stealth">
                    </div>
                    <div class="input-field">
                        <label for="survival">Выживание:</label>
                        <input name="survival" value="${wisdomMod}" readonly>
                        <input type="checkbox" id="survival" name="profs" value="survival">
                    </div>
                    <div class="input-field">
                        <label for="animal-handling">Обращение с животнымии:</label>
                        <input name="animal-handling" value="${wisdomMod}" readonly>
                        <input type="checkbox" id="animal-handling" name="profs" value="animal-handling">
                    </div>
                </div>
            </div>
            <button type="submit" class="submit-button">Дальше</button>
            <input type="text" name="charName" value=${charName} hidden>
            <input type="text" name="charRace" value="${charRace}" hidden>
            <input type="text" name="charClass" value="${charClass}" hidden>

            <input type="text" name="strength" value=${strength} hidden>
            <input type="text" name="dexterity" value=${dexterity} hidden>
            <input type="text" name="constitution" value=${constitution} hidden>
            <input type="text" name="intelligence" value=${intelligence} hidden>
            <input type="text" name="wisdom" value=${wisdom} hidden>
            <input type="text" name="charisma" value=${charisma} hidden>
            <input type="text" name="hitPoints" value=${hitPoints} hidden>
        </form>
    </div>
</div>
</div>

</body>
</html>