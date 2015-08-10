<%-- 
    Document   : suppliers_create_supplier
    Created on : Jul 22, 2015, 9:42:58 AM
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
         <%@include file="../include/header.jsp" %>
        <h3>Step2: Create supplier</h3>
        <span>${message}</span>
        <spring:form action="create_supplier.htm" commandName="supplier" role="form"  method="POST" >
            <input type="text" readonly="true" hidden="true" name="accounts.id" value="${supplier.accounts.id}" />

            <table>
                <tr>
                    <td>Supplier Name:</td>
                    <td><input name="supplierName" required="true" type="text" /></td>
                </tr>
                <tr>
                    <td>Supplier Email:</td>
                    <td><input name="supplierEmail" required="true" type="text" /></td>
                </tr>
                <tr>
                    <td>Supplier Phone:</td>
                    <td><input name="supplierPhoneNumber" required="true" type="text"/></td>
                </tr>
                <tr>
                    <td>Supplier Country:</td>
                    <td>            <select name="countries.id">
                            <c:forEach var="country" items="${listcountries}">
                                <option value="${country.id}">${country.countryName} </option>
                            </c:forEach> 
                        </select></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input name="supplierAddress" required="true" type="text"/></td>
                </tr>
                <tr>
                    <td>Description:</td>
                    <td><textarea name="supplierDescription" ></textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Create Supplier" /></td>
                </tr>
            </table>








        </spring:form>
               <%@include file="../include/footer.jsp" %>
    </body>
</html>
