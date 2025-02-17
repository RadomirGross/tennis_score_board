    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html; charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Завершенные матчи</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
    <h1>Завершенные матчи</h1>

    <form action="/matches" method="get">
        <label>
            Найти матчи игрока:
            <input type="text" name="filter_by_player_name" value="<%=
            request.getAttribute("filter_by_player_name")!=null?
            request.getAttribute("filter_by_player_name") : ""%>">
        </label>
        <button type="submit">Найти</button>
        <c:if test="${not empty errorMessage}">
            <div class="error-message">
                    ${errorMessage}
            </div>
        </c:if>
    </form>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Игрок 1</th>
            <th>Игрок 2</th>
            <th>Победитель</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="match" items="${matches}">
            <tr>
                <td>${match.id}</td>
                <td>${match.player1.name}</td>
                <td>${match.player2.name}</td>
                <td><b>${match.winner.name}</b></td>
            </tr>
        </c:forEach>
        <c:forEach var="i" begin="${matches.size()}" end="${page_size-1}">
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

    <div class="pagination">
        <button onclick="location.href='matches?page_number=${page_number - 1}&filter_by_player_name=${filter_by_player_name}'"
                class="btn"
        ${page_number <= 1 ? 'disabled' : ''}>
            Назад
        </button>
        <span>Страница ${page_number}</span>
        <button onclick="location.href='matches?page_number=${page_number + 1}&filter_by_player_name=${filter_by_player_name}'"
                class="btn"
        ${is_last_page ? 'disabled' : ''}>
            Вперед
        </button>
    </div>
    <button onclick="window.location.href='/'">Вернуться на главную</button>


    </body>
    </html>