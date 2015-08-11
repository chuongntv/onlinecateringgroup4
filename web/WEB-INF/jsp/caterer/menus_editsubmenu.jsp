<%-- 
    Document   : menus_editsubmenu
    Created on : Aug 3, 2015, 8:13:42 PM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Sub menu</title>
    </head>
    <body>
         <%@include file="../include/header.jsp" %>
        <spring:form action="../editsubmenu.htm" method="POST" commandName="submenu">
            <input name="id" value="${submenu.id}" >
            <table>
                <tr>
                    <td>Sub menu name:</td>
                    <td><input name="subMenuName" type="text" value="${submenu.subMenuName}" required="true"/></td>
                </tr>
                <tr>
                    <td>Number of food:</td>
                    <td><input name="numberOfFood" type="number" value="${submenu.numberOfFood}" required="true" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Edit"/></td>
                </tr>
            </table>
        </spring:form>
               <%@include file="../include/footer.jsp" %>
    </body>
</html>
