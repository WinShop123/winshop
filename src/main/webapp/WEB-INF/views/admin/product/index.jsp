<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Sản phẩm</title>
<body>
	<div>
    	<h2 class="table-name">Sản phẩm</h2>
        <a href="<c:url value="/admin/createproduct" />" class="create-btn">
        	Thêm mới
        	<i style="margin-left: 8px; font-size: 20px;" class="bi bi-plus-lg"></i>
        </a>
        <table class="table">
			<thead>
				<tr>
              		<th style="width: 20px;" scope="col"></th>
              		<th style="width: 20px;" scope="col"></th>
              		<th scope="col">Id</th>
              		<th style="min-width: 100px" scope="col">Danh mục</th>
              		<th style="min-width: 350px" scope="col">Tên sản phẩm</th>
              		<th style="min-width: 90px" scope="col">Hình ảnh</th>
              		<th style="min-width: 240px" scope="col">Kích thước</th>
              		<th style="min-width: 200px" scope="col">Chất liệu</th>
              		<th style="min-width: 300px" scope="col">Phụ kiện</th>
              		<th style="min-width: 90px" scope="col">Số lượng</th>
              		<th style="min-width: 150px" scope="col">Giá bán</th>
              		<th style="min-width: 350px" scope="col">Mô tả</th>
              		<th style="min-width: 90px" scope="col">Giảm giá</th>
              		<th style="min-width: 90px" scope="col">Mới</th>
              		<th style="min-width: 90px;" scope="col">Bán chạy</th>
              		<th style="min-width: 181px" scope="col">Ngày tạo</th>
            	</tr>
          	</thead>
			<tbody style="vertical-align: middle;">
          	
          	<c:forEach var="item" items="${ pagination }">
          		<tr>
              		<td><a href="<c:url value="/admin/editproduct${ item.id }" />"><i class="bi bi-pencil-square"></i></a></i></td>
              		<td><a href="<c:url value="/admin/deleteproduct${ item.id }" />"><i class="bi bi-x-lg"></i></a></td>
              		<td>${ item.id }</td>
              	<c:forEach var="category" items="${ categories }">
              	<c:if test="${ category.id == item.category_id }">
              		<td>${ category.name }</td>
              	</c:if>	
              	</c:forEach>
              		<td>${ item.name }</td>
              		<td><img class="table-img" src="<c:url value="/assets/img/products/${ item.image }" />" /></td>
              		<td>${ item.dimension }</td>
              		<td>${ item.material }</td>
              		<td>${ item.accessories }</td>
              		<td style="text-align: center">${ item.quantity }</td>
              		<td>
						<fmt:formatNumber type="number" groupingUsed="true" value="${ item.price }" /> ₫
					</td>
              		<td>${ item.description }</td>
              		<td style="text-align: center">${ item.sale_off }%</td>
              		<td style="text-align: center">${ item.is_new }</td>
              		<td style="text-align: center">${ item.is_topsell }</td>
              		<td>${ item.createat }</td>
            	</tr>
          	</c:forEach>
          	</tbody>
        </table>
        
        <c:if test="${ totaldata > 7 }">
        	<div class="pagination">
          		<a href="<c:url value="/admin/productP1" />">&laquo;</a>
          		
          		<c:forEach var="item" begin="1" end="${ paginationinfo.total }" varStatus="itemS">
          			<c:if test="${ itemS.index == paginationinfo.current }">
          				<a href="<c:url value="/admin/productP${ itemS.index }" />" class="active">${ itemS.index }</a>
          			</c:if>
          			<c:if test="${ itemS.index != paginationinfo.current }">
          				<a href="<c:url value="/admin/productP${ itemS.index }" />">${ itemS.index }</a>
          			</c:if>
          			<c:set var="lastindex" value="${ itemS.index }" />
          		</c:forEach>

          		<a href="<c:url value="/admin/productP${ lastindex }" />">&raquo;</a>
        	</div>
        </c:if>
        
        <div>${ images.size() }</div>
      </div>
</body>
</html>