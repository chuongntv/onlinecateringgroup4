<%-- 
    Document   : booking
    Created on : Aug 6, 2015, 3:03:36 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Booking From Caterer</title>
        <script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
        <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
        <script >
            function setCatererId() {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/checkbooking/setUserId.htm",
                    cache: true,
                    data: 'catererId=${userId}',
                    success: function (data) {
                        if (data == "error") {
                            window.location.href = '${pageContext.request.contextPath}/account/login.htm';
                        }
                    },
                    error: function () {
                        window.location.href = '${pageContext.request.contextPath}/account/login.htm';
                    }
                });
            }
        </script>
    </head>
    <body onload="setCatererId();">
        <h1>List Booking waiting</h1>
        <table border="1">
            <tr>
                <td>Caterer Name</td>
                <td>Invoice Date</td>
                <td>Delivery Date</td>
            </tr>
            <c:forEach items="${bookings}" var="booking">
                <tr>
                    <td>${booking.caterer.catererName}</td>
                    <td>${booking.invoiceDate}</td>
                    <td>${booking.deliveryDate}</td>
                </tr> 
            </c:forEach>
    </body>
</html>
