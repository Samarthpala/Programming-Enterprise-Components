<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %> 
<html>
<head>
 <title>User Management Application</title>
</head>
<body>
<h3>
</h3>
    <table border="1" cellpadding="5" style="margin-top: 5%;">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>UserName</th>
            <th>Accounts</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.name}" /></td>
                <td><c:out value="${user.username}" /></td>
                <td>
                	<div style="border-bottom: 1px solid black;">Name | Balance</div>
                	<c:forEach var="account" items="${user.accounts}">
	                	<div style="border-bottom: 1px solid black;">${account.accountName} | ${account.balance}</div>
                	</c:forEach>
                </td>
                <td>
                 <a href="?action=edit&id=<c:out value='${user.id}' />">Edit</a>
                 &nbsp;&nbsp;&nbsp;&nbsp;
                 <a href="?action=delete&id=<c:out value='${user.id}' />">Delete</a>                     
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>