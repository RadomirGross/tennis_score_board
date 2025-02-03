<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Новый матч</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<h1>Новый теннисный матч</h1>



<form action="/new-match" method="post">
    <label>
        Имя игрока 1:
        <input type="text" name="player1" required>
    </label>
    <label>
        Имя игрока 2:
        <input type="text" name="player2" required>
    </label>

    <button type="submit">Начать матч</button>

    <c:if test="${not empty errorMessage}">
        <div class="error-message">
            ${errorMessage}
        </div>
    </c:if>
</form>
</body>
</html>