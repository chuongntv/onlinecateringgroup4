<%-- 
    Document   : edit
    Created on : Jul 22, 2015, 6:12:29 PM
    Author     : Nathan Tran
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Info</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>                
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h2>Update Account Information!</h2>
        <spring:form action="update.htm" commandName="account" method="POST" class="form-horizontal" role="form">
            <input type="hidden" name="id" value="${account.id}"/>
            <input type="hidden" name="username" value="${account.username}"/>
            <h2>Username: ${account.username}</h2>
            <h2>User Group: ${account.userGroup}</h2>
            Email: <spring:input type="email" path="email" required="required"/><br/>
            Full Name:<spring:input path="fullName" required="required"/><br/>
            Address:<spring:input path="address"/><br/>
            Date Of Birth:<spring:input path="dateOfBirth"/><br/>
            Phone Number:<spring:input path="phoneNumber" type="number"/><br/>            
            <button type="submit"> Update</button>
        </spring:form>
        <a  href="${pageContext.request.contextPath}/account/info.htm">Back to info</a>
        <%@include file="../include/footer.jsp" %>        
    </body>    
</html>
