<%-- 
    Document   : customers_showall
    Created on : Aug 6, 2015, 2:00:59 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Customers</title>
        <script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
        <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
        <script >
            function setCatererId() {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/customers/setUserId.htm",
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
            setCatererId();
        </script>
    </head>
    <body onload="setCatererId();">
        <h1>Show All Customers Active</h1>
        <a href="${pageContext.request.contextPath}/customers/listCustomersBlock.htm">Active Customers</a>
        <table border="1">
            <tr>
                <td>Name</td>
                <td>Full Name</td>
                <td>Email</td>
                <td>Phone</td>
                <td>Option</td>
            </tr>
            <c:forEach items="${customers}" var="customer">
                <tr>
                    <td>${customer.username}</td>
                    <td>${customer.fullName}</td>
                    <td>${customer.email}</td>
                    <td>${customer.phoneNumber}</td>  
                    <td><a href="${pageContext.request.contextPath}/customers/block/${customer.id}.htm"> Block</a></td>
                </tr> 
            </c:forEach>
        </table>
    </body>
</html>

