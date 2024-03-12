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
            font-family: 'Philosopher', cursive;
            background-color: #0d0d0d;
            color: #fff;
            padding: 10px;
        }

        body.active .wrapper .section {
            margin-left: 15%;
            width: auto;
        }

        body.active .wrapper .sidebar {
            left: 0;
        }

        .wrapper .section {
            width: auto;
            top: 5%;
            margin-left: 0;
            transition: all 0.5s ease;
        }

        .wrapper .section .top_navbar {
            align-self: center;
            align-items: center;
            align-content: center;
            background: #0d0d0d;
            height: 50px;
            display: flex;
            padding: 0 30px;
            width: auto;
        }

        .wrapper .section .top_navbar .hamburger a {
            font-size: 28px;
            color: #f4fbff;
        }

        .wrapper .section .top_navbar .hamburger a:hover {
            color: rgb(152, 152, 152);
        }

        .wrapper .sidebar {
            background: #222;
            position: fixed;
            top: 0;
            left: -20%;
            width: 225px;
            height: 100%;
            padding: 20px 0;
            transition: all 0.5s ease;
            border-right: 1px solid #fff;
        }

        .wrapper .sidebar ul li a {
            display: block;
            padding: 13px 30px;
            border-bottom: 1px solid #222;
            color: rgb(241, 237, 237);
            font-size: 16px;
            position: relative;
        }

        .wrapper .sidebar ul li a:hover,
        .wrapper .sidebar ul li a.active {
            color: #0c7db1;
            background: white;
            border-right: 2px solid rgb(5, 68, 104);
        }

        .wrapper .sidebar ul li a:hover:before,
        .wrapper .sidebar ul li a.active:before {
            display: block;
        }

        .container {
            display: grid;
            max-width: 80%;
            margin: 10px auto 0;
            grid-template-columns: repeat(4, 25% 20% 20% 32%);
            grid-gap: 1%;
            background-color: #222;
            border-radius: 10px;
            padding: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
        }

        .container-skills-health {
            display: grid;
            grid-template-columns: repeat(2, 79% 20%);
            grid-column-gap: 1%;
            max-width: 80%;
            margin: 10px auto 0;
            background-color: #222;
            border-radius: 10px;
            padding: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
        }

        h1, h2, h3 {
            text-align: center;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.8);
        }

        .box {
            border: 2px solid #444;
            border-radius: 10px;
            padding: 10px;
            margin-bottom: 5px;
            width: auto;
        }

        .box-attributes {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            width: auto;
            padding: -1px;
            margin-bottom: 5px;
        }

        .box-skills {
            display: grid;
            grid-template-columns: repeat(6, 1fr);
            align-items: center;
            width: 100%;
            padding: 0px;
            margin-bottom: 5px;
        }

        .box-skills-by-three {
            border-radius: 10px;
            margin-bottom: 5px;
        }

        .input-field {
            text-align: center;
            display: block;
            margin-bottom: 10px;
        }

        .input-field label {
            font-weight: bold;
        }

        .input-field input[type="text"],
        .input-field input[type="number"] {
            text-align: center;
            width: 60%;
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #444;
            background-color: #666;
            color: #fff;
        }

        select {
            -moz-appearance: none; /* Firefox */
            -webkit-appearance: none; /* Safari and Chrome */
            appearance: none;
            height: 15%;
            width: 65%;
            font-size: 0.8em;
            padding: 0.7em 0.7em 0.7em 1em;
            background: url(https://icons.veryicon.com/png/o/miscellaneous/lattice-technology/drop-down-37.png) 96% / 25% no-repeat #666;
            border: 1px solid #666;
            border-radius: 0.25rem;
            color: #fff;
            cursor: pointer;
        }

        hamburger {
            font-family: 'Philosopher', cursive;
        }

    </style>
</head>
<body>

<div class="wrapper">
    <div class="section">
        <div class="top_navbar">
            <div class="hamburger">
                <a href="#">Меню</a>
            </div>
        </div>
    </div>
    <div class="sidebar">
        <!--profile image & text-->
        <ul>
            <li>
                <a href="#" class="active">
                    <span class="item">Главная странице</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="item">Инвентарь</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="item">Заклинания и способности</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="item">Записи</span>
                </a>
            </li>
        </ul>
    </div>
</div>
<script>
    var hamburger = document.querySelector(".hamburger");
    hamburger.addEventListener("click", function () {
        document.querySelector("body").classList.toggle("active");
    })
</script>

<div class="container">
    <div class="box">
        <h2>Твой персонаж</h2>
        <div class="input-field">
            <label>Имя:</label>
            <br>
            <h2>${charName}</h2>
        </div>
        <div class="input-field">
            <label>Раса:</label>
            <br>
            <h2>${charRace}</h2>
        </div>
        <div class="input-field">
            <label>Класс:</label>
            <br>
            <h2>${charClass}</h2>
        </div>
    </div>

    <div class="box">
        <h2>Опыт</h2>
        <div class="input-field">
            <label for="level">Уровень:</label>
            <br>
            <input type="number" id="level" name="level" value="${level}" readonly>
        </div>
        <div class="input-field">
            <label for="experience">Опыт:</label>
            <br>
            <input type="number" id="experience" name="experience" value="${experience}">
        </div>
        <div class="input-field">
            <label for="proficiency">Бонус мастерства:</label>
            <br>
            <input type="number" id="proficiency" name="proficiency" value="${proficiency}" readonly>
        </div>
    </div>

    <div class="box">
        <h2>Основное</h2>
        <div class="input-field">
            <label for="ac">КД:</label>
            <br>
            <input type="number" id="ac" name="ac" value="${ac}" readonly>
        </div>
        <div class="input-field">
            <label for="initiative">Бонус к инициативе:</label>
            <br>
            <input type="number" id="initiative" name="initiative" value="${initiative}" readonly>
        </div>
        <div class="input-field">
            <label for="speed">Скорость:</label>
            <br>
            <input type="number" id="speed" name="speed" value="${speed}" readonly>
        </div>
        <!-- Add more fields as needed -->
    </div>
    <div class="box">
        <h2>Характеристики</h2>
        <div class="box-attributes">
            <div class="input-field">
                <label for="strength">Сила:</label>
                <br>
                <input type="number" id="strength" name="strength" value="${strength}" readonly>
                <input type="number" id="strength-mod" name="strength-mod" value="${strengthmod}" readonly>
            </div>
            <div class="input-field">
                <label for="dexterity">Ловкость:</label>
                <br>
                <input type="number" id="dexterity" name="dexterity" value="${dexterity}" readonly>
                <input type="number" id="dexterity-mod" name="dexterity-mod" value="${dexteritymod}" readonly>
            </div>
            <div class="input-field">
                <label for="constitution">Телосложение:</label>
                <br>
                <input type="number" id="constitution" name="constitution" value="${constitution}" readonly>
                <input type="number" id="constitution-mod" name="constitution-mod" value="${constitutionmod}" readonly>
            </div>
            <div class="input-field">
                <label for="intelligence">Интеллект:</label>
                <br>
                <input type="number" id="intelligence" name="intelligence" value="${intelligence}" readonly>
                <input type="number" id="intelligence-mod" name="intelligence-mod" value="${intelligencemod}" readonly>
            </div>
            <div class="input-field">
                <label for="wisdom">Мудрость:</label>
                <br>
                <input type="number" id="wisdom" name="wisdom" value="${wisdom}" readonly>
                <input type="number" id="wisdom-mod" name="wisdom-mod" value="${wisdommod}" readonly>
            </div>
            <div class="input-field">
                <label for="charisma">Харизма:</label>
                <br>
                <input type="number" id="charisma" name="charisma" value="${charisma}" readonly>
                <input type="number" id="charisma-mod" name="charisma-mod"  value="${charismamod}" readonly>
            </div>
            <!-- Add more fields as needed -->
            <!-- Add more boxes for other sections -->
        </div>
    </div>
    <!-- Add more boxes for other sections -->
</div>

<div class="container-skills-health">
    <div class="box">
        <h2>Навыки</h2>
        <div class="box-skills">
            <div class="box-skills-by-three">
                <div class="input-field">
                    <label for="acrobatics">Акробатика:</label>
                    <br>
                    <input type="number" id="acrobatics" name="acrobatics" value="${acrobatics}" readonly>
                </div>
                <div class="input-field">
                    <label for="athletics">Атлетика:</label>
                    <br>
                    <input type="number" id="athletics" name="athletics" value="${athletics}" readonly>
                </div>
                <div class="input-field">
                    <label for="arcana">Аркана:</label>
                    <br>
                    <input type="number" id="arcana" name="arcana" value="${arcana}" readonly>
                </div>
            </div>
            <div class="box-skills-by-three">
                <div class="input-field">
                    <label for="deception">Обман:</label>
                    <br>
                    <input type="number" id="deception" name="deception" value="${deception}" readonly>
                </div>
                <div class="input-field">
                    <label for="history">История:</label>
                    <br>
                    <input type="number" id="history" name="history" value="${history}" readonly>
                </div>
                <div class="input-field">
                    <label for="insight">Проницательность:</label>
                    <br>
                    <input type="number" id="insight" name="insight" value="${insight}" readonly>
                </div>
            </div>
            <div class="box-skills-by-three">
                <div class="input-field">
                    <label for="intimidation">Запугивание:</label>
                    <br>
                    <input type="number" id="intimidation" name="intimidation" value="${intimidation}" readonly>
                </div>
                <div class="input-field">
                    <label for="investigation">Расследование:</label>
                    <br>
                    <input type="number" id="investigation" name="investigation" value="${investigation}" readonly>
                </div>
                <div class="input-field">
                    <label for="medicine">Медицина:</label>
                    <br>
                    <input type="number" id="medicine" name="medicine" value="${medicine}" readonly>
                </div>
            </div>
            <div class="box-skills-by-three">
                <div class="input-field">
                    <label for="nature">Природа:</label>
                    <br>
                    <input type="number" id="nature" name="nature" value="${nature}" readonly>
                </div>
                <div class="input-field">
                    <label for="perception">Внимательность:</label>
                    <br>
                    <input type="number" id="perception" name="perception" value="${perception}" readonly>
                </div>
                <div class="input-field">
                    <label for="performance">Выступление:</label>
                    <br>
                    <input type="number" id="performance" name="performance" value="${performance}" readonly>
                </div>
            </div>
            <div class="box-skills-by-three">
                <div class="input-field">
                    <label for="persuasion">Убеждение:</label>
                    <br>
                    <input type="number" id="persuasion" name="persuasion" value="${persuasion}" readonly>
                </div>
                <div class="input-field">
                    <label for="religion">Религия:</label>
                    <br>
                    <input type="number" id="religion" name="religion" value="${religion}" readonly>
                </div>
                <div class="input-field">
                    <label for="sleight-of-hand">Ловкость рук:</label>
                    <br>
                    <input type="number" id="sleight-of-hand" name="sleight-of-hand" value="${sleight_of_hand}" readonly>
                </div>
            </div>
            <div class="box-skills-by-three">
                <div class="input-field">
                    <label for="stealth">Скрытность:</label>
                    <br>
                    <input type="number" id="stealth" name="stealth" value="${stealth}" readonly>
                </div>
                <div class="input-field">
                    <label for="survival">Выживание:</label>
                    <br>
                    <input type="number" id="survival" name="survival" value="${survival}" readonly>
                </div>
                <div class="input-field">
                    <label for="animal-handling">Обращение с животными:</label>
                    <br>
                    <input type="number" id="animal-handling" name="animal-handling" value="${animal_handling}" readonly>
                </div>
            </div>
            <!-- Add more fields as needed -->
        </div>
    </div>
    <div class="box">
        <h2>Health</h2>
        <div class="input-field">
            <label for="hitPoints">Здоровье:</label>
            <br>
            <input type="number" id="hitPoints" name="hitPoints" value="${hitPoints}" readonly>
        </div>
        <div class="input-field">
            <label for="temp">Временные ХП:</label>
            <br>
            <input type="number" id="temp" name="temp">
        </div>
        <div class="input-field">
            <label for="damage">Урон:</label>
            <br>
            <input type="number" id="damage" name="damage">
        </div>
        <!-- Add more boxes for other sections -->
    </div>
</div>

</body>
</html>