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
   <link href="styles/registrationStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="cont">
        <div class="title" >Registration</div>
        <form action="Registration" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" placeholder="field string"><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="field string"><br>

            <div class="adressBox">
                <button class="regButton" type="submit">Register</button><br/><br/>

                <a href="Login.jsp">if you have acc => log in</a><br/><br/>

                <% if (request.getParameter("valid") != null) { %>
                <p style="color: indianred;"><%= session.getAttribute("message") %></p>
                <% } %>
            </div>
        </form>
    </div>
</body>
</html>
