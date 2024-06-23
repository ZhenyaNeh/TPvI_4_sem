<%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 4/28/2024
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
    <link href="styles/loginStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="cont">
    <div class="title" >Login</div>
    <form action="${pageContext.servletContext.contextPath}/controller?command=login" method="post" class="log-in" autocomplete="off">
        <label for="username">Username:</label>
        <input type="text" id="username" name="login" placeholder="field string"><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="field string"><br>

        <div class="adressBox">
            <button class="regButton" type="submit">Login</button><br/>
            <% if (request.getParameter("error") != null) { %>
            <p style="color: red;">Invalid username or password.</p>
            <% } %>
        </div>
    </form>
</div>
</body>
</html>
