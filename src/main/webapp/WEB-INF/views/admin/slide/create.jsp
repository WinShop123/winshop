<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Thêm mới</title>
<body>
	<div>
        <h2 class="table-name">Thêm mới</h2>
        <form:form action="createslide" method="post" modelAttribute="newslide" enctype="multipart/form-data" id="create-form">
            <div class="form-group row">
              <label for="title" class="col-lg-2 col-form-label">Tiêu đề</label>
              <div class="col-lg-6">
              	<form:input path="title" type="text" class="form-control" id="title" placeholder="Tiêu đề" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="content" class="col-lg-2 col-form-label">Nội dung</label>
              <div class="col-lg-6">
              	<form:input path="content" type="text" class="form-control" id="content" placeholder="Nội dung" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="click" class="col-lg-2 col-form-label">Click link</label>
              <div class="col-lg-6">
              	<form:input path="click" type="text" class="form-control" id="click" placeholder="Click link" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="imageupload" class="col-lg-2 col-form-label">Hình ảnh</label>
              <div class="col-lg-6">
              	<div><img id="image" class="img-fluid" src="<c:url value="/assets/img/no_image.png" />" alt="Image" /></div>
              	<input type="file" class="form-control-file" name="imageupload" id="imageupload" onchange=loadFile(event) />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="video" class="col-lg-2 col-form-label">Video</label>
              <div class="col-lg-6">
              	<form:input path="video" type="text" class="form-control" id="video" placeholder="Video" />
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
      		var loadFile = function(event) {
      			var image = document.getElementById('image')
      			image.src = URL.createObjectURL(event.target.files[0]);
      		}
      		Validator({
    	        form: '#create-form',
    	        formGroupSelector: '.form-group',
    	        errorSelector: '.e-message',
    	        rules: [
    	          Validator.isRequired('#title'),
    	          Validator.isRequired('#content'),
    	          Validator.isRequired('#click'),
    	          Validator.isRequired('#imageupload')
    	        ]
    	      })
      	</script>
	</content>  
</body>