<%-- 
    Document   : update
    Created on : Jul 24, 2015, 7:42:03 PM
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
        <spring:form action="../edit.htm" commandName="typeEdit" method="POST" role="form">   
            ID: <input type="text" name="idStr" value="${requestScope.id}"/>
            <!--dong id cua doi tuong luon phai co-->
            <input type="hidden" name="id" value="${typeEdit.id}"/>
            Type Name: <spring:input path="workerTypeName" /><br>
            Pay Per Day: <spring:input path="payPerDay"  type="number" step="0.01"/>                                                     
            <input type="submit" value="Save">
        </spring:form> 
            <hr>
    </body>
</html>
