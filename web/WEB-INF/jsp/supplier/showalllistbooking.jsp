<%-- 
    Document   : booking
    Created on : Aug 6, 2015, 3:03:36 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Booking Waiting From Caterer</title>
        <script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
        <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
        <script type='text/javascript' >
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
            //window.onpaint = setCatererId();
        </script>
    </head>
    <body >
        
        <a href="${pageContext.request.contextPath}/checkbooking/showlistshipping.htm">Show all bills shipping</a><br>
        <a href="${pageContext.request.contextPath}/checkbooking/showlistcompleted.htm">Show all bills Completed</a>
        <a href="${pageContext.request.contextPath}/checkbooking/showlistcancel.htm">Show all bills Cancel</a>
        <h1>List Booking waiting</h1>
        <table border="1">
            <tr>
                <td>Caterer Name</td>
                <td>Invoice Date</td>
                <td>Delivery Date</td>
                <td>Status</td>
                <td colspan="4">Option</td>
            </tr>
            <c:forEach items="${listbookings}" var="booking">
                <tr>
                    <td>${booking.caterers.catererName}</td>
                    <td>${booking.invoiceDate}</td>
                    <td>${booking.deliveryDate}</td>
                    <td>${booking.status}</td>
                    <td><button onclick="showdetails(${booking.id});" >View Details</button></td>
                    <form><td><input type="submit" value="Shipping" onclick="form.action ='${pageContext.request.contextPath}/checkbooking/shipping/${booking.id}.htm';"></td>
                    <td><input type="submit" value="Cancel" onclick="form.action ='${pageContext.request.contextPath}/checkbooking/cancel/${booking.id}.htm';"></td></form>
                </tr> 
            </c:forEach>
        </table>
        <div id="result"></div>
        
        <script>
            function showdetails(id) {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/checkbooking/showdetails.htm",
                    cache: true,
                    data: 'idbooking=' + id,
                    success: function (data) {
                        $('#result').html(data);
                    },
                    error: function () {
                        window.location.href = '${pageContext.request.contextPath}/account/login.htm';
                    }
                });
            }
        </script>
    </body>
</html>
