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
        <form action="charsheet" method="post">
            <h2 id >Выберем твои навыки:</h2>
            <div class="skills-collumn">
                <div class="skills" id="skill-parent">
                </div>
            </div>
            <button type="submit" class="submit-button">Дальше</button>
            <p name="charName" value="${charName}" hidden>
            <p name="charRace" value="${charRace}" hidden>
            <p name="charClass" value="${charClass}" hidden>

            <p name="strength" value=${strength} hidden>
            <p name="dexterity" value=${dexterity} hidden>
            <p name="constitution" value=${constitution} hidden>
            <p name="intelligence" value=${intelligence} hidden>
            <p name="wisdom" value=${wisdom} hidden>
            <p name="charisma" value=${charisma} hidden>
            <p name="hitPoints" value=${hitPoints} hidden>
        </form>
    </div>
</div>

<script>

    let proficiency = ${proficiency};
    const listOfProficiencies = ${proficienciesList};

    console.log(proficiency)
    console.log(listOfProficiencies)

    window.onload = formCompetenceList(listOfProficiencies)

    function formCompetenceList(listOfProficiencies) {
        for (var i = 0; i < listOfProficiencies.length; i++) {
            var competenceSkill = document.createElement("div")
            competenceSkill.id = "skill-" + i;
            competenceSkill.className = "input-field"
            var skillText = listOfProficiencies[i]
            //let modificator = listOfProficiencies[i + 1]
            competenceSkill.innerText = skillText// + " " + modificator;
            document.getElementById('skill-parent').appendChild(competenceSkill)
            var competenceCheckbox = document.createElement("input")
            competenceCheckbox.setAttribute("type", "checkbox")
            competenceCheckbox.id = "checkbox-" + i;
            competenceCheckbox.setAttribute("value", skillText)
            document.getElementById('skill-parent').appendChild(competenceCheckbox)
        }
    }

</script>

</body>
</html>