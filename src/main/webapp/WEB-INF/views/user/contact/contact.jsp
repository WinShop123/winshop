<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>Liên hệ</title>
<body>

	<main id="sign-up">
		<section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <ol>
          <li><a href="<c:url value="/" />">Trang chủ</a></li>
          <li>Liên hệ</li>
        </ol>

      </div>
    </section>
		<section id="contact" class="contact">
        <div class="container" data-aos="fade-up">
  
          <div class="section-title">
            <h2>Liên hệ</h2>
            <p>Liên hệ với chúng tôi.</p>
          </div>
  
          <div class="row">
  
            <div class="col-lg-5 d-flex align-items-stretch">
              <div class="info">
                <div class="address">
                  <i class="bi bi-geo-alt"></i>
                  <h4>Vị trí:</h4>
                  <p>Số 76 - HiroShop, T.T Chờ, Yên Phong, Bắc Ninh</p>
                </div>
  
                <div class="email">
                  <i class="bi bi-envelope"></i>
                  <h4>Email:</h4>
                  <p>hiroshop76@gmail.com</p>
                </div>
  
                <div class="phone">
                  <i class="bi bi-phone"></i>
                  <h4>Điện thoại:</h4>
                  <p>0585880406</p>
                </div>
  
                <div style="text-align: center;">
                    <img src="assets/img/bigicon.png" style="width: 70%;" />
                </div>
              </div>
  
            </div>
  
            <div class="col-lg-7 mt-5 mt-lg-0 d-flex align-items-stretch">
              <form:form action="contact" method="post" class="php-email-form" modelAttribute="contact" id="contact-form">
                <div class="row">
                  <div class="form-group col-md-6">
                    <label for="name">Họ tên</label>
                    <form:input path="name" type="text" name="name" class="form-control" id="name" />
                    <div class="e-message"></div>
                  </div>
                  <div class="form-group col-md-6">
                    <label for="email">Email</label>
                    <form:input path="email" type="email" class="form-control" name="email" id="email" value="${ email }" />
                    <div class="e-message"></div>
                  </div>
                </div>
                <div class="form-group">
                  <label for="subject">Tiêu đề</label>
                  <form:input path="title" type="text" class="form-control" name="subject" id="subject" />
                  <div class="e-message"></div>
                </div>
                <div class="form-group">
                  <label for="message">Tin nhắn</label>
                  <form:textarea path="message" class="form-control" name="message" rows="10" id="message" />
                  <div class="e-message"></div>
                </div>
                <div class="text-center"><button type="submit">Gửi</button></div>
              </form:form>
            </div>
  
          </div>
  
        </div>
      </section>
	</main>
      
     <c:if test="${ success }">
  	<div class="mess-container d-flex">
    <div class="mess-box">
      <label class="mess-success">Gửi tin nhắn thành công !</label>
    </div>
  </div>
  </c:if>
      
      <content tag="script">
  	<script>
	  	var mess_container = document.querySelector('.mess-container')
	    mess_container.onclick = function() {
	      mess_container.classList.remove('d-flex')
	    }
	  	Validator({
	        form: '#contact-form',
	        formGroupSelector: '.form-group',
	        errorSelector: '.e-message',
	        rules: [
	          Validator.isRequired('#name'),
		      Validator.isValidName('#name'),
	          Validator.isRequired('#email'),
	          Validator.isEmail('#email'),
	          Validator.isRequired('#subject'),
	          Validator.isRequired('#message')
	        ]
	      })
  	</script>
  </content>
</body>