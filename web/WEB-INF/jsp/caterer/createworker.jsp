<%-- 
    Document   : createworker
    Created on : Jul 31, 2015, 6:17:44 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Worker</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h1>Create Worker</h1>
        <style>.table th, .table td { 
                border-top: none !important; 
            }
        </style>
        <form action="../manageworker/createworker.htm" commandName="workerCreate" method="POST">  
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
        <%@include file="../include/footer.jsp" %>
    </body>
</html>

