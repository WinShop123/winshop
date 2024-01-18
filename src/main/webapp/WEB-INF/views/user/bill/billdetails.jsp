<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>Giỏ hàng</title>
<body>
	<main id="main sign-up">

    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <ol>
          <li><a href="<c:url value="/" />">Trang chủ</a></li>
          <li><a href="<c:url value="/bill" />">Đơn hàng của bạn</a></li>
          <li>Chi tiết đơn hàng</li>
        </ol>

      </div>
    </section><!-- End Breadcrumbs -->

	<section>
		<div class="container">
			<div class="row d-flex">
            
            <c:if test="${ not empty billdetails }">
            	<div class="col-lg-12">
					<table class="table">
                  		<thead style="font-size: 18px; text-align: center;">
                    		<th scope="col">Hình ảnh</th>
                        	<th scope="col">Tên sản phẩm</th>
                        	<th scope="col">Số lượng</th>
                        	<th scope="col">Tổng tiền</th>
                    	</thead>
    
                    	<tbody>
            
            			<c:forEach var="item" items="${ billdetails }">
            				<tr class="center">
                        		<td style="width:100px; text-align: center;">
                        		<c:forEach var="product" items="${ products }">
                        			<c:if test="${ product.id == item.product_id }">
                        				<img style="width: 80%;" src="<c:url value="/assets/img/products/${ product.image }" />" />
                        			</c:if>
                        		</c:forEach>
                        		</td>
                        		<td style="text-align: center">
                        			<c:forEach var="product" items="${ products }">
                        			<c:if test="${ product.id == item.product_id }">
                        				${ product.name }
                        			</c:if>
                        		</c:forEach>
                        		</td>
                        		<td style="text-align: center;">
                          			${ item.quantity }
                        		</td>
                        		<td id="price" style="width: 160px; text-align: center;">
                        			<fmt:formatNumber type="number" groupingUsed="true" value="${ item.total }" /> ₫
                        		</td>
                      		</tr>
           				</c:forEach>
            
						</tbody>
					</table>
				</div>
				
            </c:if>
    
            </div>
        </div>
    </section>

  </main>
</body>