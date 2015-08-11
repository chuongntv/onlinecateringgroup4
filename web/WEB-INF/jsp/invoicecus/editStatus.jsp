<%-- 
    Document   : edit
    Created on : Aug 3, 2015, 6:40:09 PM
    Author     : Nathan Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
        <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>        
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h1>Edit status</h1>        
        <spring:form action="../editStatus.htm" commandName="invoice" method="POST" class="form-horizontal" role="form">       
            Customer Invoice ID: <input type="text" readonly name="id" value="${invoice.id}"/><br/>      
            Status: <spring:radiobutton path="status" value="cancel"/>Cancel
            <spring:radiobutton path="status" value="waiting" />Waiting 
            <c:set var="userGroup" scope="session" value="${user.userGroup}"/>
            <c:if test="${userGroup=='caterer'}">
                <spring:radiobutton path="status" value="complete" />Complete</c:if>
                <br/>
                <input type="submit" value="Save" class="btn btn-primary"/> 
                <p></p>
                <a href="${pageContext.request.contextPath}/customerInvoice/index.htm"> Back to List</a>
        </spring:form> 
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
