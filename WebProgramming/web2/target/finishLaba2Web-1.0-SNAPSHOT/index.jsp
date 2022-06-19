<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="main.css">
    <title>Second WEB</title>
</head>
<body>
<header align="center">
    Лабораторная работа №2 <br>
    Зинченко Константин Сергеевич <br>
    Вариант №15507
</header>
<div class="main_content">
    <div class="up">
        <div class="left">
            <div class="left_1">
                <b>Выберите коэфицент R:</b> <br> <br> <br>
                1.0 <input type="radio" value="1" name="rValue" id="radio1" class="rValue"> <br> <br>
                1.5 <input type="radio" value="1.5" name="rValue" id="radio2" class="rValue"> <br> <br>
                2.0 <input type="radio" value="2" name="rValue" id="radio3" class="rValue"> <br> <br>
                2.5 <input type="radio" value="2.5" name="rValue" id="radio4" class="rValue"> <br> <br>
                3.0 <input type="radio" value="3" name="rValue" id="radio5" class="rValue">
                <br> <br> <br>
                <b>Кнопки обслуживания:</b>
                <br> <br>
                <div class="buttons">
                    <div class="button_clear">
                        <table id="alerta">
                        </table>
                    </div>
                    <div class="button_send" onclick="collect()">
                        <button id="send"> Отправить </button>
                    </div>
                </div>
            </div>
            <div class="left_2">
                <b> Выберите координату Y:</b> <br> <br>
                <select id="yValue">
                    <option value="-5"> -5 </option>
                    <option value="-4"> -4 </option>
                    <option value="-3"> -3 </option>
                    <option value="-2"> -2 </option>
                    <option value="-1"> -1 </option>
                    <option value="0">   0 </option>
                    <option value="1">   1 </option>
                    <option value="2">   2 </option>
                    <option value="3">   3 </option>
                </select>
                <br> <br>
                <br> <br>
                <br> <br>
                <br> <br>
                <br> <br>
                <b>Введите координату X:</b> <br> <br>
                <input type="text" id="xValue" placeholder="-5...5">
            </div>
        </div>
        <div class="right">
            <svg  width="400" height="400" >
                <line x1="0" y1="200" x2="400" y2="200" stroke="black"></line>
                <line x1="200" y1="0" x2="200" y2="400" stroke="black"></line>
                <text x="200" y="0" class="mal">Y</text>
                <text x="400" y="200" class="mal">X</text>
                <text x="200" y="40" class="mal">-- R</text>
                <text x="200" y="120" class="mal">-- R/2</text>
                <text x="200" y="280" class="mal">-- -R/2</text>
                <text x="200" y="360" class="mal">-- -R</text>
                <text x="40" y="200" class="mal">|</text>
                <text x="120" y="200" class="mal">|</text>
                <text x="200" y="200" class="mal">0</text>
                <text x="280" y="200" class="mal">|</text>
                <text x="360" y="200" class="mal">|</text>
                <text x="28" y="200" class="mal">-R</text>
                <text x="100" y="200" class="mal">-R/2</text>
                <text x="260" y="200" class="mal">R/2</text>
                <text x="340" y="200" class="mal">R</text>
                <rect fill="#4169E1" x="120" y="200" width="80" height="158" fill-opacity="0.7"></rect>
                <polygon fill="#4169E1" fill-opacity="0.7" points="200,115 120,200 200,200"></polygon>
                <path d="M 200 114 C  270 130 270 180 285 200 L 200 200 Z" fill="#4169E1" fill-opacity="0.7" ></path>
                <circle id="point" r="5" cx="150" cy="150" fill-opacity="0.7" fill="red" stroke="firebrick" visibility="hidden"></circle>
            </svg>
        </div>
    </div>
    <div class="down">
        <br> <br> <br>
        <b id="history_text">История запросов:</b>
        <br> <br>
        <div id="answer">
            <table class="result-table" id = "result_table">
                <thead>
                <tr class="result-table-header">
                    <th class="result-table-th">Значение Х</th>
                    <th class="result-table-th">Значение Y</th>
                    <th class="result-table-th">R</th>
                    <th class="result-table-th">Время запроса</th>
                    <th class="result-table-th" id="result_table_result_sell">Результат</th>
                </tr>
                </thead>
                <tbody class="result-table-body">
                <%
                    HttpSession httpSession = request.getSession();
                    ArrayList<String> resultList = (ArrayList<String>) httpSession.getAttribute("resultList");
                    if (resultList != null)
                        for (String responseInfo : resultList)
                            out.println(responseInfo);
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="main.js"></script>
</body>
</html>