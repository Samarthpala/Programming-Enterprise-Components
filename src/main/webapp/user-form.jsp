<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %> 
<html>
<head>
 <title>User Management Application</title>
</head>
<body>
  <c:if test="${user != null}">
   <form action="user" method="post">
        </c:if>
        <c:if test="${user == null}">
   <form action="user" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
             <h2>
              <c:if test="${user != null}">
              <input type="hidden" name="action" value="update" />
               Edit User
              </c:if>
              <c:if test="${user == null}">
               <input type="hidden" name="action" value="insert" />
               Add New User
              </c:if>
             </h2>
            </caption>
          <c:if test="${user != null}">
           <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
          </c:if>
          <tr>
                <th>Salution: </th>
                <td>
                 <input type="text" name="salution" size="45"
                   value="<c:out value='${user.salution}' />"
                 />
                </td>
            </tr>     
            <tr>
                <th>Name: </th>
                <td>
                 <input type="text" name="name" size="45"
                   value="<c:out value='${user.name}' />"
                  />
                </td>
            </tr>
            <tr>
                <th>User Name: </th>
                <td>
                 <input type="text" name="username" size="45"
                   value="<c:out value='${user.username}' />"
                  />
                </td>
            </tr>
            <tr>
                <th>Email: </th>
                <td>
                 <input type="text" name="email" size="45"
                   value="<c:out value='${user.email}' />"
                 />
                </td>
            </tr>
            <tr>
                <th>Address: </th>
                <td>
                 <input type="text" name="address" size="45"
                   value="<c:out value='${user.address}' />"
                 />
                </td>
            </tr>
            <tr>
                <th>Phone Number: </th>
                <td>
                 <input type="text" name="number" size="45"
                   value="<c:out value='${user.phoneNumber}' />"
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