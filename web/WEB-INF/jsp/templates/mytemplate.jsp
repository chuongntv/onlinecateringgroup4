<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>  
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${title }</title>
<tiles:insertAttribute name="head" ignore="true"></tiles:insertAttribute>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/layout.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/prettyPhoto.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style_1.css" rel="stylesheet" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js" type="text/javascript" ></script>

<script src="${pageContext.request.contextPath}/js/cufon-yui.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/js/cufon-replace.js"  type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/js/Dynalight_400.font.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/js/tms-0.3.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/js/tms_presets.js" type="text/javascript" ></script>

<script src="${pageContext.request.contextPath}/js/jquery.easing.1.3.js" type="text/javascript" ></script>
<!--[if lt IE 9]><script type="text/javascript" src="${pageContext.request.contextPath}/js/html5.js"></script><![endif]-->
</head>
<body id="page1">


      <tiles:insertAttribute name="content"></tiles:insertAttribute>

<!--==============================footer=================================-->
<footer>
  <div class="main">
    <div class="aligncenter"> <span>Copyright &copy; Online Catering All Rights Reserved</span> Design by ChuongNtv </div>
  </div>
</footer>
<script type="text/javascript">Cufon.now();</script>
<script type="text/javascript">
$(window).load(function () {
    $('.slider')._TMS({
        duration: 1000,
        easing: 'easeOutQuint',
        preset: 'slideDown',
        slideshow: 7000,
        banners: false,
        pauseOnHover: true,
        pagination: true,
        pagNums: false
    });
});
</script>
</body>
</html>
