<%-- 
    Document   : manageworker_update
    Created on : Jul 23, 2015, 3:41:38 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Type</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h1>Edit Type</h1>
        <spring:form action="../edit.htm" commandName="typeEdit" method="POST" role="form">   
            <input type="" name="id" value="${requestScope.id}"/><br>
            <input type="" name="id" value="${typeEdit.id}"/><br>
            <input type="" name="catererId" value="${userId}"/><br>
            ${workerType.workerTypeName}
            Type Name: <spring:input path="workerTypeName" /><br>
            Pay Per Day: <spring:input path="payPerDay"  type="number" step="0.01"/>                                                     
            <input type="submit" value="Save" onclick="form.action='${pageContext.request.contextPath}/updateworkertype/editType.htm';" class="btn btn-primary">        
        </spring:form> 
         <%@include file="../include/footer.jsp" %>
    </body>
</html>
