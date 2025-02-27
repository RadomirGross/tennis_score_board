<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Счёт матча</title>
    <link rel="stylesheet" href="css/styles.css">
    <meta name="viewport" content="width=device-width, initial-scale=0.8">
    </head>
</head>
<body>

<h1>Текущий матч </h1>
<p>${uuid}</p>


    <table class="scoreboard">
        <tr>
            <th>Игрок</th>
            <th>Сеты</th>
            <th>Геймы</th>
            <th>Очки</th>
            <th>Выиграл очко</th>
        </tr>
        <tr>
            <td>${player1.name}</td>
            <td>${matchScore.player1Sets}</td>
            <td>${matchScore.player1Games}</td>
            <td class="${matchScore.gameScore.advantagePlayer1 ? 'advantage' : ''}">
                    ${matchScore.gameScore.player1Points.score}
            </td>
            <td>
                <form action="/match-score" method="post">
                    <input type="hidden" name="player_id" value="1">
                    <input type="hidden" name="match_uuid" value="${uuid}">
                    <button class="win-button" type="submit" ${matchFinished ? 'disabled' : ''}>
                        Игрок 1 выиграл текущее очко
                    </button>
                </form>
            </td>
        </tr>
        <tr>
            <td>${player2.name}</td>
            <td>${matchScore.player2Sets}</td>
            <td>${matchScore.player2Games}</td>
            <td class="${matchScore.gameScore.advantagePlayer2 ? 'advantage' : ''}">
                    ${matchScore.gameScore.player2Points.score}
            </td>
            <td>
                <form action="/match-score" method="post">
                    <input type="hidden" name="player_id" value="2">
                    <input type="hidden" name="match_uuid" value="${uuid}">
                    <button class="win-button" type="submit" ${matchFinished ? 'disabled' : ''}>
                        Игрок 2 выиграл текущее очко
                    </button>
                </form>
            </td>
        </tr>
        <tr>
            <td colspan="5" >
               <span class="status-box ${matchScore.gameScore.deuced ? 'active-deuce' : ''}">
            Deuce
        </span>
                <c:if test="${matchScore.gameScore.advantagePlayer1}">Преимущество ${player1.name}</c:if>
                <c:if test="${matchScore.gameScore.advantagePlayer2}">Преимущество ${player2.name}</c:if>
            </td>
        </tr>

        <tr>

            <td colspan="5">
        <span class="status-box ${matchScore.tieBreak ? 'active-tie-break' : ''}">
            Tie Break
        </span>
            </td>
        </td>
        </tr>
    </table>
<%--</c:if>--%>

<c:if test="${matchFinished}">
    <h2>Матч завершен!</h2>
    <h3>Победитель: ${matchWinner.name}</h3>
    <form action="/match-score" method="post">
        <input type="hidden" name="saveMatch" value="true">
        <input type="hidden" name="match_uuid" value="${uuid}">
        <button type="submit">Вернуться на главную</button>
    </form>
</c:if>
</body>
</html>
