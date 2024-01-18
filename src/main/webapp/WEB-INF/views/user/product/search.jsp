<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>Tìm kiếm</title>
<body>
	<main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <ol>
          <li><a href="<c:url value="/" />">Trang chủ</a></li>
          <li><a href="<c:url value="/all" />">Sản phẩm</a></li>
          <li>Tìm kiếm</li>
        </ol>

      </div>
    </section><!-- End Breadcrumbs -->

    <!-- ======= Portfolio Details Section ======= -->
    <section id="" class="allp">
      <div class="container d-flex align-items-center" style="padding-bottom: 10px;" data-aos="fade-up">
        <div class="row w100" data-aos="fade-up" data-aos-delay="200">
          <div class="col-lg-3 col-md-6 padding0">
            <div class="container">
              <div class="row">
                <div class="col-lg-12">
                  <ul class="list">
                    <li class="list-item">
                      <span class="list-text category">Danh mục sản phẩm</span>
                    </li>
                    <ul class="list-category">
                    	<li class="list-category-item">
                        	<a href="<c:url value="/all" />">
                          		<div class="container">
                            		<div class="row list-category-box">
                              			<div class="col-lg-3 padding0">
                                			<img class="list-category-img" src="<c:url value="/assets/img/categories/all.png" />" alt="" />
                              			</div>
                              			<div class="col-lg-9 padding0">
                                			<span class="list-category-text">Tất cả sản phẩm</span>
                              			</div>
                            		</div>
                          		</div>
                        	</a>
                      	</li>
                    <c:forEach var="item" items="${ categories }">
                    	<li class="list-category-item">
                        	<a href="<c:url value="/category${ item.id }" />">
                          		<div class="container">
                            		<div class="row list-category-box">
                              			<div class="col-lg-3 padding0">
                                			<img class="list-category-img" src="<c:url value="/assets/img/categories/${ item.icon }" />" alt="" />
                              			</div>
                              			<div class="col-lg-9 padding0">
                                			<span class="list-category-text">${ item.name }</span>
                              			</div>
                            		</div>
                          		</div>
                        	</a>
                      	</li>
                    </c:forEach>
                    </ul> 
                  </ul>
                </div>
              </div>
            </div>           
          </div>

          <div class="col-lg-9 col-md-6 padding0">
            <section id="" class="newp padding0">
              <div class="container d-flex" style="padding-bottom: 10px;" data-aos="fade-up">
        
                <div class="row" data-aos="fade-up" data-aos-delay="200" style="width:100%">
                	
                	<c:if test="${ empty pagination }">
                		<div class="col-lg-12" style="text-align: center; font-size: 22px">
                			<span tyle="text-align: center; font-size: 22px; width:100%">Không có sản phẩm nào phù hợp với từ khóa</span>
                		</div>
                	</c:if>
           
                	<c:forEach var="item" items="${ pagination }">
                		<div class="col-lg-3 col-md-6 allp-item">
                    		<a class="product-container" href="<c:url value="/details/${ item.id }" />">
                      			<div class="allp-img"><img src="<c:url value="/assets/img/products/${ item.image }" />" class="img-fluid" alt=""></div>
                      			<div class="pro-content">
                        			<h5 class="pro-title">${ item.name }</h5>
                        			<h4 class="pro-price">
                        				<fmt:formatNumber type="number" groupingUsed="true" value="${ item.price }" /> ₫
                        			</h4>
                      			</div>
                    		</a>
                    		<c:if test="${ empty loginsession }">
            					<a href="<c:url value="/login" />" class="pro-icon"><i class="bi bi-minecart"></i></a>  
            				</c:if>
            				<c:if test="${ not empty loginsession }">
            					<a data-id="${ item.id }" class="add_cart pro-icon"><i class="bi bi-minecart"></i></a>  
            				</c:if>              
                  		</div>
                	</c:forEach>

                  <c:if test="${ totaldata > 12 }">
						<div class="col-lg-12 d-flex justify-content-center">
                    		<div class="pagination">
                      			<a href="<c:url value="/search${ searchstring }/p1" />">&laquo;</a>
                      			
                      		<c:forEach var="item" begin="1" end="${ paginationinfo.total }" varStatus="itemS">
                      			<c:if test="${ itemS.index == paginationinfo.current }">
                      				<a href="<c:url value="/search${ searchstring }/p${ itemS.index }" />" class="active">${ itemS.index }</a>
                      			</c:if>
                      			<c:if test="${ itemS.index != paginationinfo.current }">
                      				<a href="<c:url value="/search${ searchstring }/p${ itemS.index }" />">${ itemS.index }</a>
                      			</c:if>
                      			<c:set var="lastindex" value="${ itemS.index }" />
                      		</c:forEach>

			                    <a href="<c:url value="/search${ searchstring }/p${ lastindex }" />">&raquo;</a>
                    		</div>
                  		</div>
                	</c:if>
        
                </div>
        
              </div>
            </section>
          </div>
        </div>

      </div>
    </section>

  </main>
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
  					$('#cart_size').html(response)
  				}
  			})
  		}) 
  	</script>
  </content>
</body>