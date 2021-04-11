<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %> 
<html>
<head>
 <title>Account Management Application</title>
</head>
<body>
   <form action="transfer" method="post">
        <table border="1" cellpadding="5">
          <tr>
                <th>Credit Account: </th>
                <td>
		        <select name="creditAccount">
		        <option value="">-</option>
		            <c:forEach var="account" items="${accounts}">
		                <option value="${account.id}">${account.accountName}</option>
		            </c:forEach>
		        </select>
                </td>
            </tr>
          <tr>
                <th>Debit Account: </th>
                <td>
                 <select name="debitAccount">
                 <option value="">-</option>
		            <c:forEach var="account" items="${accounts}">
		                <option value="${account.id}">${account.accountName}</option>
		            </c:forEach>
		        </select>
                </td>
            </tr>     
            <tr>
                <th>amount: </th>
                <td>
                 <input type="text" name="amount" size="45" />
                </td>
            </tr>
            <tr>
             <td colspan="2" align="center">
              <input type="submit" value="Save" />
             </td>
            </tr>
        </table>
        </form>
</body>
</html>