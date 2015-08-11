<%-- 
    Document   : editWorker
    Created on : Jul 30, 2015, 10:55:54 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Worker</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h1>Edit Worker</h1>
        <spring:form action="../editworker.htm" commandName="workerEdit" method="POST">   
            <input type="hidden" name="idStr" value="${requestScope.id}"/>
            <input type="hidden" name="id" value="${workerEdit.id}"/>
            Worker Name: <spring:input path="workerName"/><br>
            Phone Number: <spring:input path="workerPhoneNumber"/><br>
            Email: <spring:input path="workerEmail" type="email" /><br>
            Address: <spring:input path="workerAddress" />    <br>                                               
            Status: <spring:radiobutton path="status" value="1"/>Block
            <spring:radiobutton path="status" value="0"/>Is Active<br>
            <input type="submit" value="Save" onclick="form.action = '${pageContext.request.contextPath}/manageworker/editworker.htm';" class="btn btn-primary">
            <input type="submit" value="Cancel" onclick="form.action = '${pageContext.request.contextPath}/manageworker/cancelworker.htm';" class="btn btn-warning"/>
        </spring:form> 
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
