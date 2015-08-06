<%-- 
    Document   : customer_order_getcountry
    Created on : Jul 23, 2015, 10:18:17 AM
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
        <h3>Choose your country</h3>
        <form action="getchoice.htm" method="post" >
            <select name="countryid">
                <c:forEach items="${countries}" var="country">
                    <option value="${country.id}">${country.countryName}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Next"/>
        </form>
    </body>
</html>
