<%-- 
    Document   : managementworker_showall
    Created on : Jul 24, 2015, 3:11:50 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Worker</title>
        <script type="text/javascript" src="js/jquery-2.1.4.min.js"/>
    </head>
    <body>
    <%@include file="../include/header.jsp" %>
        <h1>Create Worker</h1><br>
        <form action="../createworker.htm" commandName="workerCreate" method="POST">  
            Worker Name: <input name="workerName"/><br>
            Phone Number: <input name="workerPhoneNumber"/><br>
            Email: <input name="workerEmail" type="email" /><br>
            Address: <input name="workerAddress" />
            <input type="submit" value="Create" class="btn btn-primary">           
        </form> 
        <hr>
        <h1>Edit Worker</h1><br>
        <spring:form action="../editworker.htm" commandName="workerEdit" method="POST">   
            <input type="hidden" name="idStr" value="${requestScope.id}"/>
            <input type="hidden" name="id" value="${workerEdit.id}"/>
            Worker Name: <spring:input path="workerName"/><br>
            Phone Number: <spring:input path="workerPhoneNumber"/><br>
            Email: <spring:input path="workerEmail" type="email" /><br>
            Address: <spring:input path="workerAddress" />    <br>                                               
            Status: <spring:radiobutton path="status" value="1"/>Block
            <spring:radiobutton path="status" value="0"/>Is Active<br>
            <input type="submit" value="Save" onclick="form.action = '${pageContext.request.contextPath}/manageworker/editworker.htm';" class="btn btn-primary"/>
            <input type="submit" value="Cancel" onclick="form.action = '${pageContext.request.contextPath}/manageworker/cancelworker.htm';"  class="btn btn-warning"/>
        </spring:form> 
        <hr>
        <h1>List Workers By Worker Type</h1>
        <table class="table table-striped" >
            <tr>  
                <td><strong>Checked</strong></td>             
                <td><strong>Worker Name</strong></td>
                <td><strong>Worker Email</strong></td>
                <td><strong>Date Of Join</strong></td>
                <td><strong>Status</strong></td>
                <td colspan="2"><strong>Options</strong></td>
            </tr>
            <c:forEach items="${workers}" var="worker">
                <tr>
                    <td><input type="checkbox" id="idString" name="idString" value="${worker.id}"/></td>
                    <td>${worker.workerName}</td>
                    <td>${worker.workerEmail}</td>                    
                    <td>${worker.workerDateOfJoin}</td>    
                    <td>${worker.statusStr}</td>    
                    <td><a href="${pageContext.request.contextPath}/manageworker/editworker/${worker.id}.htm" class="btn btn-primary">Edit</a></td>
                    <td><a  href="${pageContext.request.contextPath}/manageworker/deleteworker/${worker.id}.htm" class="btn btn-danger"> Delete</a></td>
                    <td><a  href="${pageContext.request.contextPath}/manageworker/payment/${worker.id}.htm" class="btn btn-primary"> Payment</a></td>
                </tr> 
            </c:forEach>             
        </table> 
        <script type="text/javascript">
            $('#idString').val();
            $('#idString').is(":checked");
            $.ajax({
                type: "POST",
                url: "/OnlineCatering/manageworker/timekeeping.htm",
                data: {wday:$('#idString').val()},
                success: function (data)
                {
                    alert("Submited to the Medical Department");
                }
            });
        </script>
        <%@include file="../include/footer.jsp" %>
    </body>
</html>

