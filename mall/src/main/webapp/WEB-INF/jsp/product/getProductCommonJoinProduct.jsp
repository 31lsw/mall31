<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- jquery를 사용하기위한 CDN주소 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  
<!-- bootstrap javascript소스를 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script>
    $(document).ready(function(){
    //    alert('jquery test');
    /* 입력폼 유효성 관련 요구사항
        1. 모든 폼은 공백 또는 "" 문자는 입력되면 안된다.
        2. 비밀번호는 4자이상 입력하여야 한다.
    */
        $('#orderBtn').click(function(){
            if($('#productColor').val()=='') {
                alert('productColor을 선택하세요');
                $('#productColor').focus();
            /* } else if($('#boardCommentUser').val()=='') {
                alert('boardCommentUser을 입력하세요');
                $('#boardCommentUser').focus();
            } else if($('#boardCommentPw').val().length <4) {
                alert('boardCommentPw는 4자이상 이어야 합니다');
                $('#boardCommentPw').focus(); */
            } else {
                $('#orderForm').submit();
            }
        });
    
       $('#productColor').change(function(){
            if($('#productColor').val()=='') {
                alert('productColor을 선택하세요');
                $('#productColor').focus();
            } else {
                $('#orderForm').submit();
            }
        });
    
    });
</script>

<title>BOARD VIEW(spring mvc 방식 + mybatis)</title>
</head>
<body>

<!-- 게시글 출력 -->
<div class="container">
	<h1> 상품 상세보기 </h1>
	<table>
		<tr>
			<td>productCommonNo : </td>
			<td>${productCommon.productCommonNo}</td>
		</tr>
		<tr>
			<td>categoryNo : </td>
			<td>${productCommon.categoryNo}</td>
		</tr>
		<tr>
			<td>productCommonName : </td>
			<td>${productCommon.productCommonName}</td>
		</tr>
		<tr>
			<td>productCommonPrice : </td>
			<td>${productCommon.productCommonPrice}</td>
		</tr>
		<tr>
			<td>productCommonDescription : </td>
			<td>${productCommon.productCommonDescription}</td>
		</tr>
		<tr>
			<td>productCommonDate : </td>
			<td>${productCommon.productCommonDate}</td>
		</tr>
			
	</table>
	
	
	<!-- 1. 옵션 하나에 사이즈,색상,재고량 표시 전부다 되도록 -->
	<%-- 
	<form id="orderForm" method="post" action="${pageContext.request.contextPath}/product/getProductCommonJoinProductByProductCommonNo">
		<input type="hidden" value="${productCommonNo}">
		<select id="productOption" name="productOption">
			<c:forEach items="${products}" var="p">
				<option value="${p.productColor}, ${p.productSize}">${p.productColor}, ${p.productSize}</option>
			</c:forEach>
		</select>
		<button id="orderBtn" type="button">주문하기</button>
	</form>
	--%>
		 
	 <!-- 2. 셀렉트박스로 색상/사이즈/재고량  선택하면서 보여주게끔 -->
	 <form id="orderForm" method="get" action="${pageContext.request.contextPath}/product/getProductCommonJoinProductByProductCommonNo">
		<div>
			<input type="hidden" name="productCommonNo" value="${productCommon.productCommonNo}">
		</div>
			<!-- 중복 표시를 막기위해 더미로 사용할 변수 : 색상을 선택하지 않을 경우 color의 기본값은 "default" -->
			<c:set var="color" value="default" ></c:set>
			<!-- 색상 셀렉트 박스 -->
			<select id="productColor" name="productColor">
				<option value="" selected>===선택===</option>
					<c:forEach items="${productCommon.products}" var="p">
						<!-- 셀렉트 박스에서 색상 선택을 한번도 안했을 경우 다음 옵션이 나타남-->
						<c:if test="${p.productColor ne color}">
							<option value="${p.productColor}">${p.productColor}</option>
							<!-- 선택한 색상으로 더미변수의 값이 변경됨 -->
							<c:set var="color" value="${p.productColor}"></c:set>
						</c:if>
					</c:forEach>
			</select>
		<!-- 색상 선택시 관련 색상에 대한 사이즈와 재고량만 선택할 수 있다 --> 
		<select id="productSize" name="productSize">
			<option value="0" selected>===선택===</option>
			<c:forEach items="${productCommonWithProductColor.products}" var="p">
				<c:if test="${p.productColor eq productColorSelected}">
					<option value="${p.productSize}">${p.productSize}(재고량:${p.productStock})</option>
				</c:if>
			</c:forEach>
		</select>
		
		<button id="orderBtn" type="button">주문하기</button>
	</form>
	 
	 
</div>

</body>
</html>