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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create Material Category!</h1>
        <h4>${messageCreate}</h4>
        <form action="../materialcategories/create.htm" commandName="category" method="POST">  
            <input type="hidden" name="supplierId" value="${userId}"/>
            Category Name: <input name="categoryName"/>                                                     
            <input type="submit" value="Create">           
        </form> 
        <hr>
    </body>
</html>
