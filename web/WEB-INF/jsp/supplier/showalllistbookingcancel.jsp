<%-- 
    Document   : showalllistbookingcompleted
    Created on : Aug 7, 2015, 4:47:47 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Booking Cancel</title>
    </head>
    <body>
        <h1>List Booking Completed</h1>
        <table border="1">
            <tr>
                <td>Caterer Name</td>
                <td>Invoice Date</td>
                <td>Delivery Date</td>
                <td>Status</td>
            </tr>
            <c:forEach items="${listbookingscancel}" var="booking">
                <tr>
                    <td>${booking.caterers.catererName}</td>
                    <td>${booking.invoiceDate}</td>
                    <td>${booking.deliveryDate}</td>
                    <td>${booking.status}</td>
                </tr> 
            </c:forEach>
        </table>
    </body>
</html>
