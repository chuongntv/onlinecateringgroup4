<%-- 
    Document   : suppliers_edit
    Created on : Jul 19, 2015, 7:47:52 AM
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script> 
        <script type="text/javascript" src="http://dev.jquery.com/view/trunk/plugins/validate/jquery.validate.js"></script> 


</head>
<body>

    <spring:form action="../edit.htm" method="POST" commandName="supplier" onSubmit="validate(); return false;" role="form" id="edit-form" >   
        <input type="hidden" name="id" value="${requestScope.id}"/> 
        <input type="text" hidden="true" name="accounts.id" value="${supplier.accounts.id}" />
        <h3>Edit supplier ${supplier.supplierName}</h3>
        <table>
            <tr>
                <td>
                    Name:
                </td>
                <td>
                    <input name="supplierName" value="${supplier.supplierName}" required="true" />
                   
                    </td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input name="supplierEmail" id="email" value="${supplier.supplierEmail}" onchange="checkemail()" required="true" data-validation="email" type="text" /><span id="erroremail"></span></td>
            </tr>
            <tr>
                <td>Phone Number:</td>
                <td><input name="supplierPhoneNumber" value="${supplier.supplierPhoneNumber}" id="supplierPhoneNumber"  /></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td> <input name="supplierAddress" value="${supplierAddress}"   /></td>
            </tr>
            <tr>
                <td>Country:</td>
                <td><select name="countries.id">

                        <c:forEach items="${listcountries}" var="country" >
                            <c:choose>
                                <c:when test="${country.id == supplier.countries.id}">
                                    <option selected="true" value="${country.id}">${country.countryName} </option>

                                </c:when>
                                <c:otherwise>
                                    <option value="${country.id}">${country.countryName} </option>
                                </c:otherwise>                              
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Description: </td>
                <td><textarea name="supplierDescription"   >${supplierDescription}</textarea></td>
            </tr>
            <tr>
                <td></td>
                <td> <button type="input" id="btn"  id='validate'>Edit</button></td>
            </tr>
        </table>
        <!--    <input path="countries.id" />  -->
    </spring:form>
 <script>
            //   $(document).ready(function (){
            function checkemail() {
                if($("#email").val().search("@") <0){
                    $("#erroremail").text("Email is nois valid");
                    $("#btn").hide();
                    return false;
                }else{
                    $("#erroremail").text("");
                    $("#btn").show();
                    return  true;
                }
            }

            //    });

        </script>


</body>

</html>
