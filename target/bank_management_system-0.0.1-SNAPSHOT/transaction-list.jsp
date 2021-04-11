<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %> 
<html>
<head>
 <title>Transaction Management Application</title>
</head>
<body>
<h3>
</h3>
    <table border="1" cellpadding="5" style="margin-top: 5%;">
        <tr>
            <th>ID</th>
            <th>Account</th>
            <th>Date</th>
            <th>Deposit</th>
            <th>Withdraw</th>
            <th>Balance</th>
        </tr>
        <c:forEach var="transaction" items="${listTransaction}">
            <tr>
                <td><c:out value="${transaction.id}" /></td>
                <td><c:out value="${transaction.account.accountName}" /></td>
                <td><c:out value="${transaction.date}" /></td>
                 <td><c:out value="${transaction.deposit}" /></td>
                  <td><c:out value="${transaction.withdraw}" /></td>
                   <td><c:out value="${transaction.balance}" /></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>