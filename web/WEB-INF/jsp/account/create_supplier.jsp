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
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>                
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>                

    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h2>Supplier information!</h2>
        <spring:form action="create_supplier.htm" commandName="supplier" method="POST" class="form-horizontal" role="form">       
            <h3>Account Id: ${user.id}</h3><input type="hidden" name="accounts.id" value="${user.id}"/>
            Country:<select name="countries.id">
                <c:forEach var="country" items="${listcountries}">
                    <option value="${country.id}">${country.countryName} </option>
                </c:forEach> 
            </select><br/>
            Supplier Full Name:<spring:input path="supplierName" required="required"/><br/>
            Email: <spring:input path="supplierEmail" type="email" required="required"/><br/>            
            Address:<spring:input path="supplierAddress" required="required"/><br/>
            Description:<spring:textarea path="supplierDescription"/><br/>
            Phone Number:<spring:input path="supplierPhoneNumber" type="number"/><br/>            
            <button type="submit" class="btn btn-primary"> Create</button>
        </spring:form>
        <%@include file="../include/footer.jsp" %>
    </body>


</html>
