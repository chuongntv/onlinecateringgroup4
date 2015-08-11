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
        <title>Edit Material Categories</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h1>Edit Material Categories</h1><br>
        <form action="../edit.htm"  commandName="categoryEdit" method="POST">   
            <input type="hidden" name="supplierId" value="${userId}"/>
            <input type="hidden" name="id" value="${requestScope.id}"/>
            <br>Category Name: <input name="categoryName" value="${categoryEdit.categoryName}"/>                                                      
            <input type="submit" value="Save"  class="btn btn-primary">
            
        </form> 
            <%@include file="../include/footer.jsp" %>
    </body>
</html>
