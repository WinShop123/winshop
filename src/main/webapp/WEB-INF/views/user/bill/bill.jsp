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
          <li>Đơn hàng của bạn</li>
        </ol>

      </div>
    </section><!-- End Breadcrumbs -->

	<section>
		<div class="container">
			<div class="row d-flex">
            
            <c:if test="${ empty bills }">
            	<div class="col-lg-12 text-center">
            		<div>Đơn hàng trống</div>
                </div>
            </c:if>
            
            <c:if test="${ not empty bills }">
            	<div class="col-lg-12">
					<table class="table">
                  		<thead style="font-size: 18px; text-align: center;">
                    		<th scope="col">Id</th>
                        	<th scope="col">Email</th>
                        	<th scope="col">Số điện thoại</th>
                        	<th scope="col">Đia chỉ</th>
                        	<th scope="col">Số lượng</th>
                        	<th scope="col">Thành tiền</th>
                        	<th scope="col">Ngày tạo</th>
                        	<th scope="col">Trạng thái</th>
                        	<th scope="col"></th>
                        	<th scope="col"></th>
                    	</thead>
    
                    	<tbody>
            
            			<c:forEach var="item" items="${ bills }">
            				<tr class="center" style="font-size: 14px">
                        		<td style="width:60px; text-align: center;">
                          			${ item.id }
                        		</td>
                        		<td style="width: 250px; text-align:center">${ item.username }</td>
                        		<td style="text-align: center;">
                          			${ item.phone }
                        		</td>
                        		<td style="width: 160px; text-align: center">
                        			${ item.address }
                        		</td>
                        		<td style="width: 100px; text-align: center;">
                        			${ item.quantity }
                        		</td>
                        		<td id="price" style="width: 160px; text-align: center;">
                        			<fmt:formatNumber type="number" groupingUsed="true" value="${ item.total }" /> ₫
                        		</td>
                        		<td style="text-align: center; width: 114px">${ item.createat }</td>
                        		<td style="text-align: center; width: 114px">
                        			<c:if test="${ item.status }"><span style="color: green">Đã xác nhận</span></c:if>
                        			<c:if test="${ !item.status }"><span style="color: red">Đang chuyển</span></c:if>
                        		</td>
                        		<td style="text-align: center; width: 70px">
                          			<a href="<c:url value="/billdetails/${ item.id }" />">Chi tiết</a>
                        		</td>
                        		<td style="text-align: center;">
                        			<c:if test="${ !item.status }"><a href="<c:url value="/deletebill${ item.id }" />">Hủy</a></c:if>
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