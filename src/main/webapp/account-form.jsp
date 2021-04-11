<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %> 
<html>
<head>
 <title>Account Management Application</title>
</head>
<body>
  <c:if test="${account != null}">
   <form action="account" method="post">
        </c:if>
        <c:if test="${account == null}">
   <form action="account" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
             <h2>
              <c:if test="${account != null}">
              <input type="hidden" name="action" value="update" />
               Edit Account
              </c:if>
              <c:if test="${account == null}">
               <input type="hidden" name="action" value="insert" />
               Add New Account
              </c:if>
             </h2>
            </caption>
          <c:if test="${account != null}">
           <input type="hidden" name="id" value="<c:out value='${account.id}' />" />
          </c:if>
          <tr>
                <th>Account Name: </th>
                <td>
                 <input type="text" name="accountName" size="45"
                   value="<c:out value='${account.accountName}' />"
                  />
                </td>
            </tr>
          <tr>
                <th>Account Number: </th>
                <td>
                 <input type="text" name="accountNumber" size="45"
                   value="<c:out value='${account.accountNumber}' />"
                 />
                </td>
            </tr>     
            <tr>
                <th>Balance: </th>
                <td>
                 <input type="text" name="balance" size="45"
                   value="<c:out value='${account.balance}' />"
                  />
                </td>
            </tr>
            
            <tr>
                <th>Account Type: </th>
                <td>
                 <input type="text" name="type" size="45"
                   value="<c:out value='${account.type}' />"
                 />
                </td>
            </tr>
<!--             <tr> -->
<!--                 <th>Brnach Code: </th> -->
<!--                 <td> -->
<!--                  <input type="text" name="branch_code" size="45" -->
<%--                    value="<c:out value='${account.branch.branch_code}' />" --%>
<!--                  /> -->
<!--                 </td> -->
<!--             </tr> -->
            <tr>
                <th>Branch Name: </th>
                <td>
                 <input type="text" name="name" size="45"
                   value="<c:out value='${account.branch.name}' />"
                 />
                </td>
            </tr>
            <tr>
                <th>IBAN: </th>
                <td>
                 <input type="text" name="iban" size="45"
                   value="<c:out value='${account.branch.iban}' />"
                 />
                </td>
            </tr>
            <tr>
                <th>Address: </th>
                <td>
                 <input type="text" name="address" size="45"
                   value="<c:out value='${account.branch.address}' />"
                 />
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