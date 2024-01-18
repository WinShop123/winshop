<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>Đăng nhập</title>
<body>
	<div class="box">

    <div class="row align-items-center">
        <div class="img-box col-lg-4 padding0" style="background-image: url(assets/img/abc.png); background-repeat: no-repeat; background-size: cover;">
            <h1 class="logo fly"><a class="login-logo" href="<c:url value="/" />">WinShop</a></h1>
            <div class="color-box"></div>
        </div>
        
        <div class="col-lg-8 padding0">
            <div class="contact">
                <div class="container" data-aos="fade-up">
                    <div class="row form-padding">
                        <div class="col-lg-12 mt-5 mt-lg-0 d-flex align-items-stretch">
                        
                        <form:form action="login" method="post" modelAttribute="loginaccount" class="php-email-form" id="login-form">
                        	<div class="form-group">
                                <label for="username">Tên đăng nhập</label>
                                <form:input path="username" type="text" class="form-control" name="username" id="username" />
                                <div class="e-message">
                                <c:if test="${ not empty isexist }">
                                	Tài khoản không chính xác !
                                </c:if>
                                </div>
                            </div>
        
                            <div class="form-group">
                                <label for="password">Mật khẩu</label>
                                <form:input path="password" type="password" class="form-control" name="password" id="password"/>
                                <div class="e-message">
                                <c:if test="${ not empty iscorrect }">
                                	Mật khẩu không chính xác !
                                </c:if>
                                </div>
                            </div>
                            
                            <div class="form-group">
                            </div>
                            
                            <div class="text-center"><button type="submit">Đăng nhập</button></div>

                            <div class="line"></div>

                            <a href="<c:url value="/signup" />" class="new-acc">Tạo tài khoản mới</a>
                        </form:form>
                        
                        </div>
          
                    </div>
          
                </div>
            </div>

        </div>
    </div>

  </div>
  
  <content tag="script">
  	<script>
	  	Validator({
	        form: '#login-form',
	        formGroupSelector: '.form-group',
	        errorSelector: '.e-message',
	        rules: [
	          Validator.isRequired('#username'),
	          Validator.isEmail('#username'),
	          Validator.isRequired('#password')
	        ]
	      })
  	</script>
  </content>
</body>