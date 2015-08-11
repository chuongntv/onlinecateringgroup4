<%-- 
    Document   : menus_resultsubmenudelete
    Created on : Aug 4, 2015, 5:34:57 PM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
    </head>
    <body>
         <%@include file="../include/header.jsp" %>
        <h3>An error to delete</h3>
        <p>You can't delete sub menu "${submenu.subMenuName}" because have ${number} foods in this sub menu. Please delete them first</p>
        <div>
            <h3>List food need to delete</h3>
            <c:forEach items="${listfoods}" var="food">
                <p>${food.foodName}</p>
            </c:forEach>
        </div>
        <a href="${pageContext.request.contextPath}/menus/menudetail/${sessionScope.menuid}.htm">Back to menu detail</a>
     <%@include file="../include/footer.jsp" %>
    </body>
</html>
