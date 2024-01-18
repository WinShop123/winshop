<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Sửa</title>
<body>
	<div>
        <h2 class="table-name">Sửa</h2>
        <form:form action="editaccount${ editaccount.id }" method="post" modelAttribute="editaccount" id="edit-form">
            <div class="form-group row">
              <label for="name" class="col-lg-2 col-form-label">Họ tên</label>
              <div class="col-lg-6">
              	<form:input path="name" type="text" class="form-control" id="name" placeholder="Họ tên" value="${ editaccount.name }" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="username" class="col-lg-2 col-form-label">Tên tài khoản</label>
              <div class="col-lg-6">
              	<form:input path="username" type="text" class="form-control" id="username" placeholder="Tên tài khoản" value="${ editaccount.username }" />
              </div>
              <div class="e-message"></div>
            </div>
          	<div class="form-group row">
              <label for="password" class="col-lg-2 col-form-label">Mật khẩu</label>
              <div class="col-lg-6">
              	<form:input path="password" type="password" class="form-control" id="password" placeholder="Mật khẩu" value="${ editaccount.password }" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="veole" class="col-lg-2 col-form-label">Quyền</label>
              <div class="col-lg-6">
              	<select id="role" name="role">
              	<c:if test="${ role == 'user' }">
              		<option value="user" selected>user</option>
              		<option value="admin, user">admin, user</option>
              	</c:if>
              	<c:if test="${ role == 'admin, user' }">
              		<option value="user">user</option>
              		<option value="admin, user" selected>admin, user</option>
              	</c:if>
              	</select>
              </div>
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