<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>Đăng ký</title>
</head>
<body>
	<main id="main" class="sign-up">
    <div class="logo-box">
        <h1 class="logo"><a class="in-signup" href="<c:url value="/" />">WinShop</a></h1>
    </div>

    <section id="contact" class="contact">
        <div class="container signup-box-w" data-aos="fade-up">
  
          <div class="row">
  
            <div class="col-lg-12 mt-5 mt-lg-0 d-flex align-items-stretch">
            	<form:form action="signup" method="POST" modelAttribute="newaccount" class="php-email-form" id="sign-up-form">
            		<div class="form-group">
                    	<label for="name">Họ tên</label>
                    	<form:input path="name" type="text" name="name" class="form-control" id="name" />
                    	<div class="e-message"></div>
                  	</div>
                  	<div class="form-group">
                    	<label for="email">Email</label>
                    	<form:input path="username" type="text" name="email" class="form-control" id="email" />
                    	<div class="e-message">
                    	<c:if test="${ not empty isexist }">
                    		Tài khoản đã tồn tại !
                    	</c:if>
                    	</div>
                  	</div>
                  	<div class="form-group">
                    	<label for="password">Mật khẩu</label>
                    	<form:input path="password" type="password" name="password" class="form-control" id="password" />
                    	<div class="e-message"></div>
                  	</div>
                  	<div class="form-group">
                    	<label for="re_password">Nhập lại mật khẩu</label>
                    	<input type="password" name="re_password" class="form-control" id="re_password" />
                    	<div class="e-message"></div>
                  	</div>
                  	<div class="form-group">
                    	<div class="e-message">
                    	<c:if test="${ not empty error }">
                    		Lỗi tạo tài khoản ${ error }
                    	</c:if>
                    	</div>
                  	</div>
                	<div class="text-center"><button type="submit">Đăng ký</button></div>
            	</form:form>
            </div>
  
          </div>
  
        </div>
      </section>

  </main>
  
  <c:if test="${ not empty issuccess }">
  <div class="mess-container d-flex">
    <div class="mess-box">
      <label class="mess-success">Đăng ký thành công !</label>
      <a href="<c:url value="/login" />" class="login-link">Đăng nhập</a>
    </div>
  </div>
  </c:if>
  
  <content tag="script">
  	<script>
	  	Validator({
	        form: '#sign-up-form',
	        formGroupSelector: '.form-group',
	        errorSelector: '.e-message',
	        rules: [
	          Validator.isRequired('#name'),
	          Validator.isValidName('#name'),
	          Validator.isRequired('#email'),
	          Validator.isEmail('#email'),
	          Validator.isRequired('#password'),
	          Validator.minLength('#password', 6),
	          Validator.isRequired('#re_password'),
	          Validator.isConfirmed('#re_password', function () {
	            return document.querySelector('#sign-up-form #password').value
	          }, 'Mật khẩu nhập lại không chính xác!')
	        ]
	      })
  	</script>
  </content>
</body>