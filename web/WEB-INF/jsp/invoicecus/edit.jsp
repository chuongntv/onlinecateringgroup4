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
        <h1>Set Worker</h1>        
        <spring:form action="../edit.htm" commandName="invoice" method="POST" class="form-horizontal" role="form">       
            <input type="hidden" name="id" value="${invoice.id}"/>
            Worker Type: <select name="workers.workerTypes.id" id="combo1" onchange="demo(this);" >
                <option value="0">Select Worker Type</option>
                <c:forEach var="item" items="${workerType}">
                    <option value="${item.id}"> ${item.workerTypeName}</option>
                </c:forEach> 
            </select>
            <div id="result"></div>
            Status: <spring:radiobutton path="status" value="cancel"/>Cancel
            <spring:radiobutton path="status" value="waiting" />Waiting 
            <spring:radiobutton path="status" value="complete" />Complete
            <br/>
            <input type="submit" class="btn btn-primary" value="Save"/> 
            <p></p>
            <a href="${pageContext.request.contextPath}/customerInvoice/index.htm" > Back to List</a>
        </spring:form>      
        <script type="text/javascript">
            function demo(s) {
                debugger
                //var id = $("#combo1").find('option:selected').attr('id'));
                var id = s[s.selectedIndex].value;
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/customerInvoice/chooseWorker.htm",
                    cache: true,
                    data: 'workerTypeId=' + id,
                    success: function (data) {
                        $('#result').html(data);
                    },
                    error: function () {
                        alert('Error while request..');
                    }
                });
            }
        </script>
        <%@include file="../include/footer.jsp" %>
    </body>
</html>
