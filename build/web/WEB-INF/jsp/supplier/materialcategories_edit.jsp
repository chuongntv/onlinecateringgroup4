<%-- 
    Document   : materialcategory_edit
    Created on : Jul 21, 2015, 4:36:54 PM
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
        <h1>Edit Material Categories!</h1>
        <h4>${messageEdit}</h4>
        <form action="../edit.htm"  commandName="categoryEdit" method="POST">   
            <input type="hidden" name="supplierId" value="${userId}"/>
            <input type="hidden" name="id" value="${requestScope.id}"/>
            Category Name: <input name="categoryName" value="${categoryEdit.categoryName}"/>                                                      
            <input type="submit" value="Save" >
            
        </form> 
    </body>
</html>
