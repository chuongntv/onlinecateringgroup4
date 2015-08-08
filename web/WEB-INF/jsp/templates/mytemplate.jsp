<%-- 
    Document   : mytemplate
    Created on : Aug 8, 2015, 2:46:21 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title }</title>
    <tiles:insertAttribute name="head" ignore="true"></tiles:insertAttribute>
</head>
<body>
    <a href="${pageContext.request.contextPath}/mytemplate.htm">Home</a> | 
    <a href="${pageContext.request.contextPath}/mytemplate/aboutus.htm">About Us</a> |
    <a href="${pageContext.request.contextPath}/mytemplate/news.htm">News</a> 
    <br>
    <tiles:insertAttribute name="content"></tiles:insertAttribute>
    <br>
    Copyright 2014
</body>
</html>
