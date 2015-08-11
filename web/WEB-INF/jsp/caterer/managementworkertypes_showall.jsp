<%-- 
    Document   : managementworker
    Created on : Jul 22, 2015, 7:24:42 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Management Workers</title>
        <script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
        <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
        <script >
            function setCatererId() {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/manageworker/setUserId.htm",
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
        <h1>Create Worker Type</h1><br><br>
        <form action="../create.htm" commandName="typeCreate" method="POST">  
            <br><strong>Type Name: <input name="workerTypeName"/></strong><br>
            <strong>Pay Per Day: <input name="payPerDay" type="number" step="0.01"/></strong><br>
            <input type="submit" value="Create" class="btn btn-primary">           
        </form> 
        <hr>
        <hr>
        <h1>Edit Type</h1><br><br>
        <spring:form action="../manageworker/edit.htm" commandName="typeEdit" method="POST" class="form-horizontal" role="form">   
            <input type="hidden" name="idStr" value="${requestScope.id}"/>
            <input type="hidden" name="id" value="${typeEdit.id}"/>
            ${workerType.workerTypeName}
            <br><strong>Type Name: <spring:input path="workerTypeName" /></strong><br>
            <strong>Pay Per Day: <spring:input path="payPerDay"  type="number" step="0.01"/>  </strong>   <br>                                                
            <input type="submit" value="Save" onclick="form.action = '${pageContext.request.contextPath}/manageworker/edit.htm';" class="btn btn-primary">
            <input type="submit" value="Cancel" onclick="form.action = '${pageContext.request.contextPath}/manageworker/cancel.htm';" class="btn btn-warning"/>
        </spring:form> 
        <hr>
        <hr>
        <h1>List of Worker Types</h1>
        <table class="table table-striped">
            <tr>               
                <td><strong>Type Name</strong></td>
                <td><strong>Pay Per Day</strong></td>
                <td colspan="4"><strong>Options</strong></td>
            </tr>
            <c:forEach items="${types}" var="type">
                <tr>         
                    <td>${type.workerTypeName}</td>
                    <td>${type.payPerDay}</td>                    
                    <td><a href="${pageContext.request.contextPath}/manageworker/edit/${type.id}.htm" class="btn btn-primary">Edit</a></td>
                    <td><a  href="${pageContext.request.contextPath}/manageworker/delete/${type.id}.htm" class="btn btn-danger"> Delete</a></td>                    
                    <td><a  href="${pageContext.request.contextPath}/manageworker/listworkers/${type.id}.htm" class="btn btn-primary"> Timekeeping</a></td>
                    <td><a  href="${pageContext.request.contextPath}/manageworker/listworkers/${type.id}.htm"class="btn btn-primary" > Manager</a></td>
                </tr> 
            </c:forEach>
        </table> 
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
