<%-- 
    Document   : fuckyou
    Created on : Jul 22, 2015, 10:41:53 AM
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
        <h1>Edit Type</h1>
        <h4>${messageEdit}</h4>
        <spring:form action="/edit.htm" commandName="typeEdit" method="POST" class="form-horizontal" role="form">   
            <input type="hidden" name="id" value="${requestScope.id}"/>
            ${workerType.workerTypeName}
            Type Name: <spring:input path="workerTypeName" /><br>
            Pay Per Day: <spring:input path="payPerDay"/>                                                     
            <input type="submit" value="Save" onclick="form.action='${pageContext.request.contextPath}/manageworker/edit.htm';">
            <input type="submit" value="Cancel" onclick="form.action='${pageContext.request.contextPath}/manageworker/cancel.htm';"/>
        </spring:form> 
            <hr>
    </body>
</html>
