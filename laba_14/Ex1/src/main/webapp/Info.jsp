<%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 5/30/2024
  Time: 9:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Info</title>
</head>
<body>
<c:out value="${price}"/><br/>



<button type="submit" name="sum"> sum </button><br/>
<button type="submit" name="add"> add 100 </button><br/>
<button type="submit" name="get"> get 50 </button> <br/>

<%--<table>
    <tr>
        <th>Имя</th>
        <th>Количество голосов</th>
    </tr>
    <c:forEach var="el" items="${lst}" varStatus="status">
        <tr>
            <td><c:out value="${el.name}"/></td>
            <td><c:out value="${el.golos}"/></td>
        </tr>
    </c:forEach>
</table>--%>

<a href="index.jsp"> Go back on page</a>
</body>
</html>
