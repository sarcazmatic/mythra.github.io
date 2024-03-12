<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Твою мать</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: space-around;
            align-items: center;
            background: linear-gradient(to right, #172936, #3f1334);
            color: white;
            font-family: Arial, sans-serif;
        }

        .shiny-text {
            font-size: 36px;
            font-weight: bold;
            background: linear-gradient(to right, #ffcc00, #c40eb4, #005eff, #ff6600, #ffcc00);
            background-size: 200% auto;
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            animation: shine 3s linear infinite;
            padding: 5px;
        }

        .form-container {
            width: 300px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #fffbfb;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            text-align: center;
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
            color: #005eff;
        }

        .form-group input {
            width: calc(100% - 20px);
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .custom-button {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .custom-button:hover {
            background-color: #0056b3;
        }

        @keyframes shine {
            0% {
                background-position: 0 0;
            }
            100% {
                background-position: 200% 0;
            }
        }
    </style>
</head>
<body>
<div class="shiny-text">Welcome ti MinDnD!</div>
<div class="shiny-text">Login</div>
<div class="form-container">
    <form action="login" method="post">
        <div class="form-group">
            <label for="name-login">Login:</label>
            <input type="text" id="name-login" name="name" placeholder="Enter your login" required>
            <input type="password" id="password-login" name="password" placeholder="Enter your password" required>
        </div>
        <button type="submit" class="custom-button">Submit</button>
    </form>
</div>
<div class="shiny-text">Register</div>
<div class="form-container">
    <form id="reg-form" action="register" method="post">
        <div class="form-group">
            <label for="name-reg">Register:</label>
            <input type="text" id="name-reg" name="name-reg" placeholder="Enter your login" required>
            <input type="email" id="email-register" name="email" placeholder="Enter your email" required>
            <input type="password" id="password-register" name="password" placeholder="Enter your password" required>
        </div>
        <button id="submit-register" type="submit" class="custom-button">Submit</button>
    </form>
</div>
<div class="shiny-text">${response}</div>

<script>
    document.getElementById('submit-register').addEventListener('click', function() {
        document.getElementById('reg-form').action = document.getElementById('name-reg').value.toString()+"/register";
    })
</script>

</body>
</html>