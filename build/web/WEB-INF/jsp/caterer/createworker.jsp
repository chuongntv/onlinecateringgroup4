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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create Worker</h1>
        <h4>${messageCreate}</h4>
        <form action="../manageworker/createworker.htm" commandName="workerCreate" method="POST">  
            Name: <input name="workerName" required/><br>
            Email: <input name="workerEmail" type="email" required/><br>
            Phone Number: <input name="workerPhoneNumber" required/><br>
            Address: <input name="workerAddress" required/><br>
            <input type="submit" value="Create">           
        </form> 
    </body>
</html>

