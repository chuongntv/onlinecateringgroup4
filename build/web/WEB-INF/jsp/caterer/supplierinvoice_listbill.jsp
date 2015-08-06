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
        <h1>Choose Delivery Date</h1>
        <spring:form action="../ordercaterer/listMyBill.htm" commandName="invoice" method="POST">
            <input type="hidden" name="id" value="${invoice.id}"/>
            ${invoice.invoiceDate}
            Delivery Date: <input name="deliveryDate" type="date"/><br>
            <input type="submit" value="Submit">
        </spring:form> 
            <table border="1">
            <tr>
                <td>Material Name</td>
                <td>Price/Unit</td>
                <td>Quantity</td>
                <td>Total Price</td>
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
    </body>
</html>
