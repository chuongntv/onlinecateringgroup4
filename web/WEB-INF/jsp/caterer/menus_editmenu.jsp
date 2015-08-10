<%-- 
    Document   : menus_editmenu
    Created on : Aug 3, 2015, 6:54:07 PM
    Author     : MSI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit menu</title>
    </head>
    <body>
        <h3>Edit menu ${menu.menuName}</h3>
          <spring:form action="../../menus/editmenu.htm" commandName="menu" enctype="multipart/form-data" method="POST" class="form-horizontal" role="form">
              <input type="text" name="id" value="${menu.id}" hidden="true" readonly="true"/>
              <table>
                <tr>
                    <td>
                        Menu Name:
                    </td>
                    <td>
                        <input type="text" name="menuName" value="${menu.menuName}" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Category types:
                    </td>
                    <td>
                        <select name="categoryTypes.id">
                            <c:forEach items="${categories}" var="category">
                                <c:choose>
                                    <c:when test="${category.id == menu.categoryTypes.id}">
                                        <option selected="true" value="${category.id}">${category.categoryTypeName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${category.id}">${category.categoryTypeName}</option>
                                    </c:otherwise>
                                </c:choose>
                                
                                
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Cost per plate:
                    </td>
                    <td>
                        <input type="number" name="costPerPlate" value="${menu.costPerPlate}" step="0.01" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Min plate:
                    </td>
                    <td>
                        <input type="number" name="minPlate" value="${menu.minPlate}" required="true"/>
                        <span>${minplateerror}</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        Max plate:
                    </td>
                    <td>
                        <input type="number" name="maxPlate" value="${menu.maxPlate}" required="true"/>
                        <span>${maxplateerror}</span>
                    </td>
                </tr>
                <tr>
                    <td>

                    </td>
                    <td>
                        <input type="submit" value="Edit"/>
                    </td>
                </tr>

            </table>

        </spring:form>
    </body>
</html>
