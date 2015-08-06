<%-- 
    Document   : countries_edit
    Created on : Jul 14, 2015, 4:40:33 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit Country!</h1>
        <h4>${message}</h4>
        <spring:form action="../edit.htm" commandName="country" method="POST" class="form-horizontal" role="form">       
            <input type="hidden" name="id" value="${requestScope.id}"/>
            Country Name: <spring:input path="countryName" />                                                      
            <button type="submit"> Save</button>
        </spring:form> 
    </body>
</html>
