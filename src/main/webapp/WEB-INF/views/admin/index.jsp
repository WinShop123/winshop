<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/all/taglib.jsp" %>    
    
<title>Dasboard</title>
<body>
	<div class="d-box">
        <div class="d-revenue col-lg-6">
          <img class="dash-img" src="<c:url value="/assets/img/revenue.png" />" />
          <h3>Tổng thu nhập</h3>
          <h2 style="color: #209dd8;" id="revenue">
          	<fmt:formatNumber type="number" groupingUsed="true" value="${ revenue }" /> ₫
          </h2>
        </div>
        <div class="d-quantity col-lg-6">
          <img class="dash-img" src="<c:url value="/assets/img/totalproduct.png" />" />
          <h3>Tổng sản phẩm đã bán</h3>
          <h2 style="color: #209dd8;" id="quantity">${ soldproducts }</h2>
        </div>
        <div class="col-lg-12" style="text-align: center; padding: 70px;">
          <input id="days" type="number" min="1" value="${ days }" style="width: 60px;"/> ngày gần nhất
          <div class="e-message"></div>
        </div>
     </div>
     
     <content tag="script">
     	<script>
     		$('#days').bind('keyup mouseup', function(){
     			var days = $(this).val()
     			console.log(typeof days)
     			if(days <= 0 || days === '') {
     				$('.e-message').html('Số ngày > 0')
     				setTimeout(function() {
     					$('.e-message').html('')
     				}, 3000)
     			}
     			else {
     				$.ajax({
     					type: 'get',
    	  				url: '${ pageContext.request.contextPath }/admin/modifyday',
    	  				cache: false,
    	  				data: "days=" + days,
    	  				success: function(response) {
    	  					console.log(response)
    	  					var object = JSON.parse(response)
    	  					$('#revenue').html(object.revenue.toLocaleString('de-DE') + ' ₫')
    	  					$('#quantity').html(object.soldproducts)
    	  					$('#days').html(object.days)
    	  				}
     				})
     			}
     		})
     	</script>
     </content>
</body>