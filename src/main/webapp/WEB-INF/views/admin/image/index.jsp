<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Hình ảnh</title>
<body>
	<div>
    	<h2 class="table-name">Hình ảnh</h2>
        <a href="<c:url value="/admin/createimage" />" class="create-btn">
        	Thêm mới
        	<i style="margin-left: 8px; font-size: 20px;" class="bi bi-plus-lg"></i>
        </a>
        <table class="table">
			<thead>
				<tr>
              		<th style="width: 20px;" scope="col"></th>
              		<th style="width: 20px;" scope="col"></th>
              		<th scope="col">Id</th>
              		<th scope="col">Sản phẩm</th>
              		<th scope="col">Hình ảnh</th>
              		<th scope="col">Ảnh slide</th>
            	</tr>
          	</thead>
			<tbody style="vertical-align: middle;">
          	
          	<c:forEach var="item" items="${ pagination }">
          		<tr>
              		<td><a href="<c:url value="/admin/editimage${ item.id }" />"><i class="bi bi-pencil-square"></i></a></i></td>
              		<td><a href="<c:url value="/admin/deleteimage${ item.id }" />"><i class="bi bi-x-lg"></i></a></td>
              		<td>${ item.id }</td>
              		<td>
              		<c:forEach var="product" items="${ products }">
              			<c:if test="${ product.id == item.product_id }">
              				${ product.name }
              			</c:if>
              		</c:forEach>
              		</td>
              		<td><img class="table-img" src="<c:url value="/assets/img/products/${ item.image }" />" /></td>
              		<td>${ item.is_slideimage }</td>
            	</tr>
          	</c:forEach>
          	</tbody>
        </table>
        
        <c:if test="${ totaldata > 7 }">
        	<div class="pagination">
          		<a href="<c:url value="/admin/imageP1" />">&laquo;</a>
          		
          		<c:forEach var="item" begin="1" end="${ paginationinfo.total }" varStatus="itemS">
          			<c:if test="${ itemS.index == paginationinfo.current }">
          				<a href="<c:url value="/admin/imageP${ itemS.index }" />" class="active">${ itemS.index }</a>
          			</c:if>
          			<c:if test="${ itemS.index != paginationinfo.current }">
          				<a href="<c:url value="/admin/imageP${ itemS.index }" />">${ itemS.index }</a>
          			</c:if>
          			<c:set var="lastindex" value="${ itemS.index }" />
          		</c:forEach>

          		<a href="<c:url value="/admin/imageP${ lastindex }" />">&raquo;</a>
        	</div>
        </c:if>
        
      </div>
</body>