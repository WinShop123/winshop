<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>Đổi mật khẩu</title>
<body>
	<div class="box sign-up">

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
                        
                        <form:form action="changepass"  method="post" modelAttribute="changepassword" class="php-email-form" id="change-form">
                        	<div class="form-group">
                                <label for="password">Mật khẩu cũ</label>
                                <form:input path="password" type="password" class="form-control" name="password" id="password" />
                                <div class="e-message">
                                <c:if test="${ not empty iscorrect }">
                                	Mật khẩu không chính xác !
                                </c:if>
                                </div>
                            </div>
        
                            <div class="form-group">
                                <label for="password">Mật khẩu mới</label>
                                <input type="password" class="form-control" name="newpassword" id="newpassword"/>
                                <div class="e-message"></div>
                            </div>
                            <div class="form-group">
                                <label for="password">Nhập lại mật khẩu mới</label>
                                <input type="password" class="form-control" name="re_password" id="re_password"/>
                                <div class="e-message"></div>
                            </div>
                            
                            <div class="form-group">
                            </div>
                            
                            <div class="text-center"><button type="submit">Đổi mật khẩu</button></div>
                        </form:form>
                        
                        </div>
          
                    </div>
          
                </div>
            </div>

        </div>
    </div>

  </div>
  
    <c:if test="${ not empty issuccess }">
  <div class="mess-container d-flex">
    <div class="mess-box">
      <label class="mess-success">Đổi mật khẩu thành công !</label>
      <a href="<c:url value="/" />" class="login-link">Ok</a>
    </div>
  </div>
  </c:if>
  
  <content tag="script">
  	<script>
	  	Validator({
	        form: '#change-form',
	        formGroupSelector: '.form-group',
	        errorSelector: '.e-message',
	        rules: [
	          Validator.isRequired('#password'),
	          Validator.isRequired('#newpassword'),
	          Validator.minLength('#newpassword', 6),
	          Validator.isRequired('#re_password'),
	          Validator.isConfirmed('#re_password', function() {
	        	  return document.querySelector('#change-form #newpassword').value
	          }, 'Mật khẩu nhập lại không chính xác!')
	        ]
	      })
  	</script>
  </content>
</body>