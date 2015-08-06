<%-- 
    Document   : caterer_manage_detail
    Created on : Jul 31, 2015, 10:44:39 AM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Supplier Child Invoice Detail</title>
    </head>
    <body>
        <div>
            ID Invoice: ${invoice.id} <br>
            Supplier Name: ${invoice.suppliers.supplierName} <br>
            Invoice Date: ${invoice.invoiceDate} <br>
            Delivery Date: ${invoice.deliveryDate} <br>
            Status: ${invoice.status} <br>
            Total Price: ${totalprice}
        </div>
        <div>
            <table style="width: 100%">
                <tr></tr>
                <c:forEach items="${listsupplierchildinvoices}" var="childinvoice" >
                    <tr>
                        <td>
                            ${childinvoice.id}
                        </td>
                        <td>
                            ${childinvoice.materialName}
                        </td>
                        <td>
                            ${childinvoice.quantity} 
                        </td>
                        <td>
                            ${childinvoice.materialPricePerUnit}
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
