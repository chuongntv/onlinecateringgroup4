<%-- 
    Document   : materials_showall
    Created on : Jul 21, 2015, 4:05:58 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Material</title>
    </head>
    <body>
         <%@include file="../include/header.jsp" %>
        <br>
        <a href="${pageContext.request.contextPath}/materials/create.htm" class="btn btn-primary">Create Materialss</a>
            <!--input type="submit" value="Show All Materials" onclick="form.action='';"-->
            
            <h1>Create Material </h1><br>
        <form action="../create.htm" commandName="materialCreate" method="POST">  
            <input type="hidden" name="categoryId" value="${requestScope.categoryId}"/>
            Material Name: <input name="materialName" required/><br>
            Material Unit: <input type="text" name="materialUnit" required/><br>  
            Price per Unit: <input type="number" name="materialPricePerUnit" required/><br>
            <input type="submit" value="Create" class="btn btn-primary">           
        </form> 
        <hr>
        <h1>List of Materials</h1>
        <table class="table table-striped" >
            <tr>               
                <th>Material Name</th>
                <th>Price per Unit</th>
                <th colspan="2">Options</th>
            </tr>
            <c:forEach items="${materials}" var="material">
                <tr>                   
                    <td>${material.materialName}</td>
                    <td>${material.materialPricePerUnit}/${material.materialUnit}</td>
                    <td><a href="${pageContext.request.contextPath}/materials/edit/${material.id}.htm" class="btn btn-primary">Edit</a></td>
                    <td><a href="${pageContext.request.contextPath}/materials/delete/${material.id}.htm" class="btn btn-danger"> Delete</a></td>
                </tr> 
            </c:forEach>
        </table> 
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
