<%-- 
    Document   : showall
    Created on : Jul 24, 2015, 7:36:43 PM
    Author     : admin
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@page import="java.sql.*" %>
<%@page language="java" contentType="text/html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List of Worker Types</h1>
        <table border="" >
            <tr class="info">               
                <th>Type Name</th>
                <th>Pay Per Day</th>
                <th colspan="2">Options</th>
            </tr>
            <c:forEach items="${types}" var="type">
                <tr>         
                    <td>${type.workerTypeName}</td>
                    <td>${type.payPerDay}</td>                    
                    <td><a href="${pageContext.request.contextPath}/test/edit/${type.idStr}.htm">Edit</a></td>
                </tr> 
            </c:forEach>
        </table> 

        <script type="text/javascript">
            function getState()
            {
                var xmlHttp;
                var x = document.getElementById("fillState");
                x.length = 0;
                document.getElementById("fillCity").length = 0;

                if (x.value != -1) {
                    try
                    {
                        // Firefox, Opera 8.0+, Safari
                        xmlHttp = new XMLHttpRequest();
                    }
                    catch (e)
                    {
                        // Internet Explorer
                        try
                        {
                            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                        }
                        catch (e)
                        {
                            try
                            {
                                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                            }
                            catch (e)
                            {
                                alert("Your browser does not support AJAX!");
                                return false;
                            }
                        }
                    }
                    xmlHttp.onreadystatechange = function ()
                    {
                        if (xmlHttp.readyState == 4)
                        {
                            var showdata = xmlHttp.responseText;
                            var str = showdata.split(":");
                            var len = str.length;
                            var i = 0;
                            for (i = 0; i < len; ) {
                                var option = document.createElement("option");
                                option.text = str[i];
                                option.value = str[i + 1];
                                try
                                {
                                    // for IE earlier than version 8
                                    x.add(option, x.options[null]);
                                }
                                catch (e)
                                {
                                    x.add(option, null);
                                }
                                i = i + 2;
                            }
                        }
                    }
                    var val = document.getElementById("fillCountry").value;
                    var url = "getOption.jsp?opt=" + val + "&table=state";
                    xmlHttp.open("GET", url, true);

                    xmlHttp.send(null);
                }

            }

            function getCity() {
                var xmlHttp;
                var x = document.getElementById("fillCity");
                x.length = 0;

                if (x.value != -1) {
                    try
                    {
                        // Firefox, Opera 8.0+, Safari
                        xmlHttp = new XMLHttpRequest();
                    }
                    catch (e)
                    {
                        // Internet Explorer
                        try
                        {
                            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                        }
                        catch (e)
                        {
                            try
                            {
                                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                            }
                            catch (e)
                            {
                                alert("Your browser does not support AJAX!");
                                return false;
                            }
                        }
                    }
                    xmlHttp.onreadystatechange = function ()
                    {
                        if (xmlHttp.readyState == 4)
                        {
                            var showdata = xmlHttp.responseText;
                            var str = showdata.split(":");
                            var len = str.length;
                            var i = 0;
                            for (i = 0; i < len; ) {
                                var option = document.createElement("option");
                                option.text = str[i];
                                option.value = str[i + 1];
                                try
                                {
                                    // for IE earlier than version 8
                                    x.add(option, x.options[null]);
                                }
                                catch (e)
                                {
                                    x.add(option, null);
                                }
                                i = i + 2;
                            }
                        }
                    }
                    var val = document.getElementById("fillState").value;
                    var url = "getOption.jsp?opt=" + val + "&table=city";
                    xmlHttp.open("GET", url, true);

                    xmlHttp.send(null);
                }
            }
        </script>
        <table>
            <form name="myForm">
                <%
                    Connection con = null;
                    Statement stat = null;
                    ResultSet rs = null;
                    String url = "jdbc:sqlserver://104.155.219.40:1433;databaseName=OnlineCateringDB";
                    String driver = "com.sqlserver.jdbc.Driver";
                    String userName = "sa";
                    String password = "abc123@@";
                    try {
                        Class.forName(driver).newInstance();
                        con = DriverManager.getConnection(url, userName, password);
                        stat = con.createStatement();
                        rs = stat.executeQuery("Select * from Countries");
                %>
                <tr>
                    <td>Country : </td>
                    <td>
                        <select id ="fillCountry" onChange="getState();">
                            <option value=-1>Select</option>
                            <%
                                    while (rs.next()) {
                                        out.println("<option value=" + rs.getInt(1) + ">" + rs.getString(2) + "</option>");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>State : </td>
                    <td>
                        <select id = "fillState" onChange = "getCity();">
                            <option>Select</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>City : </td>
                    <td>
                        </select>
                        <select id = "fillCity">
                            <option>Select</option>
                        </select>
                    </td>
                </tr>
            </form>
        </table>
    </body>
</html>
