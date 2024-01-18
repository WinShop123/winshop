<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Sửa</title>
<body>
	<div>
        <h2 class="table-name">Sửa</h2>
        <form:form action="editrole${ editrole.id }" method="post" modelAttribute="editrole" id="edit-form">
            <div class="form-group row">
              <label for="name" class="col-lg-2 col-form-label">Tên tài khoản</label>
              <div class="col-lg-6">
              	<form:select path="account_id" id="name">
              		<option value="">----Tài khoản----</option>
              		<c:forEach var="item" items="${ accounts }">
              			<c:if test="${ item.id == editrole.account_id }">
              				<form:option value="${ item.id }" label="${ item.name }" selected="selected" />
              			</c:if>
              			<c:if test="${ item.id != editrole.account_id }">
              				<form:option value="${ item.id }" label="${ item.name }" />
              			</c:if>
              		</c:forEach>
              	</form:select>
              	<div class="e-message"></div>
              </div>
            </div>
            <div class="form-group row">
              <label for="role" class="col-lg-2 col-form-label">Quyền</label>
              <div class="col-lg-6">
              	<form:select path="role" id="role">
              		<option value="">----Quyền----</option>
              		<c:if test="${ editrole.role == 'admin' }">
              			<option value="user">user</option>
              			<option value="admin" selected>admin</option>
              		</c:if>
              		<c:if test="${ editrole.role == 'user' }">
              			<option value="user" selected>user</option>
              			<option value="admin">admin</option>
              		</c:if>
              	</form:select>
              	<div class="e-message"></div>
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
	          Validator.isRequired('#role')
	        ]
	      })
  	</script>
  </content>
</body>