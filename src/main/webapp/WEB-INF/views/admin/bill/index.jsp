<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Hóa đơn</title>
<body>
	<div>
    	<h2 class="table-name">Hóa đơn</h2>
        <%-- <a href="<c:url value="/admin/createbill" />" class="create-btn">
        	Thêm mới
        	<i style="margin-left: 8px; font-size: 20px;" class="bi bi-plus-lg"></i>
        </a> --%>
        <table class="table">
			<thead>
				<tr>
              		<th style="min-width: 118px" scope="col"></th>
              		<th style="width: 20px;" scope="col"></th>
              		<th style="min-width: 70px;" scope="col"></th>
              		<th style="min-width: 114px" scope="col">Trạng thái</th>
              		<th style="min-width: 50px" scope="col">Id</th>
              		<th style="min-width: 150px" scope="col">Họ tên</th>
              		<th style="min-width: 150px" scope="col">Email</th>
              		<th style="min-width: 130px" scope="col">Số điện thoại</th>
              		<th style="min-width: 100px" scope="col">Địa chỉ</th>
              		<th style="min-width: 100px" scope="col">Số lượng</th>
              		<th style="min-width: 130px" scope="col">Tổng tiền</th>
              		<th style="min-width: 100px" scope="col">Ghi chú</th>
              		<th style="min-width: 100px" scope="col">Ngày tạo</th>
            	</tr>
          	</thead>
			<tbody style="vertical-align: middle;">
          	
          	<c:forEach var="item" items="${ pagination }">
          		<tr>
              		<td style="text-align: center">
              			<c:if test="${ item.status }"><span style="color: green"><i style="font-size: 22px" class="bi bi-check-lg"></i></span></c:if>
                        <c:if test="${ !item.status }"><a href="<c:url value="/admin/confirm${ item.id }" />">Xác nhận đơn</a></c:if>
              		</td>
              		<td><a href="<c:url value="/admin/deletebill${ item.id }" />"><i class="bi bi-x-lg"></i></a></td>
              		<td><a href="<c:url value="/admin/billdetails${ item.id }" />">Chi tiết</a></td>
              		<td>
              			<c:if test="${ item.status }"><span style="color: green">Thành công</span></c:if>
                        <c:if test="${ !item.status }"><span style="color: red">Đang chuyển</span></c:if>
              		</td>
              		<td>${ item.id }</td>
              		<td>
              		<c:forEach var="account" items="${ accounts }">
              			<c:if test="${ account.username == item.username }">
              				${ account.name }
              			</c:if>
              		</c:forEach>
              		</td>
              		<td>${ item.username }</td>
              		<td>${ item.phone }</td>
              		<td>${ item.address }</td>
              		<td>${ item.quantity }</td>
              		<td>
              			<fmt:formatNumber type="number" groupingUsed="true" value="${ item.total }" /> ₫
              		</td>
              		<td>${ item.note }</td>
              		<td>${ item.createat }</td>
            	</tr>
          	</c:forEach>
          	</tbody>
        </table>
        
        <c:if test="${ totaldata > 7 }">
        	<div class="pagination">
          		<a href="<c:url value="/admin/billP1" />">&laquo;</a>
          		
          		<c:forEach var="item" begin="1" end="${ paginationinfo.total }" varStatus="itemS">
          			<c:if test="${ itemS.index == paginationinfo.current }">
          				<a href="<c:url value="/admin/billP${ itemS.index }" />" class="active">${ itemS.index }</a>
          			</c:if>
          			<c:if test="${ itemS.index != paginationinfo.current }">
          				<a href="<c:url value="/admin/billP${ itemS.index }" />">${ itemS.index }</a>
          			</c:if>
          			<c:set var="lastindex" value="${ itemS.index }" />
          		</c:forEach>

          		<a href="<c:url value="/admin/billP${ lastindex }" />">&raquo;</a>
        	</div>
        </c:if>
        
      </div>
</body>