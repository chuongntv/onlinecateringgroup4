<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
        <!--<script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>-->
        <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
        <script type='text/javascript' >
            function setCatererId() {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/checkbooking/setUserId.htm",
                    cache: true,
                    data: 'catererId=${userId}',
                    success: function (data) {
                        if (data == "error") {
                            window.location.href = '${pageContext.request.contextPath}/account/login.htm';
                        }
                    },
                    error: function () {
                        window.location.href = '${pageContext.request.contextPath}/account/login.htm';
                    }
                });
            }
           //window.onpaint = setCatererId();
        </script>
    </head>
<!--==============================header=================================-->
<header>
  <div class="row-top">
    <div class="main">
      <div class="wrapper">
        <h1><a href="index.html">Catering<span>.com</span></a></h1>
        <nav>
          <ul class="menu">
            <li><a href="${pageContext.request.contextPath}/index/index.htm">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/order/getcountry.htm">Booking </a></li>
            <li><a href="shipping.html">Supplier</a></li>
            <li><a href="faq.html">Caterer </a></li>
            <li><a href="contact.html">Administrator</a></li>
            <li><a href="contact.html">Login</a></li>
            <li><a href="contact.html">Register</a></li>
            <li><a href="${pageContext.request.contextPath}/test/testmapping.htm">Test</a></li>
            <a href="${pageContext.request.contextPath}/mytemplate/aboutus.htm">About Us</a> 
          </ul>
        </nav>
      </div>
    </div>
  </div>
  <div class="row-bot">
    <div class="row-bot-bg">
      <div class="main">
        <h2>Impressive Selection <span>for any Occasion</span></h2>
        <div class="slider-wrapper">
          <div class="slider">
            <ul class="items">
              <li> <img src="${pageContext.request.contextPath}/images/slider-img1.jpg" alt="" /> </li>
              <li> <img src="${pageContext.request.contextPath}/images/slider-img2.jpg" alt="" /> </li>
              <li> <img src="${pageContext.request.contextPath}/images/slider-img3.jpg" alt="" /> </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</header>
            <!--==============================content================================-->

<section id="content">
  <div class="main">
          <div class="wrapper img-indent-bot">
      <article class="col-1"> <a href="#"><img class="img-border" src="${pageContext.request.contextPath}/images/banner-1.jpg" alt=""></a> </article>
      <article class="col-1"> <a href="#"><img class="img-border" src="${pageContext.request.contextPath}/images/banner-2.jpg" alt=""></a> </article>
      <article class="col-2"> <a href="#"><img class="img-border" src="${pageContext.request.contextPath}/images/banner-3.jpg" alt=""></a> </article>
    </div>
    <div class="wrapper">
      <article class="column-1">
        <div class="indent-left">
          <div class="maxheight indent-bot">
            <h3>Our Services</h3>
            <ul class="list-1">
              <li><a href="#">Duis autem vel eum iriure dolor</a></li>
              <li><a href="#">Hendrerit in vulputate velit esse molestie </a></li>
              <li><a href="#">Consequat vel illum dolore</a></li>
              <li><a href="#">Feugiat nulla facilisis at vero eros</a></li>
              <li><a href="#">Accumsan et iusto odio dignissim qui</a></li>
              <li><a href="#">Blandit praesent luptatum azril</a></li>
              <li><a href="#">Delenit augue duis dolore te feugait</a></li>
            </ul>
          </div>
          <a class="button-1" href="#">Read More</a> </div>
      </article>
      <article class="column-2">
        <div class="maxheight indent-bot">
          <h3 class="p1">About the Catering</h3>
          <h6 class="p2">Catering is one of free website templates created by TemplateMonster.com team. This website template is optimized for 1280X1024 screen resolution. It is also XHTML &amp; CSS valid.</h6>
          <p class="p2">This Catering Template goes with two packages – with PSD source files and without them. PSD source files are available for free for the registered members of TemplatesMonster.com. The basic package (without PSD source) is available for anyone without registration.</p>
          This website template has several pages: <a href="index.html">About</a>, <a href="menu.html">Menu</a>, <a href="catalogue.html">Catalogue</a>, <a href="shipping.html">Shipping</a>, <a href="faq.html">FAQ</a>, <a href="contact.html">Contact</a> (note that contact us form – doesn’t work). </div>
        <a class="button-2" href="#">Read More</a> </article>
    </div>  
    <c:set var="user" scope="session" value="${user}"/>
    <c:if test="${user==null}">
        <a href="${pageContext.request.contextPath}/account/register.htm">Register</a><br><br>
        <a href="${pageContext.request.contextPath}/account/login.htm">Login</a><br><br>
    </c:if>
    <c:if test="${user!=null}">
        <a href="${pageContext.request.contextPath}/account/logout.htm">Logout</a><br><br>
    </c:if>
        <strong>User ID: ${userId}</strong>
        <h3>Supplier</h3>
        <a href="${pageContext.request.contextPath}/materialcategories/listCategories/${userId}.htm">List Material Categories</a><br>
        <a href="${pageContext.request.contextPath}/supplierinvoice/listinvoices.htm">Manage Supplier Invoices</a><br>
        <a href="${pageContext.request.contextPath}/checkbooking/listbooking.htm">Check Booking</a><br>
        <h3>Customer</h3>
        <a href="${pageContext.request.contextPath}/order/getcountry.htm">Order</a>

        <h3>Admin</h3>
        <a href="${pageContext.request.contextPath}/suppliers/listSupplier.htm">List Supplier</a><br>
        <a href="${pageContext.request.contextPath}/customers/listCustomers.htm">Management Customers</a><br>
        <a href="${pageContext.request.contextPath}/managercaterer/listCaterers.htm">Management Caterers</a>
        <h3>Caterer</h3>
        <a href="${pageContext.request.contextPath}/ordercaterer/listsuppliers.htm">Go to Order Page</a><br>
        <a href="${pageContext.request.contextPath}/manageworker/listTypes/${userId}.htm">Management Worker</a><br>
        <p>Hello! This is the default welcome page for a Spring Web MVC project.</p>
        <a href="${pageContext.request.contextPath}/country/listCountries.htm">Show all countries</a><br>
   
  </div>
</section>