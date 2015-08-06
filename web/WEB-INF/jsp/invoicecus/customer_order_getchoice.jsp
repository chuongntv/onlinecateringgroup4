<%-- 
    Document   : customer_order_getchoice
    Created on : Jul 23, 2015, 10:32:16 AM
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
        <h3></h3>
        <form method="post" action="getmenus.htm" >
            <table>
                <tr>
                    <td>Categories:</td>
                    <td>            <select name="cateid">
                            <c:forEach items="${catetypes}" var="cate">
                                <option value="${cate.id}">${cate.categoryTypeName}</option>
                            </c:forEach>
                        </select></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td>          <select name="cityid">
                            <c:forEach items="${cities}" var="city">
                                <option value="${city.id}"> ${city.cityName} </option>
                            </c:forEach>
                        </select></td>
                </tr>
                <tr>
                    <td>Number of people:</td>
                    <td><input type="number" name="numberofpeople"/></td>
                </tr>

            </table>





            <input type="submit" value="Next"/>
        </form>
    </body>
</html>
