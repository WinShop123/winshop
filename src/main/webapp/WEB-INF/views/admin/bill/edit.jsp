<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Sửa</title>
<body>
	<div>
        <h2 class="table-name">Sửa</h2>
        <form:form action="editbill${ editbill.id }" method="post" modelAttribute="editbill" id="edit-form">
            <div class="form-group row">
              <label for="username" class="col-lg-2 col-form-label">Tên tài khoản</label>
              <div class="col-lg-6">
              	<form:input path="username" type="email" class="form-control" id="username" placeholder="Tên tài khoản" value="${ editbill.username }" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="name" class="col-lg-2 col-form-label">Họ tên</label>
              <div class="col-lg-6">
              	<form:input path="name" type="text" class="form-control" id="name" placeholder="Họ tên" value="${ editbill.name }" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="phone" class="col-lg-2 col-form-label">Số điện thoại</label>
              <div class="col-lg-6">
              	<form:input path="phone" type="text" class="form-control" id="phone" placeholder="Số điện thoại" value="${ editbill.phone }" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="address" class="col-lg-2 col-form-label">Địa chỉ</label>
              <div class="col-lg-6">
              	<form:input path="address" type="text" class="form-control" id="address" placeholder="Địa chỉ" value="${ editbill.address }" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="quantity" class="col-lg-2 col-form-label">Số lượng</label>
              <div class="col-lg-6">
              	<form:input path="quantity" type="text" class="form-control" id="quantity" placeholder="Số lượng" value="${ editbill.quantity }" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="total" class="col-lg-2 col-form-label">Tổng tiền</label>
              <div class="col-lg-6">
              	<form:input path="total" type="text" class="form-control" id="total" placeholder="Tổng tiền" value="${ editbill.total }" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="note" class="col-lg-2 col-form-label">Ghi chú</label>
              <div class="col-lg-6">
              	<form:input path="note" type="text" class="form-control" id="note" placeholder="Ghi chú" value="${ editbill.note }" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="createat" class="col-lg-2 col-form-label">Ngày tạo</label>
              <div class="col-lg-6">
              	<form:input path="createat" type="date" class="form-control" id="note" placeholder="Ghi chú" value="${ editbill.createat }" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row for-btn">
              <div class="col-lg-10">
                <button type="submit" class="btn btn-primary">Sửa</button>
              </div>
            </div>
          </form:form>
      </div>
      
        <content tag="script">
  	<script>
	  	Validator({
	        form: '#edit-form',
	        formGroupSelector: '.form-group',
	        errorSelector: '.e-message',
	        rules: [
	          Validator.isRequired('#username'),
		      Validator.isEmail('#username'),
	          Validator.isRequired('#name'),
	          Validator.isValidName('#name'),
	          Validator.isRequired('#phone'),
	          Validator.isPhoneNumber('#phone'),
	          Validator.isRequired('#address'),
	          Validator.isRequired('#quantity'),
	          Validator.isIntegerNumber('#quantity'),
	          Validator.isRequired('#total'),
	          Validator.isFloatNumber('#total'),
	          Validator.isRequired('#note'),
	          Validator.isRequired('#craeteat')
	        ]
	      })
  	</script>
  </content>
</body>