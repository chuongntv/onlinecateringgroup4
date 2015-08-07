<%-- 
    Document   : supplierinvoice_material
    Created on : Aug 1, 2015, 10:26:46 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order For Caterer</title>
        <script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
        <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
    </head>
    <body>
        <style type="text/css" media="screen">
            #parent {
                overflow: hidden;
                margin: 0;
                display: inline-block;
            }
            .left{
                float: left;
                width: 125px;
                text-align: left;
                margin: 2px 10px;
                display: inline;
                border: 1px solid red;
            }
            .right{
                float: left;
                text-align: left;
                margin: 2px 10px;
                display: inline;
                border: 1px solid red;
            } 
        </style>

        <h1>Order Materials</h1>
        <div id="parent">
            <div class="left">
                <c:forEach items="${categories}" var="category">
                    <button class="cate" id="${category.id}" style="background-color: antiquewhite" onclick="showMaterials(${category.id});">
                        ${category.categoryName}    
                    </button>   
                    <br>
                </c:forEach>
            </div>
            
            <div class="right" id="result">
            </div>

        </div>
        <div id="myBill">
        </div>
        <h1>List My Bill</h1>
        <table border="1">
            <tr>
                <td>Supplier Name</td>
                <td>Invoice Date</td>
                <td>Delivery Date</td>
                <td>Status</td>
            </tr>
            <c:forEach items="${listinvoices}" var="invoice">
                <tr>
                    <td>${invoice.suppliers.supplierName}</td>
                    <td>${invoice.invoiceDate}</td>
                    <td>${invoice.deliveryDate}</td>
                    <td>${invoice.status}</td>
                </tr> 
            </c:forEach>
        </table>
        <script type="text/javascript">
            function chooseDeliveryDate(){
                window.location.href = "${pageContext.request.contextPath}/ordercaterer/listMyBill.htm";
            }
            
            function showMaterials(id) {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/ordercaterer/listmaterials.htm",
                    cache: true,
                    data: 'categoryId=' + id,
                    success: function (data) {
                        $('#result').html(data);
                    },
                    error: function () {
                        alert('Error while request..');
                    }
                });
            }

            function addToBill() {
                setCatererId();
                var counts = document.getElementsByClassName('amount');
                var text = "";
                for (var i = 0; i < counts.length; i++) {
                    text += counts[i].value + "-";
                }
                ;
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/ordercaterer/addtobill.htm",
                    cache: true,
                    data: 'valueaddtobill=' + text,
                    success: function (resulst) {
                        $('#myBill').html(resulst);
                    },
                    error: function () {
                        alert('Error while request..');
                    }
                });
                
            }
            
            function setCatererId() {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/ordercaterer/setUserId.htm",
                    cache: true,
                    data: 'catererId=${userId}',
                    success: function () {
                    },
                    error: function () {
                        window.location.href = '${pageContext.request.contextPath}/account/login.htm';
                    }
                });
            }
            
        </script>
    </body>
</html>
