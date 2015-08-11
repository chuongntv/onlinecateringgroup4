<%-- 
    Document   : login
    Created on : Jul 19, 2015, 10:58:18 PM
    Author     : Nathan Tran
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h2>Login</h2>
        <h3>${message}</h3>
        <spring:form action="login.htm" commandName="account" method="POST" class="form-horizontal" role="form">       
            Username: <spring:input path="username" required="required"/>
            Password: <spring:password path="password" required="required"/>
            <button type="submit">Login</button>
        </spring:form>
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
