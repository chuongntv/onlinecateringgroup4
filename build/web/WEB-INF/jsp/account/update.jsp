<%-- 
    Document   : edit
    Created on : Jul 22, 2015, 6:12:29 PM
    Author     : Nathan Tran
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Info</title>
    </head>
    <body>
        <h1>Update Account Information!</h1>
        <spring:form action="update.htm" commandName="account" method="POST" class="form-horizontal" role="form">
            <input type="hidden" name="id" value="${account.id}"/>
            <input type="hidden" name="username" value="${account.username}"/>
            <h2>${account.username}</h2>
            <h2>${account.userGroup}</h2>
            Email: <spring:input path="email"/><br/>
            Full Name:<spring:input path="fullName"/><br/>
            Address:<spring:input path="address"/><br/>
            Date Of Birth:<spring:input path="dateOfBirth"/><br/>
            Phone Number:<spring:input path="phoneNumber"/><br/>            
            <button type="submit"> Update</button>
        </spring:form>
    </body>
</html>
