<%-- 
    Document   : index
    Created on : Jul 19, 2015, 10:24:31 AM
    Author     : Nathan Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Management</title>
        <!--<script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>-->
        <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
    </head>
    <body>
        <h1>Account Management</h1>
        <h2>Welcome ${user.username}</h2>                
        <table border="1">
            <tr class="info">                
                <th>ID</th>                
                <th>Customer Name</th>      
                <th>Worker Name</th>
                <th>Caterer Full Name</th>
                <th>Cost per Plate</th>
                <th>Plate quantity</th>
                <th>Delivery Date</th>
                <th>Delivery Name</th>
                <th>Delivery Email</th>
                <th>Delivery Phone Number</th>
                <th>Delivery Address</th>                              
                <th>Status</th>      
                <th colspan="3">Options</th> 
            </tr>
            <c:forEach items="${listInvoices}" var="list">
                <tr>
                    <td>${list.id}</td>                    
                    <td>${list.accounts.fullName}</td>
                    <th>${list.workers.workerName}</th>
                    <th>${list.caterers.catererName}</th>
                    <th>${list.costPerPlate}</th>
                    <th>${list.numberOfPlate}</th>
                    <th>${list.deliveryDate}</th>
                    <th>${list.deliveryName}</th>
                    <th>${list.deliveryEmail}</th>
                    <th>${list.deliveryPhoneNumber}</th>
                    <th>${list.deliveryAddress}</th>                                      
                    <th>${list.status}</th>  
                        <c:set var="userGroup" scope="session" value="${user.userGroup}"/>
                        <c:if test="${userGroup=='caterer'}">
                        <td><a data-toggle="tooltip" title="Edit" href="${pageContext.request.contextPath}/customerInvoice/edit/${list.id}.htm"><span class="glyphicon glyphicon-edit"></span> Edit Customer</a>
                        </td>
                    </c:if>
                    <td>
                        <c:set var="workerName" scope="session" value="${list.workers.workerName}"/>
                        <c:if test="${workerName!=null||userGroup=='customer'}">                        
                            <a data-toggle="tooltip" title="Edit" href="${pageContext.request.contextPath}/customerInvoice/editStatus/${list.id}.htm"><span class="glyphicon glyphicon-edit"></span> Edit Status</a>
                        </c:if>
                    </td>
                    <td><a href="${pageContext.request.contextPath}/customerInvoice/detail/${list.id}.htm">View Detail</a></td>
                </tr> 
            </c:forEach>
        </table>           
    </body>
</html>
