<%-- 
    Document   : suppliers_create
    Created on : Jul 22, 2015, 9:22:58 AM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    </head>
    <body>
         <%@include file="../include/header.jsp" %>
         <h3 style="text-align: center">Step 1: create account</h3>
        <p>${message}</p>
        <spring:form action="create_account.htm" method="POST" commandName="account" role="form"  > 
            <table>
                <tr>
                    <td>Username:</td>
                    <td>
                        <input type="text" name="username" required maxlength="20"  />
                    </td>
                </tr>
                <tr>
                    <td>Full name:</td>
                    <td>
                        <input type="text" name="fullName" required   />
                    </td>
                </tr>
                <tr>
                    <td>
                        Password:
                    </td>
                    <td>
                        <input type="password" name="password" id="pass1"  onsubmit="check()" required />
                    </td>
                </tr>
                <tr>
                    <td>Re-Password:</td>
                    <td>
                        <input type="password" id="pass2" onchange="check()" required  />  <span id="p"></span>
                    </td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>
                        <input type="text" id="email" name="email" required onchange="checkemail()" /><span id="erroremail"></span>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Create account" id="btn" value="Create" />
                    </td>
                </tr>


            </table>








        </spring:form>
        <%@include file="../include/footer.jsp" %>
        <script>
            //   $(document).ready(function (){
            function checkemail() {
                if($("#email").val().search("@") <0){
                    $("#erroremail").text("Email is nois valid");
                    $("#btn").hide();
                    return false;
                }else{
                    $("#erroremail").text("");
                    $("#btn").show();
                    return  true;
                }
            }
            function check() {
                //alert("Create");
                if ($("#pass1").val() != $("#pass2").val()) {
                    $("#p").text("Password must be math ");
                    $("#btn").hide();
                    return false;
                } else {
                    if ($("#pass1").val().length < 6) {
                        $("#p").text("Password must be at least 6 digit");
                        $("#btn").hide();
                        return false;
                    }
                    $("#p").text("");
                    $("#btn").show();

                }

            }
            //    });

        </script>
         
    </body>
</html>
