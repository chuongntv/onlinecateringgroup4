<%-- 
    Document   : menus_list
    Created on : Jul 31, 2015, 10:37:17 PM
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
        <table style="width: 100%">
            <tr>
                <td>Name</td>
                <td>Categories</td>
                <td>Cost per plate</td>
                <td>Min plate</td>
                <td>Max plate</td>
                <td>Image</td>
                <td>Option</td>
            </tr>
            <c:forEach items="${listmenu}" var="menu">
                <tr>

                    <td>
                        <a href='${pageContext.request.contextPath}/menus/menudetail/${menu.id}.htm'>${menu.menuName}</a>

                    </td>
                    <td>
                        ${menu.categoryTypes.categoryTypeName}
                    </td>
                    <td>${menu.costPerPlate}</td>
                    <td>${menu.minPlate}</td>
                    <td>${menu.maxPlate}</td>
                    <td><a href="${pageContext.request.contextPath}/menus/editimage/${menu.id}.htm"><img src="${pageContext.request.contextPath}${menu.image}" height="50px" width="50px" /> </a></td>
                    <td><a href="${pageContext.request.contextPath}/menus/editmenu/${menu.id}.htm">Edit</a></td>
                
                </tr>
            </c:forEach>
        </table>
        <a href="${pageContext.request.contextPath}/menus/create.htm">Create menu</a>
    </body>
</html>
