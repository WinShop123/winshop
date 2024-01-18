<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Thêm mới</title>
<body>
	<div>
        <h2 class="table-name">Thêm mới</h2>
        <form:form action="createcategory" method="post" modelAttribute="newcategory" enctype="multipart/form-data" id="create-form">
            <div class="form-group row">
              <label for="name" class="col-lg-2 col-form-label">Tên danh mục</label>
              <div class="col-lg-6">
              	<form:input path="name" type="text" class="form-control" id="name" placeholder="Tên danh mục" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="logoupload" class="col-lg-2 col-form-label">Logo</label>
              <div class="col-lg-6">
              	<div><img id="logo" class="img-fluid" src="<c:url value="/assets/img/no_image.png" />" alt="Image" /></div>
              	<input type="file" class="form-control-file" name="logoupload" id="logoupload" onchange=loadLogo(event) />
              </div>
              <div class="e-message"></div>
            </div>
          	<div class="form-group row">
              <label for="iconupload" class="col-lg-2 col-form-label">Icon</label>
              <div class="col-lg-6">
              	<div><img id="icon" class="img-fluid" src="<c:url value="/assets/img/no_image.png" />" alt="Image" /></div>
              	<input type="file" class="form-control-file" name="iconupload" id="iconupload" onchange=loadIcon(event) />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row for-btn">
              <div class="col-lg-10">
                <button type="submit" class="btn btn-primary">Thêm mới</button>
              </div>
            </div>
          </form:form>
      </div>
      
      <content tag="script">
      	<script>
      		var loadLogo = function(event) {
      			var logo = document.getElementById('logo')
      			logo.src = URL.createObjectURL(event.target.files[0]);
      		}
      		var loadIcon = function(event) {
      			var icon = document.getElementById('icon')
      			icon.src = URL.createObjectURL(event.target.files[0]);
      		}
      		Validator({
    	        form: '#create-form',
    	        formGroupSelector: '.form-group',
    	        errorSelector: '.e-message',
    	        rules: [
    	          Validator.isRequired('#name'),
    	          Validator.isRequired('#logoupload'),
    	          Validator.isRequired('#iconupload')
    	        ]
    	      })
      	</script>
      </content>
</body>