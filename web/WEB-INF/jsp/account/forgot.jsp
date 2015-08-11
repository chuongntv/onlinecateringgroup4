<%-- 
    Document   : forgot
    Created on : Jul 22, 2015, 5:50:59 PM
    Author     : Nathan Tran
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h2>Forgot Password!</h2>
        <h2>${message}</h2>
        <spring:form action="forgot.htm" commandName="account" method="POST" class="form-horizontal" role="form">    
            <h2>Type your username and your email in the form. We will send you your password</h2>
            Username: <spring:input path="username" required="required"/><br/>
            Email: <spring:input path="email" type="email" required="required"/><br/>            
            <button type="submit" class="btn btn-primary">Submit</button>
        </spring:form>
            <%@include file="../include/footer.jsp" %>
    </body>
</html>
