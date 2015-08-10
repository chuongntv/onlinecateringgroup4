<%-- 
    Document   : menus_createfood
    Created on : Aug 3, 2015, 10:11:18 AM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create food</title>
    </head>
    <body>
        
        <table>
            <spring:form action="../createfood.htm" method="POST" commandName="food">
                <tr>
                    <td>
                        Food name:
                    </td>
                    <td>
                        <input name="foodName" required="true"/>
                        <input name="subMenus.id" value="${submenuid}" hidden="true"/>
                    </td>
                    
                </tr>
                <tr>
                    <td><input type="radio" value="true" name="require"/> Is require</td>
                    
                </tr>
                <tr>
                    <td>
                        <input type="radio" value="false" name="require" checked="true"/> Not require
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Create"/>
                    </td>
                </tr>
            </spring:form>
        </table>
    </body>
</html>
