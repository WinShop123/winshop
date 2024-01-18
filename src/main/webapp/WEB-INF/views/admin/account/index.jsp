<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Tài khoản</title>
<body>
	<div>
    	<h2 class="table-name">Tài khoản</h2>
        <a href="<c:url value="/admin/createaccount" />" class="create-btn">
        	Thêm mới
        	<i style="margin-left: 8px; font-size: 20px;" class="bi bi-plus-lg"></i>
        </a>
        <table class="table">
			<thead>
				<tr>
              		<th style="width: 20px;" scope="col"></th>
              		<th style="width: 20px;" scope="col"></th>
              		<th scope="col">Id</th>
              		<th scope="col">Họ tên</th>
              		<th scope="col">Tên tài khoản</th>
              		<th scope="col">Mật khẩu</th>
              		<th style="min-width: 120px" scope="col">Quyền</th>
            	</tr>
          	</thead>
			<tbody style="vertical-align: middle;">
          	
          	<c:forEach var="item" items="${ pagination }">
          		<tr>
              		<td><a href="<c:url value="/admin/editaccount${ item.id }" />"><i class="bi bi-pencil-square"></i></a></i></td>
              		<td><a href="<c:url value="/admin/deleteaccount${ item.id }" />"><i class="bi bi-x-lg"></i></a></td>
              		<td>${ item.id }</td>
              		<td>${ item.name }</td>
              		<td>${ item.username }</td>
              		<td>${ item.password }</td>
              		<td>
              		<c:forEach var="role" items="${ roles }">
              			<c:if test="${ role.account_id == item.id }">
              				${ role.role }
              			</c:if>
              		</c:forEach>
              		</td>
            	</tr>
          	</c:forEach>
          	</tbody>
        </table>
        
        <c:if test="${ totaldata > 7 }">
        	<div class="pagination">
          		<a href="<c:url value="/admin/accountP1" />">&laquo;</a>
          		
          		<c:forEach var="item" begin="1" end="${ paginationinfo.total }" varStatus="itemS">
          			<c:if test="${ itemS.index == paginationinfo.current }">
          				<a href="<c:url value="/admin/accountP${ itemS.index }" />" class="active">${ itemS.index }</a>
          			</c:if>
          			<c:if test="${ itemS.index != paginationinfo.current }">
          				<a href="<c:url value="/admin/accountP${ itemS.index }" />">${ itemS.index }</a>
          			</c:if>
          			<c:set var="lastindex" value="${ itemS.index }" />
          		</c:forEach>

          		<a href="<c:url value="/admin/accountP${ lastindex }" />">&raquo;</a>
        	</div>
        </c:if>
        
      </div>
</body>