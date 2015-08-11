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
        <%@include file="../include/header.jsp" %>
        <h1>To Day: ${toDay}</h1><br><br>
        <br><h1>Create Worker</h1><br><br>
        <style>.table th, .table td { 
                border-top: none !important; 
            }
        </style>
        <form action="../createworker.htm" commandName="workerCreate" method="POST"> 
            <table class="table">
                <tr>
                    <td width="13%"><strong>Name: </strong></td>
                    <td ><strong><input name="workerName" required/></strong></td>
                </tr>
                <tr>
                    <td width="13%"><strong>Email: </strong></td>
                    <td ><strong><input name="workerEmail" required/></strong></td>
                </tr>
                <tr>
                    <td width="13%"><strong>Phone Number: </strong></td>
                    <td ><strong><input name="workerPhoneNumber" required/></strong></td>
                </tr>
                <tr>
                    <td width="13%"><strong>Address: </strong></td>
                    <td ><strong><input name="workerAddress" required/></strong></td>
                </tr>
            </table>  
            <input type="submit" value="Create" class="btn btn-primary"> 
        </form> 
        <hr>
        <h1>List Workers By Worker Type</h1>
        <table class="table table-striped" >
            <tr >               
                <td><strong>Checked</strong></td>
                <td><strong>Worker Name</strong></td>
                <td><strong>Worker Email</strong></td>
                <td colspan="3"><strong>Options</strong></td>
            </tr>
            <c:forEach items="${workers}" var="worker">

                <tr>
                    <td id="td${worker.id}"><button onClick = "this.style.visibility = 'hidden';
                            addDate(${worker.id});"  class="btn btn-primary">Check To Day</button></td>
                    <td>${worker.workerName}</td>
                    <td>${worker.workerEmail}</td>  
                    <td><a href="${pageContext.request.contextPath}/manageworker/editworker/${worker.id}.htm"  class="btn btn-primary">Edit</a></td>
                    <td><a  href="${pageContext.request.contextPath}/manageworker/blockworker/${worker.id}.htm"  class="btn btn-danger"> Block</a></td>
                    <td><a  href="${pageContext.request.contextPath}/manageworker/payment/${worker.id}.htm"  class="btn btn-primary"> Payment</a></td>
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
                        $('#td' + id).html(data);

                    },
                    error: function () {
                        alert('Error while request..');
                    }
                });
            }
            ;
        </script>
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
