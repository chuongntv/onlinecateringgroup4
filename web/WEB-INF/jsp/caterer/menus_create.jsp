<%-- 
    Document   : menus_create
    Created on : Aug 2, 2015, 10:12:56 PM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Menu</title>
    </head>
    <body>
        <spring:form action="create.htm" commandName="menu" enctype="multipart/form-data" method="POST" class="form-horizontal" role="form">
            <table>
                <tr>
                    <td>
                        Menu Name:
                    </td>
                    <td>
                        <input type="text" name="menuName" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Categorytypes:
                    </td>
                    <td>
                        <select name="categoryTypes.id">
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.id}">${category.categoryTypeName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Cost per plate:
                    </td>
                    <td>
                        <input type="number" name="costPerPlate" step="0.01" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Min plate:
                    </td>
                    <td>
                        <input type="number" name="minPlate" required="true"/>
                        <span>${minplateerror}</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        Max plate:
                    </td>
                    <td>
                        <input type="number" name="maxPlate" required="true"/>
                        <span>${maxplateerror}</span>
                    </td>
                </tr>
                <tr>
                    <td>

                    </td>
                    <td>
                        <input type="submit" value="Create"/>
                    </td>
                </tr>

            </table>







        </spring:form>
    </body>
</html>
