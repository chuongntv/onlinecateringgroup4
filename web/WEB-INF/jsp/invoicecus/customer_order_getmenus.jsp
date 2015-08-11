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
        <title>Choose menu</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>

        
        <div>
            <h3 style="text-align: center">List menu</h3>
            <div style="margin-left: auto;margin-right: auto">
                 <c:forEach items="${listmenu}" var="menu">

                     <div style="margin: 50px">
                <table >
                    <tr>
                        <td><div>

                                <a href="${pageContext.request.contextPath}/order/choosefoods/${menu.id}.htm"> <img src="${pageContext.request.contextPath}${menu.image}" width="70" height="100"/></a>
                            </div></td>
                            <td><div style="margin-left: 20px">
                                <h4><a href="${pageContext.request.contextPath}/order/choosefoods/${menu.id}.htm">${menu.menuName}</a></h4>
                                Cost per plate: ${menu.costPerPlate} $/Plate <br>
                                Caterer Name: ${menu.caterers.catererName}
                            </div></td>
                    </tr>
                </table>
            </div>

        </c:forEach>

            </div>
            
        </div>
        
       

       

        <%@include file="../include/footer.jsp" %>
    </body>
</html>
