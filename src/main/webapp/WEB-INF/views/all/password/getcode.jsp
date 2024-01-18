<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>

<title>Nhận mã</title>
<body>
	<div class="box sign-up">

    <div class="row align-items-center">
        <div class="img-box col-lg-4 padding0" style="background-image: url(<c:url value="/assets/img/f52d11967a8a28d7010fc11a7fea9b57.jpg" />); background-repeat: no-repeat; background-size: cover;">
            <h1 class="logo fly"><a class="login-logo" href="<c:url value="/" />">HiroShop</a></h1>
            <div class="color-box"></div>
        </div>
        
        <div class="col-lg-8 padding0">
            <div class="contact">
                <div class="container" data-aos="fade-up">
                    <div class="row form-padding">
                        <div class="col-lg-12 mt-5 mt-lg-0 d-flex align-items-stretch">
                        
                        <form action="getcode" method="post" class="php-email-form" id="code-form">
                        	<div class="form-group">
                                <label for="code">Mã xác nhận</label>
                                <input type="text" class="form-control" name="code" id="code" />
                                <div class="e-message">
                                <c:if test="${ not empty iscorrect }">
                                	Mã sai !
                                </c:if>
                                </div>
                            </div>
                            
                            <div class="form-group">
                            	<label style="font-size: 10px; color: red;">Truy cập vào email bạn đã nhập để nhận mã xác nhận(Trong trường hợp tài khoản của bạn là một email không chính xác)</label>
                              	<div></div>
                              	<a class="forgot-text" href="<c:url value="/forgotpass" />">Tạo lại tên tài khoản</a>
                            </div>
                            
                            <div class="text-center"><button type="submit">Xác nhận</button></div>
                        </form>
                        
                        </div>
          
                    </div>
          
                </div>
            </div>
        </div>
    </div>

  </div>
  
   <c:if test="${ not empty issuccess }">
  <div class="mess-container d-flex">
    <div class="mess-box" stye="width: 700px; text-align: center">
      <label class="mess-success">Thành công! Vui lòng kiểm tra mật khẩu trong email của bạn</label>
      <a href="<c:url value="/login" />" class="login-link">Đăng nhập</a>
    </div>
  </div>
  </c:if>
  
  <content tag="script">
  	<script>
	  	Validator({
	        form: '#code-form',
	        formGroupSelector: '.form-group',
	        errorSelector: '.e-message',
	        rules: [
	          Validator.isRequired('#code'),
	        ]
	      })
  	</script>
  </content>
</body>