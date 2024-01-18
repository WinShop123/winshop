<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>Xóa</title>
<body>
	<div>
        <h2 class="table-name">Xoá</h2>
        <form:form action="deleteslide${ deleteslide.id }" method="post" modelAttribute="deleteslide">
            <div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Id</label>
              <label class="col-lg-6 col-form-label">${ deleteslide.id }</label>
            </div>
            <div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Tiêu đề</label>
              <label class="col-lg-6 col-form-label">${ deleteslide.title }</label>
            </div>
            <div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Nội dung</label>
              <label class="col-lg-6 col-form-label">${ deleteslide.content }</label>
            </div>
            <div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Click link</label>
              <label class="col-lg-6 col-form-label">${ deleteslide.click }</label>
            </div>
            <div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Hình ảnh</label>
              <div class="col-lg-6">
              	<div><img id="image" class="img-fluid" src="<c:url value="/assets/img/slides/${ deleteslide.image }" />" alt="Image" /></div>
              	<label>${ deleteslide.image }</label>
              </div>
            </div>
            <div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Video</label>
              <label class="col-lg-6 col-form-label">${ deleteslide.video }</label>
            </div>
            <div class="form-group row">
              <div class="col-lg-1"></div>
              <label class="col-lg-8">Bạn có muốn xóa ?</label>
            </div>
            <div class="form-group row" style="text-align: center;">
              <div class="col-lg-1"></div>
              <div class="col-lg-2"><a class="cancel-btn" href="<c:url value="/admin/slide" />">Huỷ</a></div>
              <div class="col-lg-1"></div>
              <div class="col-lg-2"><button type="submit" class="delete-btn">Xóa</button></div>
            </div>
          </form:form>
      </div>
</body>