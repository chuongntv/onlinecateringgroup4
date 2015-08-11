<%-- 
    Document   : change_password
    Created on : Aug 3, 2015, 10:04:08 AM
    Author     : Nathan Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>    
        <script type="text/javascript">
            function check() {
                var pass1 = document.getElementById('pass1');
                var pass2 = document.getElementById('pass2');
                var message = document.getElementById('confirmMessage');
                var goodColor = "#66cc66";
                var badColor = "#ff6666";
                if (pass1.value == pass2.value) {
                    if (pass1.value.length < 6) {
                        message.style.color = badColor;
                        message.innerHTML = ("Password must be at least 6 digit");
                        return false;
                    }
                    pass2.style.backgroundColor = goodColor;
                    message.style.color = goodColor;
                    message.innerHTML = "Passwords Match!";
                    return true;
                } else {
                    pass2.style.backgroundColor = badColor;
                    message.style.color = badColor;
                    message.innerHTML = "Passwords Do Not Match!";
                    return false;
                }
                return false;
            }
            $('document').ready(function () {
                $('.form-horizontal').on('submit', function (e) {
                    console.log(check());
                    if (!check()) {
                        e.preventDefault();
                    }
                })
            })
        </script>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h2>Change Password!</h2>
        <spring:form action="changepass.htm" commandName="account" method="POST" class="form-horizontal" role="form">       
            <input type="hidden" name="id" value="${user.id}"/>
            Username: <spring:input disabled="true" readonly="true" path="username" required="required" maxlength="12"/><br/>
            Password: <spring:password id="pass1" path="password" required="required" /><br/>
            Retype-password: <input id="pass2" type="password" name="pass2" required onkeyup="check();
                            return false;"/><span id="confirmMessage" class="confirmMessage"></span><br/>
            <input type="submit" value="Save" class="btn btn-primary"/> 
        </spring:form>
        <a  href="${pageContext.request.contextPath}/account/info.htm" >Back to info</a>
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
