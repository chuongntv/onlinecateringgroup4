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
        <%@include file="../include/header.jsp" %>
        <a href="${pageContext.request.contextPath}/checkbooking/listbooking.htm" class="btn btn-primary">Show all bills Waiting</a>
        <a href="${pageContext.request.contextPath}/checkbooking/showlistshipping.htm" class="btn btn-primary">Show all bills shipping</a>
        <a href="${pageContext.request.contextPath}/checkbooking/showlistcompleted.htm" class="btn btn-primary">Show all bills Completed</a>
        <a href="${pageContext.request.contextPath}/checkbooking/showlistcancel.htm" class="btn btn-primary">Show all bills Cancel</a>
        <h1>List Booking Cancel</h1>
        <table class="table table-striped">
            <tr>
                <td><strong>Caterer Name</strong></td>
                <td><strong>Invoice Date</strong></td>
                <td><strong>Delivery Date</strong></td>
                <td><strong>Status</strong></td>
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
        <%@include file="../include/footer.jsp" %>
</html>
