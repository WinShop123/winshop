<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Slide</title>
<body>
	<div>
    	<h2 class="table-name">Slide</h2>
        <a href="<c:url value="/admin/createslide" />" class="create-btn">
        	Thêm mới
        	<i style="margin-left: 8px; font-size: 20px;" class="bi bi-plus-lg"></i>
        </a>
        <table class="table">
			<thead>
				<tr>
              		<th style="width: 20px;" scope="col"></th>
              		<th style="width: 20px;" scope="col"></th>
              		<th scope="col">Id</th>
              		<th scope="col">Tiêu đề</th>
              		<th scope="col">Nội dung</th>
              		<th scope="col">Click link</th>
              		<th scope="col">Hình ảnh</th>
              		<th scope="col">Video</th>
            	</tr>
          	</thead>
			<tbody style="vertical-align: middle;">
          	
          	<c:forEach var="item" items="${ pagination }">
          		<tr>
              		<td><a href="<c:url value="/admin/editslide${ item.id }" />"><i class="bi bi-pencil-square"></i></a></i></td>
              		<td><a href="<c:url value="/admin/deleteslide${ item.id }" />"><i class="bi bi-x-lg"></i></a></td>
              		<td>${ item.id }</td>
              		<td>${ item.title }</td>
              		<td>${ item.content }</td>
              		<td>${ item.click }</td>
              		<td><img class="table-img" src="<c:url value="/assets/img/slides/${ item.image }" />" /></td>
              		<td>${ item.video }</td>
            	</tr>
          	</c:forEach>
          	</tbody>
        </table>
        
        <c:if test="${ totaldata > 7 }">
        	<div class="pagination">
          		<a href="<c:url value="/admin/slideP1" />">&laquo;</a>
          		
          		<c:forEach var="item" begin="1" end="${ paginationinfo.total }" varStatus="itemS">
          			<c:if test="${ itemS.index == paginationinfo.current }">
          				<a href="<c:url value="/admin/slideP${ itemS.index }" />" class="active">${ itemS.index }</a>
          			</c:if>
          			<c:if test="${ itemS.index != paginationinfo.current }">
          				<a href="<c:url value="/admin/slideP${ itemS.index }" />">${ itemS.index }</a>
          			</c:if>
          			<c:set var="lastindex" value="${ itemS.index }" />
          		</c:forEach>

          		<a href="<c:url value="/admin/slideP${ lastindex }" />">&raquo;</a>
        	</div>
        </c:if>
        
      </div>
</body>