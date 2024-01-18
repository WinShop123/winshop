<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Thêm mới</title>
<body>
	<div>
        <h2 class="table-name">Thêm mới</h2>
        <form:form action="createbill" method="post" modelAttribute="newbill" id="create-form">
            <div class="form-group row">
              <label for="username" class="col-lg-2 col-form-label">Tên tài khoản</label>
              <div class="col-lg-6">
              	<form:input path="username" type="text" class="form-control" id="username" placeholder="Tên tài khoản" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="name" class="col-lg-2 col-form-label">Họ tên</label>
              <div class="col-lg-6">
              	<form:input path="name" type="text" class="form-control" id="name" placeholder="Họ tên" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="phone" class="col-lg-2 col-form-label">Số điện thoại</label>
              <div class="col-lg-6">
              	<form:input path="phone" type="text" class="form-control" id="phone" placeholder="Số điện thoại" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="address" class="col-lg-2 col-form-label">Địa chỉ</label>
              <div class="col-lg-6">
              	<form:input path="address" type="text" class="form-control" id="address" placeholder="Địa chỉ" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="quantity" class="col-lg-2 col-form-label">Số lượng</label>
              <div class="col-lg-6">
              	<form:input path="quantity" type="text" class="form-control" id="quantity" placeholder="Số lượng" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="total" class="col-lg-2 col-form-label">Tổng tiền</label>
              <div class="col-lg-6">
              	<form:input path="total" type="text" class="form-control" id="total" placeholder="Tổng tiền" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="note" class="col-lg-2 col-form-label">Ghi chú</label>
              <div class="col-lg-6">
              	<form:input path="note" type="text" class="form-control" id="note" placeholder="Ghi chú" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row for-btn">
              <div class="col-lg-10">
                <button type="submit" class="btn btn-primary">Thêm mới</button>
              </div>
            </div>
          </form:form>
      </div>   
      
      <content tag="script">
  	<script>
	  	Validator({
	        form: '#create-form',
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
	          Validator.isRequired('#note')
	        ]
	      })
  	</script>
  </content>
</body>