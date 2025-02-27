<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Новый матч</title>
    <link rel="stylesheet" href="css/styles.css">
    <meta name="viewport" content="width=device-width, initial-scale=0.8">
</head>
<body>
<h1>Новый теннисный матч</h1>



<form action="${pageContext.request.contextPath}/new-match" method="post">
    <label>
        Имя игрока 1:
        <input type="text" name="player1" value="<%=
            request.getAttribute("player1Name")!=null?
            request.getAttribute("player1Name") : ""%>" required>
    </label>
    <label>
        Имя игрока 2:
        <input type="text" name="player2" value="<%=
            request.getAttribute("player2Name")!=null?
            request.getAttribute("player2Name") : ""%>" required>
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