<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Счёт матча</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<h1>Текущий матч</h1>

<c:choose>
    <c:when test="${matchFinished}">
        <h2>Матч завершен!</h2>
        <h3>Победитель: ${winner.name}</h3>
        <button onclick="window.location.href='/'">Вернуться на главную</button>
    </c:when>
    <c:otherwise>
        <div class="match-score">
            <div class="player player1">
                <h2>${player1.name}</h2>
                <div class="current-score">${player1.score}</div>
                <form action="/match-score" method="post">
                    <input type="hidden" name="player_id" value="1">
                    <input type="hidden" name="match_uuid" value="${uuid}">
                    <button type="submit">Игрок 1 выиграл очко</button>
                </form>
            </div>
            <div class="player player2">
                <h2>${player2.name}</h2>
                <div class="current-score">${player2.score}</div>
                <form action="/match-score" method="post">
                    <input type="hidden" name="player_id" value="2">
                    <input type="hidden" name="match_uuid" value="${uuid}">
                    <button type="submit">Игрок 2 выиграл очко</button>
                </form>
            </div>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>
