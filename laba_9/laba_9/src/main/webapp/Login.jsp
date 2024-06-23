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
</head>
<body>
<h2>Login</h2>
<form action="LoginServlet" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username"><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br>
    <button type="submit">Login</button><br/><br/>
</form>

<% if (request.getParameter("error") != null) { %>
<p style="color: red;">Invalid username or password.</p>
<% } %>

</body>
</html>
