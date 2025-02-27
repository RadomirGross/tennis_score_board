<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Теннисное Табло</title>
    <link rel="stylesheet" href="css/styles.css">
    <meta name="viewport" content="width=device-width, initial-scale=0.8">

</head>
<body>
<h1>Теннисное Табло</h1>
<nav>
    <form action="${pageContext.request.contextPath}/new-match">
        <button type="submit">Создать новый матч</button>
    </form>
    <form action="${pageContext.request.contextPath}/matches">
        <button type="submit">Завершенные матчи</button>
    </form>
</nav>
</body>
</html>