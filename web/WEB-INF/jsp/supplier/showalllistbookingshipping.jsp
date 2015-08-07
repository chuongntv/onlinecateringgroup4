<%-- 
    Document   : showalllistbookingshipping
    Created on : Aug 7, 2015, 4:41:52 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List Booking Shipping</h1>
        <table border="1">
            <tr>
                <td>Caterer Name</td>
                <td>Invoice Date</td>
                <td>Delivery Date</td>
                <td>Status</td>
                <td colspan="4">Option</td>
            </tr>
            <c:forEach items="${listbookingsshipping}" var="booking">
                <tr>
                    <td>${booking.caterers.catererName}</td>
                    <td>${booking.invoiceDate}</td>
                    <td>${booking.deliveryDate}</td>
                    <td>${booking.status}</td>
                    <td><button onclick="showdetails(${booking.id});" >View Details</button></td>
                    <td><button onclick="${pageContext.request.contextPath}/checkbooking/completed/${booking.id}.htm" >Cancel</button></td>
                </tr> 
            </c:forEach>
        </table>
                <div id="resultdetails"></div>
                <script>
            function showdetails(id) {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/checkbooking/showdetails.htm",
                    cache: true,
                    data: 'idbooking=' + id,
                    success: function (data) {
                        $('#resultdetails').html(data);
                    },
                    error: function () {
                        alert("Something went wrong...");
                    }
                });
            }
            </script>
    </body>
</html>
