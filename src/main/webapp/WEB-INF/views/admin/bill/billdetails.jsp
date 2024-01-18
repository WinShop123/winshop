<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Chi tiết hóa đơn</title>
<body>
	<div>
    	<h2 class="table-name">Chi tiết hóa đơn</h2>
        <table class="table">
			<thead>
				<tr>
              		<th scope="col">Id</th>
              		<th scope="col">Hình ảnh</th>
              		<th scope="col">Tên sản phẩm</th>
              		<th scope="col">Số lượng</th>
              		<th scope="col">Tổng tiền</th>
            	</tr>
          	</thead>
			<tbody style="vertical-align: middle;">
          	
          	<c:forEach var="item" items="${ billdetails }">
          		<tr>
              		<td>${ item.id }</td>
              		<td>
              		<c:forEach var="product" items="${ products }">
              			<c:if test="${ product.id == item.product_id }">
              				<img class="table-img" src="<c:url value="/assets/img/products/${ product.image }" />" />
              			</c:if>
              		</c:forEach>
              		</td>
              		<td>
              		<c:forEach var="product" items="${ products }">
              			<c:if test="${ product.id == item.product_id }">
              				${ product.name }
              			</c:if>
              		</c:forEach>
              		</td>
              		<td>${ item.quantity }</td>
              		<td>
              			<fmt:formatNumber type="number" groupingUsed="true" value="${ item.total }" /> ₫
              		</td>
            	</tr>
          	</c:forEach>
          	</tbody>
        </table>
        
      </div>
</body>