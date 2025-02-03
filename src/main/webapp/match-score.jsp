<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    Map<String, Object> attributes = new HashMap<>();
    for (Enumeration<String> e = request.getAttributeNames(); e.hasMoreElements(); ) {
        String name = e.nextElement();
        attributes.put(name, request.getAttribute(name));
    }
    out.println("<pre>" + attributes + "</pre>");
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Счёт матча</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<h1>Текущий матч</h1>

<c:if test="${matchFinished}">
    <h2>Матч завершен!</h2>
    <h3>Победитель: ${matchWinner != null ? matchWinner.name : 'Нет победителя'}</h3>
    <form action="/match-score" method="post">
        <input type="hidden" name="saveMatch" value="true">
        <input type="hidden" name="match_uuid" value="${uuid}">
        <button type="submit">Вернуться на главную2</button>
    </form>
</c:if>

<c:if test="${not matchFinished}">
    <div class="match-score">
        <div class="player player1">
            <h2>${player1.name}</h2>
            <div class="set-score">Сеты: ${matchScore.player1Sets}</div>
            <div class="game-score">Геймы: ${matchScore.player1Games}</div>
            <div class="current-score">Очки: ${matchScore.gameScore.player1Points}</div>
            <form action="/match-score" method="post">
                <input type="hidden" name="player_id" value="1">
                <input type="hidden" name="match_uuid" value="${uuid}">
                <button type="submit">Игрок 1 выиграл очко</button>
            </form>
        </div>

        <div class="player player2">
            <h2>${player2.name}</h2>
            <div class="set-score">Сеты: ${matchScore.player2Sets}</div>
            <div class="game-score">Геймы: ${matchScore.player2Games}</div>
            <div class="current-score">Очки: ${matchScore.gameScore.player2Points}</div>
            <form action="/match-score" method="post">
                <input type="hidden" name="player_id" value="2">
                <input type="hidden" name="match_uuid" value="${uuid}">
                <button type="submit">Игрок 2 выиграл очко</button>
            </form>
        </div>
    </div>
</c:if>

</body>
</html>
