<%-- 
    Document   : editmaterial
    Created on : Jul 31, 2015, 7:12:03 PM
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
        <h1>Edit Material!</h1>
        <spring:form action="../edit.htm" commandName="materialEdit" method="POST" >       
            <input type="hidden" name="id" value="${requestScope.id}"/>
            Material Name: <spring:input path="materialName" /><br>
            Material Unit: <spring:input type="text" path="materialUnit"/><br>  
            Price per Unit: <spring:input type="number" path="materialPricePerUnit"/><br>   
            <input type="submit" value="Save" onclick="form.action = '${pageContext.request.contextPath}/materials/edit.htm';">
            <input type="submit" value="Cancel" onclick="form.action = '${pageContext.request.contextPath}/materials/cancel.htm';"/>
        </spring:form> 
        <hr>
    </body>
</html>
