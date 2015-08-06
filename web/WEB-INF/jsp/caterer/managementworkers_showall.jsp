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
        <title>JSP Page</title>
        <script type="text/javascript" src="js/jquery-2.1.4.min.js">
    </head>
    <body>
        <h1>Create Worker</h1>
        <h4>${messageCreate}</h4>
        <form action="../createworker.htm" commandName="workerCreate" method="POST">  
            Worker Name: <input name="workerName"/><br>
            Phone Number: <input name="workerPhoneNumber"/><br>
            Email: <input name="workerEmail" type="email" /><br>
            Address: <input name="workerAddress" />
            <input type="submit" value="Create">           
        </form> 
        <hr>
        <h1>Edit Worker</h1>
        <h4>${messageEdit}</h4>
        <spring:form action="../editworker.htm" commandName="workerEdit" method="POST">   
            <input type="hidden" name="idStr" value="${requestScope.id}"/>
            <input type="hidden" name="id" value="${workerEdit.id}"/>
            Worker Name: <spring:input path="workerName"/><br>
            Phone Number: <spring:input path="workerPhoneNumber"/><br>
            Email: <spring:input path="workerEmail" type="email" /><br>
            Address: <spring:input path="workerAddress" />    <br>                                               
            Status: <spring:radiobutton path="status" value="1"/>Block
            <spring:radiobutton path="status" value="0"/>Is Active<br>
            <input type="submit" value="Save" onclick="form.action = '${pageContext.request.contextPath}/manageworker/editworker.htm';">
            <input type="submit" value="Cancel" onclick="form.action = '${pageContext.request.contextPath}/manageworker/cancelworker.htm';"/>
        </spring:form> 
        <hr>
        <h1>List Workers By Worker Type</h1>
        <h3>${messagenotification}</h3>
        <table border="" >
            <tr class="info">  
                <th>Checked</th>             
                <th>Worker Name</th>
                <th>Worker Email</th>
                <th>Date Of Join</th>
                <th>Status</th>
                <th colspan="2">Options</th>
            </tr>
            <c:forEach items="${workers}" var="worker">
                <tr>
                    <td><input type="checkbox" id="idString" name="idString" value="${worker.id}"/></td>
                    <td>${worker.workerName}</td>
                    <td>${worker.workerEmail}</td>                    
                    <td>${worker.workerDateOfJoin}</td>    
                    <td>${worker.statusStr}</td>    
                    <td><a href="${pageContext.request.contextPath}/manageworker/editworker/${worker.id}.htm">Edit</a></td>
                    <td><a  href="${pageContext.request.contextPath}/manageworker/deleteworker/${worker.id}.htm" > Delete</a></td>
                    <td><a  href="${pageContext.request.contextPath}/manageworker/payment/${worker.id}.htm" > Payment</a></td>
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
    </body>
</html>

