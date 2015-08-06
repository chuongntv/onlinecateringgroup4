<%-- 
    Document   : materialcategories_showall
    Created on : Jul 15, 2015, 3:36:10 AM
    Author     : TriThoai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Material Categories</title>
    <script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
        <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
        <script >
            function setCatererId() {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/materialcategories/setUserId.htm",
                    cache: true,
                    data: 'catererId=${userId}',
                    success: function (data) {
                        if (data == "error") {
                            window.location.href = '${pageContext.request.contextPath}/account/login.htm';
                        }
                    },
                    error: function () {
                        alert('Error while request..');
                    }
                });
            }
            setCatererId();
        </script>
    </head>
    <body onload="setCatererId();">
        <strong>UserId: ${userId}</strong><br>
        <a href="${pageContext.request.contextPath}/materialcategories/create.htm">Create category</a>
            <hr>
            <h1>Create Material Category!</h1>
        <h4>${messageCreate}</h4>
        <form action="../create.htm" commandName="category" method="POST">  
            <input type="hidden" name="supplierId" value="${userId}"/>
            Category Name: <input name="categoryName"/>                                                     
            <input type="submit" value="Create">           
        </form> 
        <hr>
        <h1>List of Material Categories</h1>
        <table border="1" >
            <tr class="info">               
                <th>Category Name</th>
                <th colspan="2">Options</th>
            </tr>
            <c:forEach items="${categories}" var="category">
                <tr>         
                    <td>${category.categoryName}</td>
                                        
                    <td><a data-toggle="tooltip" title="Edit Catergory" href="${pageContext.request.contextPath}/materialcategories/edit/${category.id}.htm"><span class="glyphicon glyphicon-edit"></span> Edit</a></td>
                    <td><a data-toggle="tooltip" title="Delete Catergory" href="${pageContext.request.contextPath}/materialcategories/delete/${category.id}.htm" > Delete</a></td>
                    <td><a data-toggle="tooltip" href="${pageContext.request.contextPath}/materials/listmaterials/${category.id}.htm" > Create Material</a></td>
                </tr> 
            </c:forEach>
        </table> 
    </body>
</html>
