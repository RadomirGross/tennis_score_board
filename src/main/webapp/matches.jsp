<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Завершенные матчи</title>
    <link rel="stylesheet" href="css/styles.css">
    <meta name="viewport" content="width=device-width, initial-scale=0.8">
    <script>
        function goToPage(pageNumber) {
            var url = 'matches?page_number=' + pageNumber;

            <c:if test="${not empty filter_by_player_name}">
            url += '&filter_by_player_name=' + encodeURIComponent('${filter_by_player_name}');
            </c:if>

            location.href = url;
        }
    </script>
</head>
<body>
<h1>Завершенные матчи</h1>

<form action="/matches" method="get">
    <label>
        Найти матчи игрока:
        <input type="text" name="filter_by_player_name" value="${filter_by_player_name}">
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
    <c:set var="remaining" value="${page_size - matches.size()}" />
    <c:if test="${remaining > 0}">
        <c:forEach var="i" begin="1" end="${remaining}">
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>

<div class="pagination">
    <button onclick="goToPage(${page_number - 1})"
            class="btn"
    ${page_number <= 1 ? 'disabled' : ''}>
        Назад
    </button>
    <span>Страница ${page_number}</span>
    <button onclick="goToPage(${page_number + 1})"
            class="btn"
    ${is_last_page ? 'disabled' : ''}>
        Вперед
    </button>
</div>
<button onclick="window.location.href='/'">Вернуться на главную</button>
</body>
</html>