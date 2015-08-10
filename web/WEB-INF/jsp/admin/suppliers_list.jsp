<%-- 
    Document   : suppliers_showall
    Created on : Jul 18, 2015, 9:19:13 AM
    Author     : MSI
--%>



<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>


   
        <%@include file="../include/header.jsp" %>
        <h1>Supplier List</h1>
        <form action="search.htm" method="post">
            <input type="text" name="name" placeholder="Input name, phone, email"/>
            <button type="submit"  >Search</button>
        </form>

        <br>

        <table border="1" style="width: 100%">
            <thead>
            <td>ID</td>
            <td>Name</td>
            <td>Email</td>
            <td>Phone</td>
        </thead>
        <c:forEach items="${suppliers}" var="supplier">
            <tbody>

            <td>
                ${supplier.id}
            </td>
            <td>
                ${supplier.supplierName}
            </td>
            <td>
                ${supplier.supplierEmail}
            </td>
            <td>
                ${supplier.supplierPhoneNumber}
            </td>  
            
            <td>
                ${supplier.countries.countryName}
            </td>
            
            <td><a data-toggle="tooltip" title="Edit" href="${pageContext.request.contextPath}/suppliers/edit/${supplier.id}.htm"> Edit</a></td>
            <td><a data-toggle="tooltip" title="Detail" href="${pageContext.request.contextPath}/suppliers/detail/${supplier.id}.htm" onclick ="return confirm('Are you sure ?')" >Delete</a></td>

        </tbody>
    </c:forEach>
</table>
        <br>
        <a href="${pageContext.request.contextPath}/suppliers/create_account.htm">Create Supplier</a>
        <%@include file="../include/footer.jsp" %>
    

