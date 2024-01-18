<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>Chi tiết sản phẩm</title>
<body>
	<main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <ol>
          <li><a href="<c:url value="/" />">Home</a></li>
          <li><a href="<c:url value="/all" />">Sản phẩm</a></li>
          <li>Chi tiết sản phẩm</li>
        </ol>

      </div>
    </section><!-- End Breadcrumbs -->

    <!-- ======= Portfolio Details Section ======= -->
	<section id="portfolio-details" class="portfolio-details">
    	<div class="container">

        	<div class="row gy-4">
          		<div class="col-lg-6">
            		<div class="portfolio-details-slider swiper">
              			<div class="swiper-wrapper align-items-center">
							<div class="swiper-slide">
								<img src="<c:url value="/assets/img/products/${ productdetails.image }" />" />
								<%-- <c:forEach var="item" items="${ productimages }">
									<c:if test="${ item.is_slideimage }">
										<img src="<c:url value="/assets/img/products/${ item.image_img }" />" />
									</c:if>
								</c:forEach> --%>
							</div>
						

              			</div>
              		<div class="swiper-pagination"></div>
            	</div>
          	</div>

          <div class="col-lg-6">
            <div class="portfolio-info">
              <h3>${ productdetails.name }</h3>
              <ul>
                
                <li><strong>Kích thước</strong>: ${ productdetails.dimension }</li>
                <li><strong>Phụ kiện</strong>: ${ productdetails.accessories }</li>
                <li><strong>Chất liệu</strong>: ${ productdetails.material }</li>
                <li><strong>Hàng còn</strong>: ${ productdetails.quantity }</li>
                <li>
                  <strong>Số lượng</strong>: 
                  <input id="input" class="quantity" type="number" min="1" max="${ productdetails.quantity }" value="1" />
                </li>
                <li>
                  <strong>Giá bán</strong>: 
                  <label class="detail-price">
                  	<fmt:formatNumber type="number" groupingUsed="true" value="${ productdetails.price }" /> ₫
                  </label>
                </li>
                <li class="cart-box" style="margin-top: 40px;">
                  	<c:if test="${ empty loginsession }">
            			<a href="<c:url value="/login" />" class="cart-btn">Thêm vào giỏ 
                    		<i class="bi bi-minecart" style="font-size: 22px;"></i>
                  		</a> 
            		</c:if>
            		<c:if test="${ not empty loginsession }">
            			<span style="cursor: pointer" data-id="${ productdetails.id }" id="btn" class="cart-btn">Thêm vào giỏ 
                    		<i class="bi bi-minecart" style="font-size: 22px;"></i>
                  		</span>  
            		</c:if> 
                </li>
              </ul>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="portfolio-description">
              <h2>Mô tả</h2>
              <p>
                ${ productdetails.description }
              </p>
              <div class="img-container">
              
              	<c:forEach var="item" items="${ productimages }">
					<c:if test="${ not item.is_slideimage }">
					<img class="img-product" src="<c:url value="/assets/img/products/${ item.image_img }" />" />
					</c:if>
				</c:forEach>
				
              </div>
            </div>
          </div>

        </div>

      </div>
    </section><!-- End Portfolio Details Section -->
    <section id="" class="newp">
      <div class="section-title">
        <h2>Sản phẩm liên quan</h2>
      </div>
      	<div class="container width80pt d-flex align-items-center has-scrollbar" style="padding-bottom: 10px;" data-aos="fade-up">

        <div class="row nowrap" data-aos="fade-up" data-aos-delay="200">
        	
        <c:forEach var="item" items="${ relatedproducts }">
          	<div class="col-lg-2 col-md-6 newp-item">
            	<a href="<c:url value="/details/${ item.id }" />">
             		<div class="newp-img"><img src="<c:url value="/assets/img/products/${ item.image }" />" class="img-fluid" alt=""></div>
            	</a>              
          	</div>
		</c:forEach>

        </div>

      </div>
    </section>
	
  </main>
  
	<content tag="script">
  		<script>
  			$('#btn').on('click', function() {
  				var id = $(this).data("id")
				var quantity = $('#input').val()
				$.ajax({
					type: 'get',
	  				url: '${ pageContext.request.contextPath }/addcart',
	  				cache: false,
	  				data: "id="+ id + "&quantity=" + quantity,
	  				success: function(response) {
	  					if(response === 0) {
	  						$('#cart_size').html('')
	  					}
	  					else {
	  						$('#cart_size').html(response)
	  					}
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
  		</script>
	</content>
  
</body>