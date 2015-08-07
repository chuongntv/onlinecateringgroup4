<%-- 
    Document   : caterer_showall
    Created on : Aug 7, 2015, 2:52:53 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Management Caterer</title>
        <script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
        <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
        <script >
            function setCatererId() {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/managercaterer/setUserId.htm",
                    cache: true,
                    data: 'catererId=${userId}',
                    success: function (data) {
                        if (data == "error") {
                            window.location.href = '${pageContext.request.contextPath}/account/login.htm';
                        }
                    },
                    error: function () {
                        alert('Error while request..');
                    }
                });
            }
            //window.onpaint = setCatererId();
        </script>
    </head>
    <body onload="setCatererId();">
        <h1>Show All Customers Active</h1>
        <a href="${pageContext.request.contextPath}/managercaterer/listCaterersBlock.htm">Active Caterer</a>
        <table border="1">
            <tr>
                <td>Caterer Name</td>
                <td>Email</td>
                <td>User Name Login</td>
                <td>Email Name Login</td>
                <td>City</td>
                <td>Phone</td>
                <td>Address</td>
                <td>Option</td>
            </tr>
            <c:forEach items="${caterers}" var="caterer">
                <tr>
                    <td>${caterer.catererName}</td>
                    <td>${caterer.catererEmail}</td>
                    <td>${caterer.accounts.username}</td>
                    <td>${caterer.accounts.email}</td>
                    <td>${caterer.cities.cityName}</td>
                    <td>${caterer.catererPhoneNumber}</td>
                    <td>${caterer.catererAddress}</td>  
                    <td><a href="${pageContext.request.contextPath}/managercaterer/block/${caterer.accounts.id}.htm"> Block</a></td>
                </tr> 
            </c:forEach>
        </table>
    </body>
</html>
