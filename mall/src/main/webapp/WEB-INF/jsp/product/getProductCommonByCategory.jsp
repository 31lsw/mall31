<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="container">
	<h1>Product LIST(spring mvc + mybatis 방식)</h1>
	<table border="1" class="table table-striped">
		<thead>
			<tr>
				<th>productCommonNo</th>
				<th>categoryNo</th>
				<th>productCommonName</th>
				<th>productCommonPrice</th>
				<th>productCommonDescription</th>
				<th>productCommonDate</th>			
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="p">
				<tr>
					<td>${p.productCommonNo}</td>
					<td>${p.categoryNo}</td>
					<td>${p.productCommonName}</td>
					<td>${p.productCommonPrice}</td>
					<td>${p.productCommonDescription}</td>
					<td>${p.productCommonDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="pagination pagination-sm pagination justify-content-center">
		<c:if test="${currentPage > 1}">
			<a class="page-link" href="${pageContext.request.contextPath}/product/getProductCommonByCategory?categoryNo=${categoryNo}&currentPage=1">처음</a>
			<a class="page-link" href="${pageContext.request.contextPath}/product/getProductCommonByCategory?categoryNo=${categoryNo}&currentPage=${currentPage - 1}">이전</a>
		</c:if>
		<c:if test="${startPage > 1}">
			<a class="page-link" href="${pageContext.request.contextPath}/product/getProductCommonByCategory?categoryNo=${categoryNo}&currentPage=${previousTest}">previousTest</a>	
		</c:if>
		<c:forEach var="page" begin="${startPage}" end="${endPage}" step="1">
			<a class="page-link" href="${pageContext.request.contextPath}/product/getProductCommonByCategory?categoryNo=${categoryNo}&currentPage=${page}">${page}</a>
		</c:forEach>
		<c:if test="${endPage < totalPage}">
			<a class="page-link" href="${pageContext.request.contextPath}/product/getProductCommonByCategory?categoryNo=${categoryNo}&currentPage=${currentPage + 1}">다음</a>
			<a class="page-link" href="${pageContext.request.contextPath}/product/getProductCommonByCategory?categoryNo=${categoryNo}&currentPage=${nextTest}">nextTest</a>
			<a class="page-link" href="${pageContext.request.contextPath}/product/getProductCommonByCategory?categoryNo=${categoryNo}&currentPage=${totalPage}">끝</a>
		</c:if>
	</ul>
</div>

</body>
</html>