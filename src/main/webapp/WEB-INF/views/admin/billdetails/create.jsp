<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>  

<title>Thêm mới</title>
<body>
	<div>
        <h2 class="table-name">Thêm mới</h2>
        <form:form action="createbilldetails" method="post" modelAttribute="newbilldetails" id="create-form">
            <div class="form-group row">
              <label for="bill_id" class="col-lg-2 col-form-label">Id hóa đơn</label>
              <div class="col-lg-6">
              	<form:select path="bill_id" id="bill_id">
              		<option value="" selected>----Hóa đơn----</option>
              		<c:forEach var="item" items="${ bills }">
              			<form:option value="${ item.id }" label="${ item.id }" />
              		</c:forEach>
              	</form:select>
              </div>
              <div class="e-message"></div>
            </div>
            <div class="form-group row">
              <label for="product" class="col-lg-2 col-form-label">Tên sản phẩm</label>
              <div class="col-lg-6">
              	<form:select path="product_id" id="product">
              		<option value="" selected>----Sản phẩm----</option>
              		<c:forEach var="item" items="${ products }">
              			<form:option value="${ item.id }" label="${ item.name }" />
              		</c:forEach>
              	</form:select>
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
              <label for="total" class="col-lg-2 col-form-label">Tổng tiền</label>
              <div class="col-lg-6">
              	<form:input path="total" type="text" class="form-control" id="total" placeholder="Tổng tiền" />
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
	  	Validator({
	        form: '#create-form',
	        formGroupSelector: '.form-group',
	        errorSelector: '.e-message',
	        rules: [
	          Validator.isRequired('#bill_id'),
	          Validator.isRequired('#product'),
	          Validator.isRequired('#quantity'),
	          Validator.isRequired('#total')
	        ]
	      })
  	</script>
  </content>
</body>