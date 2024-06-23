<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>laba_9</title>
</head>
<body>
<h1>"Laba_9"</h1><br/>
<a href="task2">Task_2</a><br/><br/>
<a href="Registration.jsp">Task_3</a><br/><br/>

<form action="ServletFirst" method="get">
  <input type="submit" value="TASK 6 GET" />
</form>
<form action="ServletFirst" method="post">
  <input type="submit" value="TASK 6 POST" />
</form><br/><br/>

<form action="Servlet1" method="get">
  <input type="text" autocomplete="off" name="name" placeholder="Введите имя" />
  <br/>
  <input type="submit" value="TASK 7" />
</form>
</body>
</html>