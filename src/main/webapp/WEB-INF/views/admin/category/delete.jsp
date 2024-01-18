<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>Xóa</title>
<body>
	<div>
        <h2 class="table-name">Xoá</h2>
        <form:form action="deletecategory${ deletecategory.id }" method="post" modelAttribute="deletecategory">
        	<div class="form-group row">
              <label for="name" class="col-lg-2 col-form-label">Id</label>
              <label class="col-lg-6 col-form-label">${ deletecategory.id }</label>
            </div>
            <div class="form-group row">
              <label for="name" class="col-lg-2 col-form-label">Tên danh mục</label>
              <label class="col-lg-6 col-form-label">${ deletecategory.name }</label>
            </div>
            <div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Logo</label>
              <div class="col-lg-6">
              	<div><img id="image" class="img-fluid" src="<c:url value="/assets/img/categories/${ deletecategory.logo }" />" alt="Image" /></div>
              	<label>${ deletecategory.logo }</label>
              </div>
            </div>
          	<div class="form-group row">
              <label for="icon" class="col-lg-2 col-form-label">Icon</label>
              <div class="col-lg-6">
              	<div><img id="image" class="img-fluid" src="<c:url value="/assets/img/categories/${ deletecategory.icon }" />" alt="Image" /></div>
              	<label>${ deletecategory.icon }</label>
              </div>
            </div>
            <div class="form-group row">
              <div class="col-lg-1"></div>
              <label class="col-lg-8">Bạn có muốn xóa ?</label>
            </div>
            <div class="form-group row" style="text-align: center;">
              <div class="col-lg-1"></div>
              <div class="col-lg-2"><a class="cancel-btn" href="<c:url value="/admin/category" />">Huỷ</a></div>
              <div class="col-lg-1"></div>
              <div class="col-lg-2"><button type="submit" class="delete-btn">Xóa</button></div>
            </div>
          </form:form>
      </div>
</body>