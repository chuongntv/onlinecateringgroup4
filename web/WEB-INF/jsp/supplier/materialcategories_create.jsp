<%-- 
    Document   : materialcategories_create
    Created on : Aug 1, 2015, 7:46:54 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Material Category</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h1>Create Material Category</h1><br>
        <form action="../materialcategories/create.htm" commandName="category" method="POST">  
            <input type="hidden" name="supplierId" value="${userId}"/>
            Category Name: <input name="categoryName"/>                                                     
            <input type="submit" value="Create" class="btn btn-primary">           
        </form> 
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
