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
        <title>Create Material</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h1>Create Material </h1>
        <style>.table th, .table td { 
                border-top: none !important; 
            }
        </style>
        <spring:form action="../materials/create.htm" commandName="materialCreate" method="POST">  
            <input type="hidden" name="categoryId" value="${requestScope.categoryId}"/>
            <table class="table">
                <tr>
                    <td width="13%"><strong>Material Name: </strong></td>
                    <td ><strong><input name="materialName" required/></strong></td>
                </tr>
                <tr>
                    <td width="13%"><strong>Material Unit: </strong></td>
                    <td ><strong><input type="text" name="materialUnit" required/></strong></td>
                </tr>
                <tr>
                    <td width="13%"><strong>Price per Unit: </strong></td>
                    <td ><strong><input type="number" name="materialPricePerUnit" required/></strong></td>
                </tr>
            </table>
            <input type="submit" value="Create"  class="btn btn-primary">           
        </spring:form> 
        <%@include file="../include/footer.jsp" %>

    </body>
</html>
