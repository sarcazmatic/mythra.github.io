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

    </style>
</head>

<body>
<div class="summary-box">
    <div class="character-box">
        <h2>Твой персонаж</h2>
        <br>
        <h3>Имя: ${charName}</h3>
        <br>
        <h3>Раса: ${charRaceOne} ${charRaceTwo}</h3>
        <br>
        <h3>Класс: ${charClassOne} ${charClassTwo}</h3>
        <br>
    </div>
    <div class="atrb-container">
        <h2>Выберем характеристики</h2>
        <form action="skills" method="post">
            <div class="atrb-collumn">
                <div class="atrbs">
                    <div class="input-field">
                        <label for="strength">Сила:</label>
                        <br>
                        <input type="number" id="strength" name="strength" min="3" max="18" required placeholder="От 3 до 18">
                    </div>
                    <div class="input-field">
                        <label for="dexterity">Ловкость:</label>
                        <br>
                        <input type="number" id="dexterity" name="dexterity" min="3" max="18" required placeholder="От 3 до 18">
                    </div>
                    <div class="input-field">
                        <label for="constitution">Телосложение:</label>
                        <br>
                        <input type="number" id="constitution" name="constitution" min="3" max="18" required placeholder="От 3 до 18">
                    </div>
                </div>
                <div class="atrbs">
                    <div class="input-field">
                        <label for="intelligence">Интеллект:</label>
                        <br>
                        <input type="number" id="intelligence" name="intelligence" min="3" max="18" required placeholder="От 3 до 18">
                    </div>
                    <div class="input-field">
                        <label for="wisdom">Мудрость:</label>
                        <br>
                        <input type="number" id="wisdom" name="wisdom" min="3" max="18" required placeholder="От 3 до 18">
                    </div>
                    <div class="input-field">
                        <label for="charisma">Харизма:</label>
                        <br>
                        <input type="number" id="charisma" name="charisma" min="3" max="18" required placeholder="От 3 до 18">
                    </div>
                </div>
            </div>
            <button type="submit" class="submit-button">Дальше</button>
            <input type="text" name="charName" value=${charName} hidden>
            <input type="text" name="charRace" value="${charRaceOne}${charRaceTwo}" hidden>
            <input type="text" name="charClass" value="${charClassOne}${charClassTwo}" hidden>
        </form>
    </div>
</div>

</body>
</html>