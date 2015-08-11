<%-- 
    Document   : menus_menudetail
    Created on : Aug 3, 2015, 12:21:52 AM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <title>Menu ${menu.menuName} detail</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>

        <div>
            <span>${message}</span>
            <h3 style="text-align: center">List food for menu ${menu.menuName}</h3>
            <div style="width: 400px;margin-left: auto;margin-right: auto;">
                <c:forEach items="${listsubmenus}" var="submenu">
                    <div>

                        <span style="font-size: medium;font-weight: bold"> ${submenu.subMenuName}</span>(
                        <a href="${pageContext.request.contextPath}/menus/editsubmenu/${submenu.id}.htm">Edit</a>
                        <a href="${pageContext.request.contextPath}/menus/createfood/${submenu.id}.htm">Add food</a>
                        <a href="${pageContext.request.contextPath}/menus/deletesubmenu /${submenu.id}.htm">Delete</a>)
                        <c:forEach items="${listfoods}" var="food">

                            <c:choose>
                                <c:when test="${food.subMenus.id == submenu.id}">
                                    <P>

                                        <c:choose>
                                            <c:when test="${food.isRequire==1}">
                                                <a href="${pageContext.request.contextPath}/menus/editfoodimage/${food.id}.htm"><img src="${pageContext.request.contextPath}${food.image}"height="50px" width="40px"/></a> ${food.foodName} (This food is required)   
                                                </c:when>
                                                <c:otherwise>
                                                <a href="${pageContext.request.contextPath}/menus/editfoodimage/${food.id}.htm"><img src="${pageContext.request.contextPath}${food.image}"height="50px" width="40px"/></a> ${food.foodName}   
                                                </c:otherwise>
                                            </c:choose>
                                        <a href="${pageContext.request.contextPath}/menus/editfood/${food.id}.htm">Edit</a> 
                                        <a data-toggle="tooltip" title="Delete Food" href="${pageContext.request.contextPath}/menus/deletefood/${food.id}.htm" onclick ="return confirm('Are you sure ?')" >Delete</a>


                                    </P>

                                </c:when>
                            </c:choose>

                        </c:forEach> 
                    </div>
                </c:forEach> 

                <a href="${pageContext.request.contextPath}/menus/createsubmenu.htm">Create SubMenu</a> |
                <a href="${pageContext.request.contextPath}/menus/listmenus.htm">Back to list menu</a> 
            </div>
        </div>

        <%@include file="../include/footer.jsp" %>
    </body>
</html>
