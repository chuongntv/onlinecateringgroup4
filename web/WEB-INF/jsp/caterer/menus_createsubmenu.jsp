<%-- 
    Document   : menus_createsubmenu
    Created on : Aug 3, 2015, 3:53:12 PM
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
        <spring:form action="createsubmenu.htm" method="POST" commandName="submenu">
            <input name="menus.id" value="${submenu.menus.id}" hidden="true">
            <table>
                <tr>
                    <td>Sub menu name:</td>
                    <td><input name="subMenuName" type="text" required="true"/></td>
                </tr>
                <tr>
                    <td>Number of food:</td>
                    <td><input name="numberOfFood" type="number" required="true" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Create"/></td>
                </tr>
            </table>
        </spring:form>
    </body>
</html>
