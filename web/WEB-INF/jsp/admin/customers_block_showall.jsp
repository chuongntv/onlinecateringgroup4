<%-- 
    Document   : customers_block_showall
    Created on : Aug 6, 2015, 2:35:32 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Show All Customers Block</h1>
        <a href="${pageContext.request.contextPath}/customers/listCustomers.htm">Block Customers</a>
        <table border="1">
            <tr>
                <td>Name</td>
                <td>Full Name</td>
                <td>Email</td>
                <td>Phone</td>
                <td>Option</td>
            </tr>
            <c:forEach items="${customersBlocked}" var="customer">
                <tr>
                    <td>${customer.username}</td>
                    <td>${customer.fullName}</td>
                    <td>${customer.email}</td>
                    <td>${customer.phoneNumber}</td>  
                    <td><a href="${pageContext.request.contextPath}/customers/active/${customer.id}.htm"> Active</a></td>
                </tr> 
            </c:forEach>
        </table>
    </body>
</html>
