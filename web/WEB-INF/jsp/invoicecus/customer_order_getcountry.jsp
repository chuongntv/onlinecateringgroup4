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
         <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <title>Choose country</title>
    </head>
    <body>
         <%@include file="../include/header.jsp" %>
         
         <div>
             <h3 style="text-align: center">Choose your country</h3>
             <div style="width: 200px;margin-left: auto;margin-right: auto">
                 <form action="getchoice.htm" method="post"  >
            <select name="countryid">
                <c:forEach items="${countries}" var="country">
                    <option value="${country.id}">${country.countryName}</option>
                </c:forEach>
            </select>
            <input class="btn btn-default btn-sm" type="submit" value="Next"/>
        </form>
             </div>
         </div>
         
        
         <%@include file="../include/footer.jsp" %>
    </body>
</html>
