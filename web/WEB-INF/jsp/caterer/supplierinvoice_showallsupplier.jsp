<%-- 
    Document   : supplierinvoice_showall
    Created on : Aug 1, 2015, 9:22:23 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice</title>
        <script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
        <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
        <script >
            function setCatererId() {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/ordercaterer/setUserId.htm",
                    cache: true,
                    data: 'catererId=${userId}',
                    success: function (data) {
                        if (data == "error") {
                            window.location.href = '${pageContext.request.contextPath}/account/login.htm';
                        }
                    },
                    error: function () {
                        alert('Error while request..');
                    }
                });
            }
            setCatererId();
        </script>
    </head>
    <body onload="setCatererId();">
        <%@include file="../include/header.jsp" %>
        <h1>List Suppliers</h1>       
        <c:forEach items="${suppliers}" var="supplier">
            <a href="${pageContext.request.contextPath}/ordercaterer/listmaterialcategories/${supplier.id}.htm" class="btn btn-primary">
                <div style="background-color: #CCCCFF">
                    <h1>${supplier.supplierName}</h1>
                </div>
            </a>
        </c:forEach> 
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
