<%-- 
    Document   : register
    Created on : Jul 19, 2015, 11:15:11 AM
    Author     : Nathan Tran
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Register!</h1>
        <spring:form action="register.htm" commandName="account" method="POST" class="form-horizontal" role="form">       
            Username: <spring:input path="username"/><br/>
            Password: <spring:password path="password"/><br/>
            User Group: <spring:radiobutton path="userGroup" value="customer"/>Customer<br/>
            <spring:radiobutton path="userGroup" value="caterer"/>Caterer<br/>
            <spring:radiobutton path="userGroup" value="supplier"/>Supplier<br/>
            <br/>
            Email: <spring:input path="email"/><br/>
            Full Name:<spring:input path="fullName"/><br/>
            Address:<spring:input path="address"/><br/>
            Date Of Birth:<spring:input path="dateOfBirth"/><br/>
            Phone Number:<spring:input path="phoneNumber"/><br/>
            <input type="hidden" name="status" value="0"/>
            <button type="submit"> Create</button>
        </spring:form>
    </body>
</html>
