<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %> 
<html>
<head>
 <title>Account Management Application</title>
</head>
<body>
<h3>
</h3>

    <table border="1" cellpadding="5" style="margin-top: 5%;">
        <tr>
            <th>ID</th>
            <th>Account Name</th>
            <th>Account Number</th>
             <th>Balance</th>
              <th>Account Type</th>
                <th>Branch Name</th>
                 <th>Branch IBAN</th>
                  <th>Branch Address</th>
                  <th>User Name</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="account" items="${listAccount}">
            <tr>
                <td><c:out value="${account.id}" /></td>
                <td><c:out value="${account.accountName}" /></td>
                <td><c:out value="${account.accountNumber}" /></td>
                <td><c:out value="${account.balance}" /></td>
                <td><c:out value="${account.type}" /></td>
                <td><c:out value="${account.branch.name}" /></td>
                <td><c:out value="${account.branch.iban}" /></td>
                <td><c:out value="${account.branch.address}" /></td>
                <td><c:out value="${account.user.name}" /></td>
                <td>
                 <a href="?action=edit&id=<c:out value='${account.id}' />">Edit</a>
                 &nbsp;&nbsp;&nbsp;&nbsp;
                 <a href="?action=delete&id=<c:out value='${account.id}' />">Delete</a>                     
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>