<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 4/28/2024
  Time: 1:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="styles/welcome.css" rel="stylesheet" type="text/css">
    <title>Welcome Page</title>
</head>
<body>
    <header>
        <jsp:include page="header.jsp"/>
    </header>

    <div id="container">
        <div id="tableDiv">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>People</th>
                    <th>Country</th>
                    <th>Count</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${classes}" var="cl">
                <tr>
                    <td>${cl.getClassId()}</td>
                    <td>${cl.getClassName()}</td>
                    <td>${cl.getClassDay()}</td>
                    <td>${cl.getClassHours()}</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


        <div id="add-remove-div">
            <form id="add-form" method="post" action="${pageContext.servletContext.contextPath}/controller?command=add_new_class">
                <h3 style="font-weight: bold">Добавить занятие</h3>
                <input type="text" placeholder="People" name="people" autocomplete="off"/>
                <input type="text" placeholder="Country" name="country" autocomplete="off"/>
                <input type="text" placeholder="Count" name="count" autocomplete="off"/>
                <button type="submit">Добавить</button>
            </form>
            <br/><br/>
            <form id="remove-form" method="post" action="${pageContext.servletContext.contextPath}/controller?command=remove_class">
                <h3 style="font-weight: bold">Удалить занятие</h3>
                <input type="text" placeholder="ID" name="id" autocomplete="off"/>
                <button type="submit">Удалить</button>
            </form>
        </div>

    </div>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
