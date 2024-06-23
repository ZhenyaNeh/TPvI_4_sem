<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div id="head" style=" font-size: 18px; margin: 4px; font-weight: bold;">
  <p>Добро пожаловать, ${name}!</p>
  <p>Role: <%= session.getAttribute("role") %></p>
  <p>Current Date: <%= new Date() %></p>

</div>
</body>
</html>