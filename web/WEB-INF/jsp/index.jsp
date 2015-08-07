<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
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

    <body>
    <c:set var="user" scope="session" value="${user}"/>
    <c:if test="${user==null}">
        <a href="${pageContext.request.contextPath}/account/register.htm">Register</a><br><br>
        <a href="${pageContext.request.contextPath}/account/login.htm">Login</a><br><br>
    </c:if>
    <c:if test="${user!=null}">
        <a href="${pageContext.request.contextPath}/account/logout.htm">Logout</a><br><br>
    </c:if>
        <strong>User ID: ${userId}</strong>
        <h3>Supplier</h3>
        <a href="${pageContext.request.contextPath}/materialcategories/listCategories/${userId}.htm">List Material Categories</a><br>
        <a href="${pageContext.request.contextPath}/supplierinvoice/listinvoices.htm">Manage Supplier Invoices</a><br>
        <a href="${pageContext.request.contextPath}/checkbooking/listbooking.htm">Check Booking</a><br>
        <h3>Customer</h3>
        <a href="${pageContext.request.contextPath}/order/getcountry.htm">Order</a>

        <h3>Admin</h3>
        <a href="${pageContext.request.contextPath}/suppliers/listSupplier.htm">List Supplier</a><br>
        <a href="${pageContext.request.contextPath}/customers/listCustomers.htm">Management Customers</a><br>
        <a href="${pageContext.request.contextPath}/managercaterer/listCaterers.htm">Management Caterers</a>
        <h3>Caterer</h3>
        <a href="${pageContext.request.contextPath}/ordercaterer/listsuppliers.htm">Go to Order Page</a><br>
        <a href="${pageContext.request.contextPath}/manageworker/listTypes/${userId}.htm">Management Worker</a><br>
        <p>Hello! This is the default welcome page for a Spring Web MVC project.</p>
        <a href="${pageContext.request.contextPath}/country/listCountries.htm">Show all countries</a><br>
    </body>
</html>

