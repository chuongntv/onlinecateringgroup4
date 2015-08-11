<%-- 
    Document   : supplierinvoice_listbill
    Created on : Aug 6, 2015, 3:55:46 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h1>Choose Delivery Date</h1><br>
        <spring:form action="../ordercaterer/listMyBill.htm" commandName="invoice" method="POST">
            <input type="hidden" name="id" value="${invoice.id}"/><br>
            <br><h3>Delivery Date:  <input name="deliveryDate" type="date"/></h3>
            <input type="submit" value="Submit" class="btn btn-primary">
        </spring:form> 
            <table class="table table-striped">
            <tr>
                <td><strong>Material Name</strong></td>
                <td><strong>Price/Unit</strong></td>
                <td><strong>Quantity</strong></td>
                <td><strong>Total Price</strong></td>
            </tr>
            <c:forEach items="${childinvoices}" var="child">
                <tr>
                    <td>${child.materialName}</td>
                    <td>${child.materialPricePerUnit}</td>
                    <td>${child.quantity}</td>
                    <td>${child.materialPricePerUnit * child.quantity}</td>  
                </tr> 
            </c:forEach>
        </table>
            <%@include file="../include/footer.jsp" %>
    </body>
</html>
