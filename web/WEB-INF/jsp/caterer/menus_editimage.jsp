<%-- 
    Document   : menus_editimage
    Created on : Aug 1, 2015, 1:05:50 AM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Image</title>
    </head>
    <body>
         <%@include file="../include/header.jsp" %>
        <h3>Edit image menu ${menu.menuName}</h3>
        
        <spring:form action="../editimage.htm" commandName="menu" method="post" enctype="multipart/form-data" >
            <input type="text" name="id" value="${menu.id}" hidden="true"/>
            <img src="${pageContext.request.contextPath}${menu.image}" width="100px" height="150px"/><br>
            
            <input name="file" cssClass="form-control" type="file" /><br>
            <input type="submit" value="Edit"/>
        </spring:form>
               <%@include file="../include/footer.jsp" %>
    </body>
</html>
