<%-- 
    Document   : edit
    Created on : Jul 23, 2015, 4:24:42 PM
    Author     : Nathan Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Account</title>
    </head>
    <body>
        <h1>Edit Account!</h1>
        <spring:form action="../edit.htm" commandName="account" method="POST" class="form-horizontal" role="form">
            <input type="hidden" name="id" value="${account.id}"/>
            <input type="hidden" name="username" value="${account.username}"/>
            <h2>${account.username}</h2>
            Password: <spring:input path="password"/><br/>
            User Group: <spring:radiobutton path="userGroup" value="customer"/>Customer
            <spring:radiobutton path="userGroup" value="caterer"/>Caterer
            <spring:radiobutton path="userGroup" value="supplier"/>Supplier<br/>
            Email: <spring:input path="email"/><br/>
            Full Name:<spring:input path="fullName"/><br/>
            Address:<spring:input path="address"/><br/>
            Date Of Birth:<spring:input path="dateOfBirth"/><br/>
            Phone Number:<spring:input path="phoneNumber"/><br/>
            Status: <spring:radiobutton path="status" value="0"/>Active
            <spring:radiobutton path="status" value="1"/>Inactive
            <button type="submit"> Save</button>
        </spring:form>
    </body>
</html>
