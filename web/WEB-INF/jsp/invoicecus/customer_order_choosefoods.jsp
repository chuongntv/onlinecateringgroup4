<%-- 
    Document   : customer_order_choosefoods
    Created on : Jul 24, 2015, 10:23:39 AM
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
        <h3>choose your foods</h3>
        <span>${message}</span>
        <form action="../choosefoods.htm" method="post">


            <c:forEach items="${listsubmenus}" var="submenu">
                <div>

                    <h4>${submenu.id} ${submenu.subMenuName}</h4>
                    <c:forEach items="${listfoods}" var="food">

                        <c:choose>
                            <c:when test="${food.subMenus.id == submenu.id}">
                                <P>

                                    <c:choose>
                                        <c:when test="${food.isRequire==1}">
                                            <input name="foods" type="checkbox" value="${food.foodName}" checked="true" required="true">    ${food.foodName} (This food is required)   
                                        </c:when>
                                        <c:otherwise>
                                            <input name="foods" type="checkbox" value="${food.foodName}"  >   ${food.foodName}   
                                        </c:otherwise>
                                    </c:choose>
                                            
                                </P>

                            </c:when>
                        </c:choose>
                    </c:forEach> 
                </div>
            </c:forEach> 
            <input type="submit" value="Next" class="btn btn-primary"/>
        </form>
        <%@include file="../include/footer.jsps" %>
    </body>
</html>
