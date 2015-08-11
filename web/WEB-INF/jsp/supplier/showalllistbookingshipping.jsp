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
        <script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
        <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
        <title>List Booking Shipping</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <a href="${pageContext.request.contextPath}/checkbooking/listbooking.htm" class="btn btn-primary">Show all bills Waiting</a>
        <a href="${pageContext.request.contextPath}/checkbooking/showlistshipping.htm" class="btn btn-primary">Show all bills shipping</a>
        <a href="${pageContext.request.contextPath}/checkbooking/showlistcompleted.htm" class="btn btn-primary">Show all bills Completed</a>
        <a href="${pageContext.request.contextPath}/checkbooking/showlistcancel.htm" class="btn btn-primary">Show all bills Cancel</a>
        <h1>List Booking Shipping</h1>
        <table class="table table-striped">
            <tr>
                <td><strong>Caterer Name</strong></td>
                <td><strong>Invoice Date</strong></td>
                <td><strong>Delivery Date</strong></td>
                <td><strong>Status</strong></td>
                <td colspan="4"><strong>Option</strong></td>
            </tr>
            <c:forEach items="${listbookingsshipping}" var="booking">
                <tr>
                    <td>${booking.caterers.catererName}</td>
                    <td>${booking.invoiceDate}</td>
                    <td>${booking.deliveryDate}</td>
                    <td>${booking.status}</td>
                    <td><button onclick="showdetails(${booking.id});" class="btn btn-info">View Details</button></td>
                <form><td><input type="submit" value="Completed" onclick="form.action = '${pageContext.request.contextPath}/checkbooking/completedshipping/${booking.id}.htm';" class="btn btn-primary"></td></form>
            </tr> 
        </c:forEach>
    </table>

    <div id="resultdetailsshipping"></div>

    <script type="text/javascript">
        function showdetails(id) {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/checkbooking/showdetails.htm",
                cache: true,
                data: 'idbooking=' + id,
                success: function (data) {
                    $('#resultdetailsshipping').html(data);
                },
                error: function () {
                    alert("Something went wrong...");
                }
            });
        }
    </script>
    <%@include file="../include/footer.jsp" %>
</body>
</html>
