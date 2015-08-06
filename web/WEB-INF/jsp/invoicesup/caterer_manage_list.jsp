<%-- 
    Document   : caterer_manage_list
    Created on : Jul 31, 2015, 2:14:04 AM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Order</title>
    </head>
    <body>
        <h1>Manage Supplier Invoice</h1>
        <table style="width: 100%">
            <tr style="font-style: oblique">
                <td>
                    ID
                </td>
                <td>
                    Supplier Name
                </td>
                <td>
                    Invoice Date
                </td>
                <td>
                    Delivery Date
                </td>
                <td>
                    Status
                </td>
            </tr>
            <c:forEach items="${listinvoices}" var="invoice">
                <tr>
                    <td>${invoice.id}</td>
                    <td>${invoice.suppliers.supplierName}</td>
                    <td>${invoice.invoiceDate}</td>
                    <td>${invoice.deliveryDate}</td>
                    <td>${invoice.status}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/supplierinvoice/detail/${invoice.id}.htm">View Detail</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
