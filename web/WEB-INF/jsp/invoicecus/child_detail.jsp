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
        <%@include file="../include/header.jsp" %>
        <h3>Customer Child Invoice</h3>
        <div>
            ID Invoice: ${invoice.id} <br>
            Customer Name: ${invoice.accounts.fullName} <br>
            Invoice Date: ${invoice.invoiceDate} <br>
            Delivery Date: ${invoice.deliveryDate} <br>
            Status: ${invoice.status} <br>            
        </div>
        <div>
            <table style="width: 100%" class="table table-striped" border="2">
                <tr>
                    <td>Id</td>                    
                    <td>Food Name</td>
                </tr>
                <c:forEach items="${listchild}" var="childinvoice" >
                    <tr>
                        <td>
                            ${childinvoice.id}
                        </td>
                        <td>
                            ${childinvoice.foodName}
                        </td>                        
                    </tr>
                </c:forEach>
            </table>
        </div>
            <%@include file="../include/footer.jsp" %>
    </body>
</html>
