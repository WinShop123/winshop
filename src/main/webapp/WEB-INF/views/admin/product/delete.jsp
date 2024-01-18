<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>Xóa</title>
<body>
	<div>
        <h2 class="table-name">Xoá</h2>
        <form:form action="deleteproduct${ deleteproduct.id }" method="post" modelAttribute="deleteproduct">
        	<div class="form-group row">
              <label for="icon" class="col-lg-2 col-form-label">Id</label>
              <label class="col-lg-6 col-form-label">${ deleteproduct.id }</label>
            </div>
            <div class="form-group row">
              <label for="name" class="col-lg-2 col-form-label">Danh mục</label>
              <label class="col-lg-6 col-form-label">
              <c:forEach var="item" items="${ categories }">
              <c:if test="${ item.id == deleteproduct.category_id }">
              	${ item.name }
              </c:if>
              </c:forEach>
              </label>
            </div>
          	<div class="form-group row">
              <label for="icon" class="col-lg-2 col-form-label">Tên sản phẩm</label>
              <label class="col-lg-6 col-form-label">${ deleteproduct.name }</label>
            </div>
            <div class="form-group row">
              <label for="icon" class="col-lg-2 col-form-label">Hình ảnh</label>
              <div class="col-lg-6">
              	<div><img id="image" class="img-fluid" src="<c:url value="/assets/img/products/${ deleteproduct.image }" />" alt="Image" /></div>
              	<label>${ deleteproduct.image }</label>
              </div>
            </div>
            <div class="form-group row">
              <label for="icon" class="col-lg-2 col-form-label">Kích thước</label>
              <label class="col-lg-6 col-form-label">${ deleteproduct.dimension }</label>
            </div>
            <div class="form-group row">
              <label for="icon" class="col-lg-2 col-form-label">Chất liệu</label>
              <label class="col-lg-6 col-form-label">${ deleteproduct.material }</label>
            </div>
            <div class="form-group row">
              <label for="icon" class="col-lg-2 col-form-label">Phụ kiện</label>
              <label class="col-lg-6 col-form-label">${ deleteproduct.accessories }</label>
            </div>
            <div class="form-group row">
              <label for="icon" class="col-lg-2 col-form-label">Số lượng</label>
              <label class="col-lg-6 col-form-label">${ deleteproduct.quantity }</label>
            </div>
            <div class="form-group row">
              <label for="icon" class="col-lg-2 col-form-label">Giá bán</label>
              <label class="col-lg-6 col-form-label">
				<fmt:formatNumber type="number" groupingUsed="true" value="${ deleteproduct.price }" /> đ
				</label>
            </div>
            <div class="form-group row">
              <label for="icon" class="col-lg-2 col-form-label">Mô tả</label>
              <label class="col-lg-6 col-form-label">${ deleteproduct.description }</label>
            </div>
            <div class="form-group row">
              <label for="icon" class="col-lg-2 col-form-label">Giảm giá</label>
              <label class="col-lg-6 col-form-label">${ deleteproduct.sale_off }</label>
            </div>
            <div class="form-group row">
              <label for="icon" class="col-lg-2 col-form-label">Mới</label>
              <label class="col-lg-6 col-form-label">${ deleteproduct.is_new }</label>
            </div>
            <div class="form-group row">
              <label for="icon" class="col-lg-2 col-form-label">Bán chạy</label>
              <label class="col-lg-6 col-form-label">${ deleteproduct.is_topsell }</label>
            </div>
            <div class="form-group row">
              <label for="icon" class="col-lg-2 col-form-label">Ngày tạo</label>
              <label class="col-lg-6 col-form-label">${ deleteproduct.createat }</label>
            </div>
            <div class="form-group row">
              <div class="col-lg-1"></div>
              <label class="col-lg-8">Bạn có muốn xóa ?</label>
            </div>
            <div class="form-group row" style="text-align: center;">
              <div class="col-lg-1"></div>
              <div class="col-lg-2"><a class="cancel-btn" href="<c:url value="/admin/product" />">Huỷ</a></div>
              <div class="col-lg-1"></div>
              <div class="col-lg-2"><button type="submit" class="delete-btn">Xóa</button></div>
            </div>
          </form:form>
      </div>
</body>