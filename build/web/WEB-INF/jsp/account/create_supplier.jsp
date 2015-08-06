<%-- 
    Document   : create_supplier
    Created on : Jul 24, 2015, 9:34:36 PM
    Author     : Nathan Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creating supplier</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script type="text/javascript">
//            $(function () {
//                $("#country.id").autocomplete({
//                    source: function (request, response) {
//                        $.getJSON("../account/getCountries.htm", {
//                            term: request.term
//                        }, response);
//                    }
//                });
//            });
//            $(document).ready(function () {
//                //attach autocomplete
//                $("#countries.id").autocomplete({
//                    minLength: 1,
//                    delay: 500,
//                    //define callback to format results
//                    source: function (request, response) {
//                        $.getJSON("${pageContext.request.contextPath}/account/getCountries", request, function (result) {
//                            response($.map(result, function (item) {
//                                return {
//                                    // following property gets displayed in drop down
//                                    label: item.countryName,
//                                    // following property gets entered in the textbox
//                                    value: item.id,
//                                    // following property is added for our own use
//                                    //tag_url: "http://" + window.location.host + "/tags/" + item.tagId + "/" + item.name
//                                }
//                            }));
//                        });
//                    }
//                });
//            });            
//            $(document).ready(function () {
//                $('#UserInfo_Email').autocomplete(
//                        {
//                            minLength: 0,
//                            source: '@Url.Action("GetAllStudent", "UserInfoes")',
//                            focus: function (event, ui) {
//                                $("#UserInfo_Email").val(ui.item.Id);
//                                return false;
//                            },
//                            select: function (event, ui) {
//                                $("#StudentId").val(ui.item.Id);
//                                $("#UserInfo_Email").val(ui.item.Email);
//                                return false;
//                            },
//                            appendTo: $("#myModal")
//                        })
//                        .autocomplete("instance")._renderItem = function (ul, item) {
//                    return $("<li>").append(item.id + "<br/><b>" + item.countryName + "</b>").appendTo(ul);
//                };
//
//            })
        </script>        
    </head>
    <body>
        <h1>Supplier information!</h1>
        <spring:form action="create_supplier.htm" commandName="supplier" method="POST" class="form-horizontal" role="form">       
            <h3>Account Id: ${user.id}</h3><input type="hidden" name="accounts.id" value="${user.id}"/>
            Country: <spring:input path="countries.id"/><br/>
            <spring:errors path="countries.id"/>
            Supplier Full Name:<spring:input path="supplierName"/><br/>
            Email: <spring:input path="supplierEmail"/><br/>            
            Address:<spring:input path="supplierAddress"/><br/>
            Description:<spring:textarea path="supplierDescription"/><br/>
            Phone Number:<spring:input path="supplierPhoneNumber"/><br/>            
            <button type="submit"> Create</button>
        </spring:form>
    </body>


</html>
