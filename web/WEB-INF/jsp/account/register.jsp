<%-- 
    Document   : register
    Created on : Jul 19, 2015, 11:15:11 AM
    Author     : Nathan Tran
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

   
        <%@include file="../include/header.jsp" %>
        <title>Register</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<!--        <script src="//code.jquery.com/jquery-1.10.2.js"></script>-->
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>        
        <script>
            $(function () {
                $("#dateOfBirth").datepicker();
            });
        </script>
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
        <h2>Register!</h2>
        <h2>${message}</h2>
        <spring:form action="register.htm" commandName="account" method="POST" class="form-horizontal" role="form">       
            Username: <spring:input path="username" required="required"/><br/>
            Password: <spring:password path="password" required="required" id="pass1"/><br/>
            Retype-password: <input type="password" id="pass2" name="pass2" required onkeyup="check();
                            return false;"/><span id="confirmMessage" class="confirmMessage"></span><br/>
            User Group: <spring:radiobutton path="userGroup" value="customer"/>Customer
            <spring:radiobutton path="userGroup" value="caterer"/>Caterer
            <spring:radiobutton path="userGroup" value="supplier"/>Supplier<br/>
            <br/>
            Email: <spring:input type="email" path="email" required="required"/><br/>
            Full Name:<spring:input path="fullName" required="required"/><br/>
            Address:<spring:input path="address"/><br/>
            Date Of Birth:<spring:input path="dateOfBirth"/><br/>
            Phone Number:<spring:input path="phoneNumber" type="number"/><br/>
            <input type="hidden" name="status" value="0"/>
            <button type="submit" class="btn btn-primary"> Create</button>
        </spring:form>
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
