<%-- 
    Document   : timekeeping
    Created on : Jul 24, 2015, 9:27:35 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
        <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Time Keeping</title>
    </head>
    <body>
        <h1>${toDay}</h1>
        <input type="button" value="Create Worker" onclick="form.action = '${pageContext.request.contextPath}/manageworker/createworker.htm';">
        <a href="${pageContext.request.contextPath}/manageworker/createworker.htm">Create Worker</a>
        <h1>Create Worker</h1>
        <h4>${messageCreate}</h4>
        <form action="../createworker.htm" commandName="workerCreate" method="POST">  
            Name: <input name="workerName" required/><br>
            Email: <input name="workerEmail" type="email" required/><br>
            Phone Number: <input name="workerPhoneNumber" required/><br>
            Address: <input name="workerAddress" required/><br>
            <input type="submit" value="Create">           
        </form> 
        <hr>
        <h1>List Workers By Worker Type</h1>
        ${messagePaymentNoti}
        <table border="" >
            <tr class="info">               
                <th>Checked</th>
                <th>Worker Name</th>
                <th>Worker Email</th>
                <th colspan="2">Options</th>
            </tr>
            <c:forEach items="${workers}" var="worker">

                <tr>
                    <td id="td${worker.id}"><button onClick = "this.style.visibility = 'hidden';
                            addDate(${worker.id});">Check To Day</button></td>
                    <td>${worker.workerName}</td>
                    <td>${worker.workerEmail}</td>  
                    <td><a href="${pageContext.request.contextPath}/manageworker/editworker/${worker.id}.htm">Edit</a></td>
                    <td><a  href="${pageContext.request.contextPath}/manageworker/blockworker/${worker.id}.htm" > Block</a></td>
                    <td><a  href="${pageContext.request.contextPath}/manageworker/payment/${worker.id}.htm" > Payment</a></td>
                </tr>                 

            </c:forEach>
        </table> 
        <script type="text/javascript">
            function addDate(id) {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/manageworker/addDate.htm",
                    cache: false,
                    data: 'workerId=' + id,
                    success: function (data) {
                        $('#td'+id).html(data);

                    },
                    error: function () {
                        alert('Error while request..');
                    }
                });
            }
            ;
        </script>
    </body>
</html>
