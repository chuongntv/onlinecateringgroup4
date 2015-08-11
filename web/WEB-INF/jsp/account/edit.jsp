<%-- 
    Document   : edit
    Created on : Jul 23, 2015, 4:24:42 PM
    Author     : Nathan Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@include file="../include/header.jsp" %>
<title>Edit Account</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<!--        <script src="//code.jquery.com/jquery-1.10.2.js"></script>-->
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>  
<h2>Edit Account!</h2>
<spring:form action="../edit.htm" commandName="account" method="POST" class="form-horizontal" role="form">
    <input type="hidden" name="id" value="${account.id}"/>
    <input type="hidden" name="username" value="${account.username}"/>
    <h2>${account.username}</h2>
    Password: <spring:input path="password" required="required"/><br/>
    User Group: <spring:radiobutton path="userGroup" value="customer"/>Customer
    <spring:radiobutton path="userGroup" value="caterer" />Caterer
    <spring:radiobutton path="userGroup" value="supplier"/>Supplier
    <spring:radiobutton path="userGroup" value="admin"/>Admin<br/>
    Email: <spring:input type="email" path="email" required="required"/><br/>
    Full Name:<spring:input path="fullName" required="required"/><br/>
    Address:<spring:input path="address"/><br/>
    Date Of Birth:<spring:input path="dateOfBirth"/><br/>
    Phone Number:<spring:input path="phoneNumber"/><br/>
    Status: <spring:radiobutton path="status" value="0"/>Active
    <spring:radiobutton path="status" value="1"/>Inactive
    <button type="submit" class="btn btn-primary"> Save</button>
</spring:form>
<a  href="${pageContext.request.contextPath}/account/index.htm" class="btn btn-primary">Back to list</a>
<%@include file="../include/footer.jsp" %>
<script>
    $(function () {
        $("#dateOfBirth").datepicker();
    });
</script>
</body>
</html>
