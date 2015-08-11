<%-- 
    Document   : customer_order_getmenus
    Created on : Jul 23, 2015, 12:12:10 PM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h3>List menu</h3>
        <table>
            <tr>
                <td>
                    Menu Name
                </td>
                <td>
                    Cost
                </td>
                <td>
                    Caterer Name
                </td>
            </tr>
            <c:forEach items="${listmenu}" var="menu">
            <tr>
               
                <td>
                    <a href="${pageContext.request.contextPath}/order/choosefoods/${menu.id}.htm">${menu.menuName}</a>
                </td>
                <td>
                    ${menu.costPerPlate} $/Plate
                </td>
                <td>
                    ${menu.caterers.catererName}
                </td>
            </tr>
            </c:forEach>
        </table>
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
