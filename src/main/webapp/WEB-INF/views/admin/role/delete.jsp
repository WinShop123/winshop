<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>Xóa</title>
<body>
	<div>
        <h2 class="table-name">Xoá</h2>
        <form:form action="deleterole${ deleterole.id }" method="post" modelAttribute="deleterole">
        	<div class="form-group row">
              <label for="name" class="col-lg-2 col-form-label">Id</label>
              <label class="col-lg-6 col-form-label">${ deleterole.id }</label>
            </div>
            <div class="form-group row">
              <label for="name" class="col-lg-2 col-form-label">Tên tài khoản</label>
              <label class="col-lg-6 col-form-label">
              	<c:forEach var="item" items="${ accounts }">
              		<c:if test="${ item.id == deleterole.account_id }">
              			${ item.name }
              		</c:if>
              	</c:forEach>
              </label>
            </div>
            <div class="form-group row">
              <label for="logo" class="col-lg-2 col-form-label">Quyền</label>
              <label class="col-lg-6 col-form-label">${ deleterole.role }</label>
            </div>
            <div class="form-group row">
              <div class="col-lg-1"></div>
              <label class="col-lg-8">Bạn có muốn xóa ?</label>
            </div>
            <div class="form-group row" style="text-align: center;">
              <div class="col-lg-1"></div>
              <div class="col-lg-2"><a class="cancel-btn" href="<c:url value="/admin/role" />">Huỷ</a></div>
              <div class="col-lg-1"></div>
              <div class="col-lg-2"><button type="submit" class="delete-btn">Xóa</button></div>
            </div>
          </form:form>
      </div>
</body>