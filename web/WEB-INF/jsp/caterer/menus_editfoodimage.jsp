<%-- 
    Document   : menus_editfoodimage
    Created on : Aug 3, 2015, 4:53:15 PM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit food image</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        
        <div>
            <h3 style="text-align: center">Edit image ${food.foodName}</h3>
            <div style="width: 600px;margin-left: auto;margin-right: auto">
                <spring:form action="../editfoodimage.htm" commandName="food" method="post" enctype="multipart/form-data" >
            <input type="text" name="id" value="${food.id}" hidden="true"/>

            <img src="${pageContext.request.contextPath}${food.image}" width="100px" height="150px"/><br>
            <input name="file" cssClass="form-control" type="file" /><br>
            <input type="submit" value="Edit"/>
        </spring:form>
            </div>
        </div>
        
        
               <%@include file="../include/footer.jsp" %>
    </body>
</html>
