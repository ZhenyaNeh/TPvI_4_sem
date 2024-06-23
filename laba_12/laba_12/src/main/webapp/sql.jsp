<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style/tableStyle.css"/>
    <title>SQL tags</title>
</head>
<body>
<sql:setDataSource var = "snapshot" driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
                   url = "jdbc:sqlserver://localhost:1434;database=LIBRARY;encrypt=true;trustServerCertificate=true;IntegratedSecurity=false"
                   user = "User1"  password = "1234"/>

<sql:query dataSource = "${snapshot}" sql = "select * from PEOPLE" var = "result" />
<table>
    <tr>
        <th>Id</th>
        <th>People</th>
        <th>Country</th>
        <th>Count</th>
    </tr>
    <c:forEach items="${result.rows}" var="res">
        <tr>
            <td><c:out value="${res.ID}"/></td>
            <td><c:out value="${res.PEOPLE}"/></td>
            <td><c:out value="${res.PEOPLE_COUNTRY}"/></td>
            <td><c:out value="${res.PEOPLE_COUNT}"/></td>
        </tr>
    </c:forEach>
</table>

<sql:query dataSource="${snapshot}" var="user" >
    SELECT * FROM USERS where USER_LOG = ?
    <sql:param value="User1" />
</sql:query>

<table>
    <tr>
        <th>Id</th>
        <th>Логин</th>
        <th>Роль</th>
    </tr>
    <c:forEach items="${user.rows}" var="us">
        <tr>
            <td><c:out value="${us.ID}"/></td>
            <td><c:out value="${us.USER_LOG}"/></td>
            <td><c:out value="${us.USER_ROL}"/></td>
        </tr>
    </c:forEach>
</table>


<sql:transaction dataSource = "${snapshot}">
    <sql:update var = "count">
        UPDATE USERS SET USER_LOG = 'user11' WHERE ID = 5
    </sql:update>

    <sql:update var = "count">
        UPDATE USERS SET USER_LOG = 'New Login' WHERE ID = 5
    </sql:update>
</sql:transaction>

<sql:query dataSource = "${snapshot}" var = "user">
    SELECT * from USERS;
</sql:query>

<table>
    <tr>
        <th>Id</th>
        <th>Логин</th>
        <th>Роль</th>
    </tr>
    <c:forEach items="${user.rows}" var="us">
        <tr>
            <td><c:out value="${us.ID}"/></td>
            <td><c:out value="${us.USER_LOG}"/></td>
            <td><c:out value="${us.USER_ROL}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
