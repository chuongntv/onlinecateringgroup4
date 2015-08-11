<%-- 
    Document   : info
    Created on : Aug 9, 2015, 10:08:05 PM
    Author     : Nathan Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Information</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h2>Account Information!</h2>
        <div>
        <label>Account Id: ${user.id}</label><br/>
        <label>Username: ${user.username}</label><br/>
        <label>Full name ${user.fullName}</label><br/>
        <label>User group: ${user.userGroup}</label><br/>
        <label>Email: ${user.email}</label><br/>
        <label>Address: ${user.address}</label><br/>
        <label>Date of birth: ${user.dateOfBirth}</label><br/>
        <label>Phone number: ${user.phoneNumber}</label><br/>
        </div>
        <div>
            <a  href="${pageContext.request.contextPath}/account/update.htm" class="btn btn-primary">Update Info</a>
            <a  href="${pageContext.request.contextPath}/account/changepass.htm" class="btn btn-primary">Change Password</a>
        </div>
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
