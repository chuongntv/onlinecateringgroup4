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
        <title>Edit Material</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h1>Edit Material</h1><br>
        <spring:form action="../edit.htm" commandName="materialEdit" method="POST" >       
            <input type="hidden" name="id" value="${requestScope.id}"/>
            <table class="table">
                <tr>
                    <td width="13%"><strong>Material Name: </strong></td>
                    <td ><strong><spring:input path="materialName" /></strong></td>
                </tr>
                <tr>
                    <td width="13%"><strong>Material Unit: </strong></td>
                    <td width="13%"><strong><spring:input type="text" path="materialUnit"/></strong></td> 
                </tr>
                <tr>
                    <td width="13%"><strong>Price per Unit: </strong></td>
                    <td width="13%"><strong>  <spring:input type="number" path="materialPricePerUnit"/><br></strong></td> 
                </tr>
                <input type="submit" value="Save" onclick="form.action = '${pageContext.request.contextPath}/materials/edit.htm';"  class="btn btn-primary">
                <input type="submit" value="Cancel" onclick="form.action = '${pageContext.request.contextPath}/materials/cancel.htm';"  class="btn btn-warning"/>
            </spring:form> 
            <%@include file="../include/footer.jsp" %>
    </body>
</html>
