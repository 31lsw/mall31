<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 원격지 jquery 소스 가져오기 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- jquery API 구현 -->
<script>
	
	/* ------ Jquery 설명 ------
	
	1. '$' == 'jquery' 같은 뜻이다
	jQuery(document); //메서드 실행시 return값이 document(jquery로 wrapping된 document, 원래 도큐먼트에 jquery메서드까지 사용할 수 있는 document)
	
	2. 원래 소스 vs jquery
	document.getElementById('test') jquery('#test')
	jQuery(document);
	
	3. load와 ready의 차이
		load는 document가 다 만들어져야 한다. 다 불러와져야 함
		ready는 document가 다 만들어져야하는 게 아니고 tag자체만 읽혀지면 됨. img는 안읽혀져도 불러올 수 있다
	
	4. 콜백함수 : js는 변수에 함수를 담을 수 있다. 
	------------------------ */
	
	$(document).ready(function(){
		$('#searchBtn').click(function(){
			$('#searchForm').submit();
		});	
	});
	
</script>
</head>
<body>

<div class="container">
	<h1>상품목록</h1>
	<table class="table table-striped">
		<c:forEach items="${list}" var="productCommon">
				<tr>
					<td>${productCommon.productCommonNo}</td>
					<td><a href="${pageContext.request.contextPath}/product/getProductCommonJoinProductByProductCommonNo?productCommonNo=${productCommon.productCommonNo}">${productCommon.productCommonName}</a></td>
					<td>${productCommon.productCommonPrice}</td>
					<td>${productCommon.productCommonDescription}</td>
					<td>${productCommon.productCommonDate}</td>
				</tr>
			</c:forEach>
	</table>
	<%-- 1.get방식 --%>
	<%--
		<form action="/product/getProductListByCategory?categoryNo=${categoryNo}" method="get">
		
		</form>
	--%>
	<!-- 2. post 방식 -->
	<form id="searchForm" action="${pageContext.request.contextPath}/product/getProductListByCategory" method="get">
		<input type="hidden" value="${categoryNo}" name="categoryNo">
		productName 검색어 : <input type="text" name="searchWord">
		<button id="searchBtn" type="button">검색</button>
	</form>
	<div>
		<!-- 이전....다음.... /product/getProductListByCategory?categoryNo=${categoryNo}&currentPage=-->
		<!-- 검색.... /product/getProductListByCategory -->
	</div>
	<ul class="pagination pagination-sm pagination justify-content-center">
		<c:if test="${currentPage > 1}">
			<a class="page-link" href="${pageContext.request.contextPath}/product/getProductListByCategory?categoryNo=${categoryNo}&currentPage=1">처음</a>
			<a class="page-link" href="${pageContext.request.contextPath}/product/getProductListByCategory?categoryNo=${categoryNo}&currentPage=${currentPage - 1}">이전</a>
		</c:if>
		<c:if test="${startPage > 1}">
			<a class="page-link" href="${pageContext.request.contextPath}/product/getProductListByCategory?categoryNo=${categoryNo}&currentPage=${previousTest}">previousTest</a>	
		</c:if>
		<c:forEach var="page" begin="${startPage}" end="${endPage}" step="1">
			<a class="page-link" href="${pageContext.request.contextPath}/product/getProductListByCategory?categoryNo=${categoryNo}&currentPage=${page}">${page}</a>
		</c:forEach>
		<c:if test="${endPage < totalPage}">
			<a class="page-link" href="${pageContext.request.contextPath}/product/getProductListByCategory?categoryNo=${categoryNo}&currentPage=${currentPage + 1}">다음</a>
			<a class="page-link" href="${pageContext.request.contextPath}/product/getProductListByCategory?categoryNo=${categoryNo}&currentPage=${nextTest}">nextTest</a>
			<a class="page-link" href="${pageContext.request.contextPath}/product/getProductListByCategory?categoryNo=${categoryNo}&currentPage=${totalPage}">끝</a>
		</c:if>
	</ul>
</div>

</body>
</html>