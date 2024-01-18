<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Thêm mới</title>
<body>
	<div>
        <h2 class="table-name">Thêm mới</h2>
        <form:form action="createrole" method="post" modelAttribute="newrole" id="create-form">
            <div class="form-group row">
              <label for="name" class="col-lg-2 col-form-label">Tên tài khoản</label>
              <div class="col-lg-6">
              	<form:select path="account_id" id="name">
              		<option value="" selected>----Tài khoản----</option>
              		<c:forEach var="item" items="${ accounts }">
              			<form:option value="${ item.id }" label="${ item.name }" />
              		</c:forEach>
              	</form:select>
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="role" class="col-lg-2 col-form-label">Quyền</label>
              <div class="col-lg-6">
              	<form:select path="role" id="role">
              		<option value="" selected>----Quyền----</option>
              		<option value="user">user</option>
              		<option value="admin">admin</option>
              	</form:select>
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
	          Validator.isRequired('#role')
	        ]
	      })
  	</script>
  </content>
</body>