<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title><decorator:title default="Win-Admin"/></title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="<c:url value="/assets/img/favicon.png" />" rel="icon">
  <link href="<c:url value="/assets/img/apple-touch-icon.png" />" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Jost:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="<c:url value="/assets/vendor/aos/aos.css" />" rel="stylesheet">
  <link href="<c:url value="/assets/vendor/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
  <link href="<c:url value="/assets/vendor/bootstrap-icons/bootstrap-icons.css" />" rel="stylesheet">
  <link href="<c:url value="/assets/vendor/boxicons/css/boxicons.min.css" />" rel="stylesheet">
  <link href="<c:url value="/assets/vendor/glightbox/css/glightbox.min.css" />" rel="stylesheet">
  <link href="<c:url value="/assets/vendor/remixicon/remixicon.css" />" rel="stylesheet">
  <link href="<c:url value="/assets/vendor/swiper/swiper-bundle.min.css" />" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="<c:url value="/assets/css/style.css" />" rel="stylesheet"/>
  <link href="<c:url value="/assets/css/admin.css" />" rel="stylesheet"/>

  <!-- =======================================================
  * Template Name: Arsha
  * Updated: Mar 10 2023 with Bootstrap v5.2.3
  * Template URL: https://bootstrapmade.com/arsha-free-bootstrap-html-template-corporate/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

	<main class="admin-container">
    	<div class="side-box">
      		<h1 class="admin-logo"><a href="<c:url value="/admin" />">Win-Admin</a></h1>
      		<ul class="big">
        		<li class="item-li1"><a href="<c:url value="/admin" />" class="text-li1">Dashboard</a></li>
        		<li class="item-li1">
          			<div class="click text-li1 d-flex align-items-center space-between">
            			<a href="#" class="table-text">Quản lý bảng</a>
            			<i class="bi bi-table"></i>
          			</div>
        		</li>
        		<ul class="small">
          			<li class="item-li2"><a class="text-li2" href="<c:url value="/admin/category" />">Danh mục</a></li>
          			<li class="item-li2"><a class="text-li2" href="<c:url value="/admin/product" />">Sản phẩm</a></li>
          			<li class="item-li2"><a class="text-li2" href="<c:url value="/admin/image" />">Hình ảnh</a></li>
          			<li class="item-li2"><a class="text-li2" href="<c:url value="/admin/account" />">Tài khoản</a></li>
          			<li class="item-li2"><a class="text-li2" href="<c:url value="/admin/cart" />">Giỏ hàng</a></li>
          			<li class="item-li2"><a class="text-li2" href="<c:url value="/admin/bill" />">Hóa đơn</a></li>
          			<li class="item-li2"><a class="text-li2" href="<c:url value="/admin/slide" />">Slide</a></li>
        		</ul>
        		<li class="item-li1"><a href="<c:url value="/admin/logout" />" class="text-li1">Đăng xuất</a></li>
      		</ul>
    	</div>
    	<div class="content-box">
      		
      		<decorator:body />
      		
    	</div>
	</main>
	
	<div id="preloader"></div>
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="<c:url value="/assets/vendor/aos/aos.js" />"></script>
  <script src="<c:url value="/assets/vendor/bootstrap/js/bootstrap.bundle.min.js" />"></script>
  <script src="<c:url value="/assets/vendor/glightbox/js/glightbox.min.js" />"></script>
  <script src="<c:url value="/assets/vendor/isotope-layout/isotope.pkgd.min.js" />"></script>
  <script src="<c:url value="/assets/vendor/swiper/swiper-bundle.min.js" />"></script>
  <script src="<c:url value="/assets/vendor/waypoints/noframework.waypoints.js" />"></script>

  <!-- Template Main JS File -->
  <script src="<c:url value="/assets/js/main.js" />"></script>
  <script src="<c:url value="/assets/js/validator.js" />"></script>
  <script src="<c:url value="/assets/jquery/jquery-3.6.4.min.js" />"></script>
  
  <decorator:getProperty property="page.script" ></decorator:getProperty>
  
  <script>
    var table = document.querySelector('.click')
    var small = document.querySelector('.small')
    table.onclick = function() {
      console.log(1)
      small.classList.toggle('active')
    }
  </script>

</body>

</html>