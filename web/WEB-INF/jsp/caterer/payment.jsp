<%-- 
    Document   : payment
    Created on : Jul 31, 2015, 3:24:49 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment For Worker</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h1>Payment For Worker</h1>
        <spring:form action="../payment.htm" commandName="paymentworker" method="POST">   
            <input type="hidden" name="id" value="${paymentworker.id}"/>
            Worker Name: ${workerNameSalary}<br>
            Phone Number: ${workerPhoneNumberSalary}<br>
            Email: ${workerEmailSalary}<br> 
            Total Working Day: <label style="color: red" name="workingDay">${workingDay}</label><br>
            Payment Type: <spring:radiobutton path="payType" value="week"/>Week
            <spring:radiobutton path="payType" value="month"/>Month
            <spring:radiobutton path="payType" value="quarter"/>Quarter
            <spring:radiobutton path="payType" value="year"/>year<br>
            ${messgePayment}
            <input type="submit" value="Payment" class="btn btn-primary">
        </spring:form> 
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
