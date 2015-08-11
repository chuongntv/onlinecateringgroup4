<%-- 
    Document   : index
    Created on : Jul 19, 2015, 10:24:31 AM
    Author     : Nathan Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Management</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h2>Account Management</h2>
        <h3>Welcome ${user.username}</h3>
        Give me a id for userId: ${user.id}
        <a  href="${pageContext.request.contextPath}/account/update.htm"> Update</a>
        <table border="1">
            <tr class="info">                
                <th>ID</th>                
                <th>User Name</th>                               
                <th>Password</th>
                <th>Email</th>
                <th>User Group</th>
                <th>Full Name</th>
                <th>Address</th>
                <th>Date Of Birth</th>
                <th>Phone Number</th>
                <th>Status</th>      
                <th></th> 
            </tr>
            <c:forEach items="${accounts}" var="account">
                <tr>
                    <td>${account.id}</td>                    
                    <td>${account.username}</td>
                    <th>${account.password}</th>
                    <th>${account.email}</th>
                    <th>${account.userGroup}</th>
                    <th>${account.fullName}</th>
                    <th>${account.address}</th>
                    <th>${account.dateOfBirth}</th>
                    <th>${account.phoneNumber}</th>
                    <th>${account.status}</th>  
                    <td><a data-toggle="tooltip" title="Edit" href="${pageContext.request.contextPath}/account/edit/${account.id}.htm"><span class="glyphicon glyphicon-edit"></span> Edit</a></td>
                </tr> 
            </c:forEach>
        </table>                      
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
