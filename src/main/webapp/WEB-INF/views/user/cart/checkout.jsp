<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>Thanh toán</title>
<body>
	<main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <ol>
          <li><a href="<c:url value="/" />">Trang chủ</a></li>
          <li><a href="<c:url value="/cart" />">Giỏ hàng</a></li>
          <li>Đặt hàng</li>
        </ol>

      </div>
    </section><!-- End Breadcrumbs -->

    <section id="contact" class="contact">
        <div class="container width80pt" data-aos="fade-up">
  
          <div class="row">
  
            <div class="col-lg-12 mt-5 mt-lg-0 d-flex align-items-stretch">
            	<form:form action="checkout" method="post" modelAttribute="bill" class="php-email-form" id="checkout-form">
            		<div class="row">
                  		<div class="form-group col-md-12">
                    		<label for="phone">Số điện thoại</label>
                    		<form:input path="phone" type="text" class="form-control" name="phone" id="phone" />
                    		<div class="e-message"></div>
                  		</div>
               		</div>
                	<div class="form-group">
                		<label for="email">Email</label>
                		<form:input path="username" type="text" class="form-control" name="email" id="email" />
                		<div class="e-message"></div>
                	</div>
                	<div class="form-group">
                    	<label for="address">Địa chỉ</label>
                    	<form:input path="address" type="text" class="form-control" name="address" id="address" />
                    	<div class="e-message"></div>
                  	</div>
                	<div class="form-group">
                  		<label for="note">Ghi chú</label>
                  		<form:textarea path="note" class="form-control" name="note" id="note" rows="10" />
               		</div>
                	<div class="row">
                    	<div class="form-group col-md-6 text-center">
                      		<label class="font">${ totalQuantity } sản phẩm</label>
                    	</div>
                    	<div class="form-group col-md-6 text-center">
                     		<label class="font">
                     			<fmt:formatNumber type="number" groupingUsed="true" value="${ totalPrice }" /> ₫
                     		</label>
                    	</div>
                  	</div>
                	<div class="text-center"><button type="submit">Đặt hàng</button></div>
            	</form:form>
            </div>
  
          </div>
  
        </div>
      </section>

  </main>
  
  <content tag="script">
      	<script>
      		Validator({
    	        form: '#checkout-form',
    	        formGroupSelector: '.form-group',
    	        errorSelector: '.e-message',
    	        rules: [
    	          Validator.isRequired('#phone'),
    	          Validator.isPhoneNumber('#phone'),
    	          Validator.isRequired('#email'),
    	          Validator.isRequired('#address')
    	        ]
    	      })
      	</script>
      </content>
</body>