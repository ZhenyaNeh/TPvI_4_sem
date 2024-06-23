<%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 4/22/2024
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h2>Registration</h2>
    <form action="Registration" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><%= session.getAttribute("Name") %><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><%= session.getAttribute("Name") %><br>
        <button type="submit">Register</button>
    </form>
    <a href="Login.jsp">if you have acc => log in</a><br/><br/>

    <% if (request.getParameter("valid") != null) { %>
    <p style="color: indianred;"><%= session.getAttribute("message") %></p>
    <% } %>

    <% if (request.getParameter("valid") == null) { %>
    <p style="color: lightgreen;"><%= session.getAttribute("message") %></p>
    <% } %>
</body>
</html>
