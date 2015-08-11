<%-- 
    Document   : create_supplier
    Created on : Jul 24, 2015, 9:34:36 PM
    Author     : Nathan Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creating supplier</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>        
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h2>Create caterer!</h2>
        <spring:form action="create_caterer.htm" commandName="caterer" method="POST" class="form-horizontal" role="form">       
            <h3>Account Id: ${user.id}</h3><input type="hidden" name="accounts.id" value="${user.id}"/>
            City: <select name="cities.id">
                <c:forEach var="city" items="${listcities}">
                    <option value="${city.id}"> ${city.cityName}</option>
                </c:forEach> 
            </select><br/>
            Caterer Full Name:<spring:input path="catererName" required="required"/><br/>
            Email: <spring:input path="catererEmail" type="email" required="required"/><br/>            
            Address:<spring:input path="catererAddress" required="required"/><br/>            
            Phone Number:<spring:input path="catererPhoneNumber" type="number"/><br/>            
            <button type="submit" class="btn btn-primary">Create</button>
        </spring:form>
            <%@include file="../include/footer.jsp" %>
    </body>
</html>
