<%-- 
    Document   : customer_order_choosefoods
    Created on : Jul 24, 2015, 10:23:39 AM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choose food</title>
          <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    </head>
    <body>
        <%@include file="../include/header.jsp" %>
        <h3 style="text-align: center">Choose your foods</h3>
        <div >
            <div style="width: 600px;margin-left: auto; margin-right: auto">
                  <span>${message}</span>
        <form action="../choosefoods.htm" method="post">


            <c:forEach items="${listsubmenus}" var="submenu">
                <div>

                    <h5 style="color: #404040; font-size: medium"> ${submenu.subMenuName}</h5>
                    <c:forEach items="${listfoods}" var="food">

                        <c:choose>
                            <c:when test="${food.subMenus.id == submenu.id}">
                                <P>

                                    <c:choose>
                                        <c:when test="${food.isRequire==1}">
                                           <img src="${pageContext.request.contextPath}${food.image}" width="50px" height="70px" /> <input name="foods" type="checkbox" value="${food.foodName}" checked="true" required="true">    ${food.foodName} (This food is required)   
                                        </c:when>
                                        <c:otherwise>
                                           <img src="${pageContext.request.contextPath}${food.image}" width="50px" height="70px" /> <input name="foods" type="checkbox" value="${food.foodName}"  >   ${food.foodName}   
                                        </c:otherwise>
                                    </c:choose>
                                            
                                </P>

                            </c:when>
                        </c:choose>
                    </c:forEach> 
                </div>
            </c:forEach> 
            <input type="submit" value="Next"/>
        </form>
            </div>
        </div>
            
        
      
         <%@include file="../include/footer.jsp" %>
    </body>
</html>
