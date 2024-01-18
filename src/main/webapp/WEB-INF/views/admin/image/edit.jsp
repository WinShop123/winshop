<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Sửa</title>
<body>
	<div>
        <h2 class="table-name">Sửa</h2>
        <form:form action="editimage${ editimage.id }" method="post" modelAttribute="editimage" enctype="multipart/form-data" id="edit-form">
            <div class="form-group row">
              <label for="productname" class="col-lg-2 col-form-label">Sản phẩm</label>
              	<div class="col-lg-6">
              		<form:select path="product_id" id="productname">
              			<option value="" selected="selected">---Sản phẩm---</option>
              		<c:forEach var="item" items="${ products }">
              			<c:if test="${ item.id == editimage.product_id }">
              				<form:option value="${ item.id }" selected="selected" label="${ item.name }" />
              			</c:if>
              			<c:if test="${ item.id != editimage.product_id }">
              				<form:option value="${ item.id }" label="${ item.name }" />
              			</c:if>
              		</c:forEach>
              		</form:select>
              	</div>
              	<div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="imageupload" class="col-lg-2 col-form-label">Hình ảnh</label>
              <div class="col-lg-6">
              	<div><img id="image" class="img-fluid" src="<c:url value="/assets/img/products/${ editimage.image }" />" alt="Image" /></div>
              	<input type="file" class="form-control-file" name="imageupload" id="imageupload" onchange=loadFile(event) />
              </div>
              <div class="e-message"></div>
            </div>
          	<div class="form-group row">
              <label for="slideimage" class="col-lg-2 col-form-label">Ảnh slide</label>
              <div class="col-lg-6">
              	<form:select path="is_slideimage" id="slideimage">
              		<option value="" selected="selected">---Ảnh slide---</option>
              	<c:if test="${ editimage.is_slideimage }">
              		<option value="true" selected>true</option>
              		<option value="false">false</option>
              	</c:if>
              	<c:if test="${ editimage.is_slideimage == false }">
              		<option value="true">true</option>
              		<option value="false" selected>false</option>
              	</c:if>
              	</form:select>
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row for-btn">
              <div class="col-lg-10">
                <button type="submit" class="btn btn-primary">Sửa</button>
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
    	        form: '#edit-form',
    	        formGroupSelector: '.form-group',
    	        errorSelector: '.e-message',
    	        rules: [
    	          Validator.isRequired('#productname'),
    	          Validator.isRequired('#slideimage')
    	        ]
    	      })
      	</script>
      </content>
</body>