<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>Error</title>
<body>
	<div class="forbidden" style="padding-top: 100px;">
        <h1>403 Forbidden</h1>
        <h3>Bạn không có quyền truy cập vào trang này!</h3>
        <h3 style="margin-top: 100px;">Quay lại trang chủ</h3>
        <h1 class="logo"><a class="in-signup" href="<c:url value="/" />">HiroShop</a></h1>
    </div>
</body>