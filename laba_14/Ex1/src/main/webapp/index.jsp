<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Online-голосование</h1>
<form action="Servlet" method="post">
    <div>
        <label for="login">login:</label>
        <input type="text" id="login" name="login" placeholder="field login"><br>

        <button type="submit" name="action">LOGIN</button>
    </div>
</form>
</body>
</html>