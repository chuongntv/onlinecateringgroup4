<%-- 
    Document   : add
    Created on : Jul 13, 2015, 6:40:02 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        UserId: ${userId}
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Country</title>
    </head>
    <body>
        <h1>Create Countries!</h1>
        <h4>${message}</h4>
        <spring:form action="create.htm" commandName="country" method="POST" class="form-horizontal" role="form">       
            Country Name: <spring:input path="countryName" />                                                      
            <button type="submit"> Create</button>
        </spring:form> 
       
    </body>
</html>
