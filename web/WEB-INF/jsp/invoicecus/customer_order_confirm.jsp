<%-- 
    Document   : customer_order_confirm
    Created on : Jul 24, 2015, 6:53:03 PM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script>
            /*  alert("hello");
            function checkdate() {
                alert("Checkday ing...");
                var datenow = new Date();
                var i = Date.parse(datenow);
                var minutes = 1000 * 60;
                var hours = minutes * 60;
                var days = hours * 24;
                var years = days * 365;
               // var d = Math.round(i / days);
                alert(i/years);
                datecheck   =  document.getElementById("date");
                
            }
*/
        </script>

    </head>
    <body>
        <spring:form action="confirm.htm" method="POST" commandName="customerinvoke" >
            <input name="accounts.id" readonly="true" hidden="true" value="${customerinvoke.accounts.id}">
            <table>
                <tr>
                    <td>Name:</td>
                    <td><input name="deliveryName" required="true"  id="name" type="text"/></td>
                </tr>
                                <tr>
                    <td>Phone:</td>
                    <td><input name="deliveryPhoneNumber" required="true"  type="text"/></td>
                </tr>
                                <tr>
                    <td>Address:</td>
                    <td><input name="deliveryAddress" required="true" type="text"/></td>
                </tr>
                <tr>
                    <td>Delivery Date:</td>
                    <td><input id="date" name="deliveryDate" type="date" required="true"   /><span  >${messagedate}</span></td>
                </tr>
            </table>

            <input type="submit" value="Order"  />

        </spring:form>


    </body>

</html>
