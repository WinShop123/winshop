<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>Xóa</title>
<body>
	<div>
        <h2 class="table-name">Xoá</h2>
        <form:form action="deletebill${ deletebill.id }" method="post" modelAttribute="deletebill">
        	<div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Id</label>
              <label class="col-lg-6 col-form-label">${ deletebill.id }</label>
            </div>
            <div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Tài khoản</label>
              <label class="col-lg-6 col-form-label">${ deletebill.username }</label>
            </div>
            <div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Họ tên</label>
              <label class="col-lg-6 col-form-label">${ deletebill.name }</label>
            </div>
            <div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Số điện thoại</label>
              <label class="col-lg-6 col-form-label">${ deletebill.phone }</label>
            </div>
            <div class="form-group row">
            <div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Địa chỉ</label>
              <label class="col-lg-6 col-form-label">${ deletebill.address }</label>
            </div>
            <div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Số lượng</label>
              <label class="col-lg-6 col-form-label">${ deletebill.quantity }</label>
            </div>
            <div class="form-group row">
            <div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Tổng tiền</label>
              <label class="col-lg-6 col-form-label">
              	<fmt:formatNumber type="number" groupingUsed="true" value="${ deletebill.total }" /> ₫
              </label>
            </div>
            <div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Ghi chú</label>
              <label class="col-lg-6 col-form-label">${ deletebill.note }</label>
            </div>
            <div class="form-group row">
              <div class="col-lg-1"></div>
              <label class="col-lg-8">Bạn có muốn xóa ?</label>
            </div>
            <div class="form-group row" style="text-align: center;">
              <div class="col-lg-1"></div>
              <div class="col-lg-2"><a class="cancel-btn" href="<c:url value="/admin/bill" />">Huỷ</a></div>
              <div class="col-lg-1"></div>
              <div class="col-lg-2"><button type="submit" class="delete-btn">Xóa</button></div>
            </div>
          </form:form>
      </div>
</body>