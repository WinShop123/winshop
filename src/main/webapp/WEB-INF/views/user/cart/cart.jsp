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
          <li>Giỏ hàng</li>
        </ol>

      </div>
    </section><!-- End Breadcrumbs -->

	<section>
		<div class="container">
			<div class="row d-flex">
            
            <c:if test="${ empty cart }">
            	<div class="col-lg-12 text-center">
            		<div>Giỏ hàng trống</div>
                </div>
            </c:if>
            
            <c:if test="${ not empty cart }">
            	<div class="col-lg-9 has-y-scrollbar" style="max-height: 470px; overflow-y: auto;">
					<table class="table">
                  		<thead style="font-size: 18px; text-align: center;">
                    		<th scope="col">Hình ảnh</th>
                        	<th scope="col">Tên sản phẩm</th>
                        	<th scope="col">Số lượng</th>
                        	<th scope="col">Giá bán</th>
                        	<th scope="col">Giảm giá</th>
                        	<th scope="col">Thành tiền</th>
                        	<th scope="col"></th>
                    	</thead>
    
                    	<tbody>
            
            			<c:forEach var="item" items="${ cart }">
            				<tr class="center">
                        		<td style="width:100px; text-align: center;">
                          			<img style="width: 80%;" src="<c:url value="/assets/img/products/${ item.value.product.image }" />" />
                        		</td>
                        		<td style="width: 250px;">${ item.value.product.name }</td>
                        		<td style="text-align: center;">
                          			<input class="edit-btn" data-id="${ item.key }" id="item-${ item.key }" style="width: 70px;" type="number" min="1" max="${ item.value.product.quantity }" value="${ item.value.quantity }" />
                          			<input class="pquantity" type="hidden" value="${ item.value.product.quantity }" />
                          			<div class="e-message"></div>
                        		</td>
                        		<td style="width: 160px; text-align: end;">
                        			<fmt:formatNumber type="number" groupingUsed="true" value="${ item.value.product.price }" /> ₫
                        		</td>
                        		<td style="width: 160px; text-align: center;">
                        			${ item.value.product.sale_off } %
                        		</td>
                        		<td id="price" style="width: 160px; text-align: end;">
                        			<fmt:formatNumber type="number" groupingUsed="true" value="${ item.value.totalPrice }" /> ₫
                        		</td>
                        		<td style="text-align: center;">
                          			<a href="<c:url value="/delete-item/${ item.key }" />"><i style="font-size: 30px; color: rgb(10 32 144 / 90%);" class="bi bi-x"></i></a>
                        		</td>
                      		</tr>
           				</c:forEach>
            
						</tbody>
					</table>
				</div>
				
				<div class="col-lg-3">
                    <div class="pay-bill">
                      <label id="total_price">
                      	<fmt:formatNumber type="number" groupingUsed="true" value="${ totalPrice }" /> ₫
                      </label>
                      <label id="total_quantity">${ totalQuantity } sản phẩm </label>
                      <c:if test="${ empty loginsession }">
                      	<a href="<c:url value="/login" />" class="pay-btn">Đặt hàng</a>
                      </c:if>
                      <c:if test="${ not empty loginsession }">
                      	<a href="<c:url value="/checkout" />" class="pay-btn">Đặt hàng</a>
                      </c:if>
                    </div>
                </div>
            </c:if>
    
            </div>
        </div>
    </section>

  </main>
  
  <c:if test="${ success }">
  	<div class="mess-container d-flex">
    <div class="mess-box">
      <label class="mess-success">Đặt hàng thành công !</label>
      <a href="<c:url value="/bill" />" class="login-link">Đơn hàng của bạn<a>
    </div>
  </div>
  </c:if>
  
	<content tag="script">
		<script type="text/javascript">
			$('.edit-btn').bind('keyup mouseup', function() {
				var element = $(this).parent().children('.e-message')
				var id = $(this).data("id")
				var pquantity = $(this).parent().children('.pquantity').val()
				var quantity = $('#item-' + id).val()
				$.ajax({
					type: 'get',
	  				url: '${ pageContext.request.contextPath }/edit-item',
	  				cache: false,
	  				data: "id="+ id + "&quantity=" + quantity,
	  				success: function(response) {
	  					console.log(response)
	  					if(response.hasOwnProperty('-1')) {
	  						element.text('Số lượng > 0 hoặc <= ' + pquantity)
	  						setTimeout(function () {
	  							element.text('')
	  						}, 3000)
	  					}
	  					else {
	  						$('#price').html('')
		  					$('#price').html(response['' + id].totalPrice.toLocaleString('de-DE') + ' ₫')
		  					var totalPrice = 0
		  					var totalQuantity = 0
		  					for (const [key, value] of Object.entries(response)) {
		  						totalQuantity += value.quantity
		  						totalPrice += value.totalPrice
		  					}
		  					$('#total_price').html(totalPrice.toLocaleString('de-DE') + ' ₫')
		  					$('#total_quantity').html(totalQuantity + " sản phẩm")
	  					}
	  				},
	  				error: function(XMLHttpRequest, textStatus, errorThrown) {
	  					element.text('Số lượng > 0 hoặc <= ' + pquantity)
	  					setTimeout(function () {
	  							element.text('')
	  					}, 3000)
	  				}
				})
			})
			var messages = document.querySelectorAll('.e-message');
		    Array.from(messages).forEach(message => {
		      if(message.innerHTML != null) {
		        setTimeout(function() {
		          message.innerHTML = '';
		        }, 5000)
		      }
		    })
		    var mess_container = document.querySelector('.mess-container')
		    mess_container.onclick = function() {
		      mess_container.classList.remove('d-flex')
		    }
		</script>
	</content>
</body>