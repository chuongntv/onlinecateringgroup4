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
        <%@include file="../include/header.jsp" %>
        <a href="${pageContext.request.contextPath}/materialcategories/create.htm" class="btn btn-primary">Create category</a>
            <hr>
            <h1>Create Material Category</h1><br>
        <form action="../create.htm" commandName="category" method="POST">  
            <input type="hidden" name="supplierId" value="${userId}"/>
            Category Name: <input name="categoryName"/>                                                     
            <input type="submit" value="Create" class="btn btn-primary">           
        </form> 
        <hr>
        <h1>List of Material Categories</h1>
        <table class="table table-striped" >
            <tr>               
                <td><strong>Category Name</strong></td>
                <td colspan="2"><strong>Options</strong></td>
            </tr>
            <c:forEach items="${categories}" var="category">
                <tr>         
                    <td>${category.categoryName}</td>
                    <td><a data-toggle="tooltip" title="Edit Catergory" href="${pageContext.request.contextPath}/materialcategories/edit/${category.id}.htm" class="btn btn-primary">Edit</a></td>
                    <td><a data-toggle="tooltip" title="Delete Catergory" href="${pageContext.request.contextPath}/materialcategories/delete/${category.id}.htm" class="btn btn-danger"> Delete</a></td>
                    <td><a data-toggle="tooltip" href="${pageContext.request.contextPath}/materials/listmaterials/${category.id}.htm" class="btn btn-primary"> Create Material</a></td>
                </tr> 
            </c:forEach>
        </table> 
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
