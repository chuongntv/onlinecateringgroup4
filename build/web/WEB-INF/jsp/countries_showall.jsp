<%-- 
    Document   : countries
    Created on : Jul 13, 2015, 5:21:19 PM
    Author     : admin
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
        Give me a id for userId: <input type="text" name="userId"/>
        
            Country ID: <input type="text" name="countryId"/>
            <input type="submit" value="Find"/>
            <a href="${pageContext.request.contextPath}/country/find/2.htm"> Find</a>
        
        
        <h2>${message}</h2>
        <h1>Countries List</h1>
        <table border="1" class="table table-striped table-hover ">
            <tr class="info">                
                <th>ID</th>                
                <th>Country Name</th>
                <th colspan="2">Options</th>
            </tr>
            <c:forEach items="${countries}" var="country">
                <tr>
                    <td>${country.id}</td>                    
                    <td>${country.countryName}</td>
                                        
                    <td><a data-toggle="tooltip" title="Edit Country" href="${pageContext.request.contextPath}/country/edit/${country.id}.htm"><span class="glyphicon glyphicon-edit"></span> Edit</a></td>
                    <td><a data-toggle="tooltip" title="Delete Country" href="${pageContext.request.contextPath}/country/delete/${country.id}.htm" onclick ="return confirm('Are you sure ?')" ><span class="glyphicon glyphicon-trash"></span> Delete</a></td>
                </tr> 
            </c:forEach>
        </table> 
        <a href="${pageContext.request.contextPath}/country/create.htm">Create a country</a>
    </body>
</html>
