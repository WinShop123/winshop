<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Thêm mới</title>
<body>
	<div>
        <h2 class="table-name">Thêm mới</h2>
        <form:form action="createproduct" method="post" modelAttribute="newproduct" enctype="multipart/form-data" id="create-form">
        	<div class="form-group row">
            	<label for="category" class="col-lg-2 col-form-label">Danh mục</label>
              	<div class="col-lg-6">
              		<form:select path="category_id" id="category">
              			<option value="" selected>---Danh mục---</option>
              		<c:forEach var="item" items="${ categories }">
              			<form:option value="${ item.id }" label="${ item.name }" />
              		</c:forEach>
              		</form:select>
              	</div>
              	<div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="name" class="col-lg-2 col-form-label">Tên sản phẩm</label>
              <div class="col-lg-6">
              	<form:input path="name" type="text" class="form-control" id="name" placeholder="Tên sản phẩm" />
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
              <label for="dimension" class="col-lg-2 col-form-label">Kích thước</label>
              <div class="col-lg-6">
              	<form:input path="dimension" type="text" class="form-control" id="dimension" placeholder="Kích thước" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="material" class="col-lg-2 col-form-label">Chất liệu</label>
              <div class="col-lg-6">
              	<form:input path="material" type="text" class="form-control" id="material" placeholder="Chất liệu" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="accessories" class="col-lg-2 col-form-label">Phụ kiện</label>
              <div class="col-lg-6">
              	<form:input path="accessories" type="text" class="form-control" id="accessories" placeholder="Phụ kiện" />
              </div>
              <div class="e-message"></div>
            </div>
          	<div class="form-group row">
              <label for="quantity" class="col-lg-2 col-form-label">Số lượng</label>
              <div class="col-lg-6">
              	<form:input path="quantity" type="text" class="form-control" id="quantity" placeholder="Số lượng" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="price" class="col-lg-2 col-form-label">Giá cả</label>
              <div class="col-lg-6">
              	<form:input path="price" type="text" class="form-control" id="price" placeholder="Giá cả" />
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="description" class="col-lg-2 col-form-label">Mô tả</label>
              <div class="col-lg-6">
              	<form:input path="description" type="text" class="form-control" id="description" placeholder="Mô tả" />
              </div>
              <div class="e-message"></div>
            </div>
          	<div class="form-group row">
              <label for="saleoff" class="col-lg-2 col-form-label">Giảm giá</label>
              <div class="col-lg-6">
              	<form:input path="sale_off" type="text" class="form-control" id="saleoff" placeholder="Giảm giá" />
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
    	          Validator.isRequired('#category'),
    	          Validator.isRequired('#company'),
    	          Validator.isRequired('#name'),
    	          Validator.isRequired('#imageupload'),
    	          Validator.isRequired('#dimension'),
    	          Validator.isRequired('#material'),
    	          Validator.isRequired('#accessories'),
    	          Validator.isRequired('#quantity'),
    	          Validator.isIntegerNumber('#quantity'),
    	          Validator.isRequired('#price'),
    	          Validator.isFloatNumber('#price'),
    	          Validator.isRequired('#description'),
    	          Validator.isRequired('#price'),
    	          Validator.isRequired('#saleoff'),
    	          Validator.isIntegerNumber('#saleoff'),
    	          Validator.isSaleOffValue('#saleoff')
    	        ]
    	      })
      	</script>
      </content>
</body>