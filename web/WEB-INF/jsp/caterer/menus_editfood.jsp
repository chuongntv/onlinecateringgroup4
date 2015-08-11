<%-- 
    Document   : menus_editfood
    Created on : Aug 3, 2015, 10:28:45 PM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit food</title>
    </head>
    <body>
         <%@include file="../include/header.jsp" %>
         <table>
            <spring:form action="../editfood.htm" method="POST" commandName="food">
                <tr>
                    <td>
                        Food name:
                    </td>
                    <td>
                        <input name="foodName" value="${food.foodName}" required="true"/>
                        <input name="id" value="${food.id}" hidden="true"/>
                    </td>
                    
                </tr>
                <c:choose>
                    <c:when test="${food.isRequire ==0}">
                        <tr>
                    <td><input type="radio" value="true" name="require"/> Is require</td>
                    
                </tr>
                <tr>
                    <td>
                        <input type="radio" value="false"  name="require" checked="true"/> Not require
                    </td>
                </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                    <td><input type="radio" value="true" checked="true" name="require"/> Is require</td>
                    
                </tr>
                <tr>
                    <td>
                        <input type="radio" value="false" name="require" /> Not require
                    </td>
                </tr>
                    </c:otherwise>
                </c:choose>
                
                
                <tr>
                    <td>
                        <input type="submit" value="Edit"/>
                    </td>
                </tr>
            </spring:form>
        </table>
            <%@include file="../include/footer.jsp" %>
    </body>
</html>
