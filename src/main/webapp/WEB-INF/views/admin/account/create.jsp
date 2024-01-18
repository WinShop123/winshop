<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Thêm mới</title>
<body>
	<div>
        <h2 class="table-name">Thêm mới</h2>
        <form:form action="createaccount" method="post" modelAttribute="newaccount" id="create-form">
            <div class="form-group row">
              <label for="name" class="col-lg-2 col-form-label">Họ tên</label>
              <div class="col-lg-6">
              	<form:input path="name" type="text" class="form-control" id="name" placeholder="Họ tên" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="username" class="col-lg-2 col-form-label">Tên tài khoản</label>
              <div class="col-lg-6">
              	<form:input path="username" type="text" class="form-control" id="username" placeholder="Tên tài khoản" />
              </div>
              <div class="e-message"></div>
            </div>
          	<div class="form-group row">
              <label for="password" class="col-lg-2 col-form-label">Mật khẩu</label>
              <div class="col-lg-6">
              	<form:input path="password" type="password" class="form-control" id="password" placeholder="Mật khẩu" />
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
		          Validator.isRequired('#name'),
		          Validator.isValidName('#name'),
		          Validator.isRequired('#username'),
		          Validator.isEmail('#username'),
		          Validator.isRequired('#password'),
		          Validator.minLength('#password', 6)
		        ]
		      })
		</script>
	</content>
</body>