<%-- 
    Document   : creatematerial
    Created on : Jul 31, 2015, 7:14:32 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create Material </h1>
        <h4>${messageCreate}</h4>
        <spring:form action="../materials/create.htm" commandName="materialCreate" method="POST">  
            <input type="hidden" name="categoryId" value="${requestScope.categoryId}"/>
            Material Name: <input name="materialName" required/><br>
            Material Unit: <input type="text" name="materialUnit" required/><br>  
            Price per Unit: <input type="number" name="materialPricePerUnit" required/><br>
            <input type="submit" value="Create">           
        </spring:form> 
        <hr>
        
    </body>
</html>
