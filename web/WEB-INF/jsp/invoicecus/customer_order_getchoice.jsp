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
        <title>Search Menu</title>
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h3 style="text-align: center">Search menu</h3>
        <div>
            <div style="width: 600px;margin-left: auto;margin-right: auto">
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
                    <td><input type="number" name="numberofpeople" required="true"/></td>
                </tr>

            </table>





            <input type="submit" value="Next"/>
        </form>
            </div>
        </div>
        
       
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
