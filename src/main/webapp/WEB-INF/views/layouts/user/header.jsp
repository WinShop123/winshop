<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<!-- ======= Header ======= -->
<c:if test="${ empty isHomepage }">
	<header id="header" class="fixed-top header-inner-pages">
</c:if>
<c:if test="${ not empty isHomepage }">
	<header id="header" class="fixed-top ">
</c:if>
    <div class="container d-flex align-items-center">
      <h1 class="logo me-auto"><a href="<c:url value="/" />">WinShop</a></h1>

      <nav id="navbar" class="navbar">
        <ul>
          <li><a class="nav-link scrollto active" href="<c:url value="/" />">Trang chủ</a></li>
          <li class="dropdown"><a href="<c:url value="/all" />"><span>Sản phẩm</span> <i class="bi bi-chevron-down"></i></a>
            <ul>
            <c:forEach var="item" items="${ categories }">
            	<li><a href="<c:url value="/category${ item.id }" />">${ item.name }</a></li>
            </c:forEach>
            </ul>
          </li>
          <!-- <li><a class="nav-link scrollto" href="#team">Tin tức</a></li> -->
          <li><a class="nav-link" href="<c:url value="/contact" />">Liên hệ</a></li>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->
      <nav class="navbar">
        <ul>
          <li class="display-flex-center">
            <form:form class="form-custom" action="/WinShop/search" modelAttribute="searchobject">
              <form:input path="name" id="search-bar" class="search-bar" type="text" placeholder="Search..." />
            </form:form>
            <i class="search-icon bi-search" style="font-size: 22px;"></i>
          </li>
          <li class="dropdown"><a href="<c:url value="/login" />" class="nav-link"><i class="bi bi-person-circle" style="font-size: 22px;"></i></a>
			<ul>
				<c:if test="${ not empty loginsession && loginsession.name != '' }">
					<li style="cursor: none"><a>Hi, ${ loginsession.name }!</a><li>
					<c:if test="${ rolesession == 'admin, user' }">
						<li><a href="<c:url value="/admin" />">Win-Admin</a></li>
					</c:if>
					<c:if test="${ rolesession == 'user' }">
						<li><a href="<c:url value="/bill" />">Đơn hàng của bạn</a></li>
					</c:if>
					<li><a href="<c:url value="/changepass" />">Đổi mật khẩu</a></li>
					<li><a href="<c:url value="/logout" />">Đăng xuất</a></li>
				</c:if>
				
				<c:if test="${ not empty loginsession && loginsession.name == '' }">
					<li><a href="<c:url value="/bill" />">Đơn hàng của bạn</a></li>
					<li><a href="<c:url value="/login" />">Đăng nhập</a></li>
					<li><a href="<c:url value="/signup" />">Đăng ký</a></li>
				</c:if>
				
				<c:if test="${ empty loginsession }">
					<li><a href="<c:url value="/login" />">Đăng nhập</a></li>
					<li><a href="<c:url value="/signup" />">Đăng ký</a></li>
				</c:if>
			</ul>
          </li>
          <li>
          		<a href="<c:url value="/cart" />" class="nav-link">
          		<p id="cart_size" style="position: absolute; top: 14px; right: -10px; font-size: 20px;">
          			<c:if test="${ cart.size() > 0 }">${ cart.size() }</c:if>
          		</p>
          	
          		<i class="bi bi-minecart" style="font-size: 22px; position: relative;"></i>
          	</a>
          </li>
        </ul>
      </nav>
    </div>
 </header><!-- End Header -->
