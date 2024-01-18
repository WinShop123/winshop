<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>WinShop</title>
<body>
	<section id="hero" class="d-flex align-items-center">
      <div class="container w100">
        <div class="row"> 
          <div class="col-lg-12">
            <div class="portfolio-details-slider swiper">
              <div class="swiper-wrapper align-items-center">
  
                <c:forEach var="item" items="${ slides }">
                	<div class="swiper-slide">
                  		<section id="" class="d-flex align-items-center">
                    		<div class="container">
                      			<div class="row">
                        			<div class="col-lg-6 d-flex flex-column justify-content-center pt-4 pt-lg-0 order-2 order-lg-1" data-aos="fade-up" data-aos-delay="200">
                          				<h1>${ item.title }</h1>
                          				<h2>${ item.content }</h2>
                          				<div class="d-flex justify-content-center justify-content-lg-start">
                            				<a href="<c:url value="${ item.click }" />" class="btn-get-started scrollto">Xem ngay</a>
                            			<c:if test="${ not empty item.video }">
                            				<a href="${ item.video }" class="glightbox btn-watch-video"><i class="bi bi-play-circle"></i><span>Watch Video</span></a>
                            			</c:if>
                          				</div>
                        			</div>
                        			<div class="col-lg-6 order-1 order-lg-2 hero-img" data-aos="zoom-in" data-aos-delay="200">
                          				<img src="<c:url value="/assets/img/slides/${ item.image }" />" class="img-fluid animated" alt="">
                        			</div>
                      			</div>
                    		</div>
                  		</section>
                	</div>
                </c:forEach>
  
              </div>
              <div class="swiper-pagination"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  
  <main id="main">
    <!-- ======= Categories Section ======= -->
    <section class="categories">
      <div class="container width80pt section-ct">

        <div class="row nowrap has-scrollbar" data-aos="zoom-in">

		<c:forEach var="item" items="${ categories }">
			<div class="col-lg-3 col-md-4 col-6 d-flex align-items-center justify-content-center">
				<a href="<c:url value="/category${ item.id }" />"><img src="<c:url value="/assets/img/categories/${ item.logo }" />" alt="cat-img" /></a>
			</div>
		</c:forEach>
		
        </div>
        
      </div>
    </section>

    <c:if test="${ newproducts.size() >= 4 }">
    <section id="" class="newp">
      <div class="section-title ">
        <h2>Sản phẩm mới</h2>
      </div>
      <div class="container width80pt d-flex align-items-center has-scrollbar" style="padding-bottom: 10px;" data-aos="fade-up">

        <div class="row nowrap" data-aos="fade-up" data-aos-delay="200">
        
        <c:forEach var="item" items="${ newproducts }">
        	<div class="col-lg-3 col-md-6 newp-item">
            	<a href="<c:url value="/details/${ item.id }" />">
              		<div class="newp-img"><img src="<c:url value="assets/img/products/${ item.image }" />" class="img-fluid" alt=""></div>
            	</a>              
          	</div>
        </c:forEach>

        </div>

      </div>
    </section>
    </c:if>

    <c:if test="${ topsellproducts.size() >= 4 }">
    <section id="" class="newp">
      <div class="section-title ">
        <h2>Bán chạy</h2>
      </div>
      <div class="container width80pt d-flex align-items-center has-scrollbar" style="padding-bottom: 10px;" data-aos="fade-up">

        <div class="row nowrap" data-aos="fade-up" data-aos-delay="200">
        
        <c:forEach var="item" items="${ topsellproducts }">
        	<div class="col-lg-3 col-md-6 newp-item">
            	<a href="<c:url value="/details/${ item.id }" />">
              		<div class="newp-img"><img src="<c:url value="assets/img/products/${ item.image }" />" class="img-fluid" alt=""></div>
            	</a>              
          	</div>
        </c:forEach>

        </div>

      </div>
    </section>
    </c:if>

    <section id="cta" class="cta">
      <div class="container" data-aos="zoom-in">

        

      </div>
    </section>

    <section id="" class="allp">
      <div class="section-title ">
        <h2>Tất cả sản phẩm</h2>
      </div>
      <div class="container width80pt d-flex align-items-center" style="padding-bottom: 10px;" data-aos="fade-up">

        <div class="row" data-aos="fade-up" data-aos-delay="200">
        
        <c:forEach var="item" items="${ products }">
        	<div class="col-lg-3 col-md-6 allp-item">
            	<a class="product-container" href="<c:url value="/details/${ item.id }" />">
              	<div class="allp-img"><img src="<c:url value="assets/img/products/${ item.image }" />" class="img-fluid" alt=""></div>
              	<div class="pro-content">
                	<h5 class="pro-title">${ item.name }</h5>
                	<h4 class="pro-price">
                		<fmt:formatNumber type="number" groupingUsed="true" value="${ item.price }" /> ₫
                	</h4>
              	</div>
            	</a>
            		<span data-id="${ item.id }" class="add_cart pro-icon"><i class="bi bi-minecart"></i></span>  
         
        	</div>
        </c:forEach>
        
        </div>
      </div>
    </section>

  </main><!-- End #main -->
  
  <content tag="script">
  	<script type="text/javascript">
  		$('.add_cart').click(function() {
  			var id = $(this).data('id');
  			$.ajax({
  				type: 'get',
  				url: '${ pageContext.request.contextPath }/addcart',
  				cache: false,
  				data: "id="+ id + "&quantity=1",
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
  	</script>
  </content>
</body>