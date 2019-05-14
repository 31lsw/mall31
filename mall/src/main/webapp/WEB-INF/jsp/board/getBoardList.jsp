<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BOARD LIST(spring mvc + mybatis 방식)</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
	<h1>BOARD LIST(spring mvc + mybatis 방식)</h1>
	<div>전체행의 수 : ${boardCount}</div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>boardNo</th>
				<th>boardTitle</th>
				<th>boardUser</th>
				<th>boardDate</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="b" items="${list}">
				<tr>
					<th>${b.boardNo}</th>
					<td><a href="${pageContext.request.contextPath}/board/getBoard?boardNo=${b.boardNo}">${b.boardTitle}</a></td>
					<td>${b.boardUser}</td>
					<td>${b.boardDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="pagination pagination-sm pagination justify-content-center">
	<%--
		<c:if test="${startPage > 1}">
			<a class="page-link" href="${pageContext.request.contextPath}/board/getBoardList?currentPage=1">처음</a>
		</c:if>
	--%>
		<c:if test="${currentPage > 1}">
			<a class="page-link" href="${pageContext.request.contextPath}/board/getBoardList?currentPage=1">처음</a>
			<a class="page-link" href="${pageContext.request.contextPath}/board/getBoardList?currentPage=${currentPage - 1}">이전</a>
		</c:if>
		<c:if test="${startPage > 1}">
			<a class="page-link" href="${pageContext.request.contextPath}/board/getBoardList?currentPage=${previousTest}">previousTest</a>	
		</c:if>
		<c:forEach var="page" begin="${startPage}" end="${endPage}" step="1">
			<a class="page-link" href="${pageContext.request.contextPath}/board/getBoardList?currentPage=${page}">${page}</a>
		</c:forEach>
		<c:if test="${endPage < totalPage}">
			<a class="page-link" href="${pageContext.request.contextPath}/board/getBoardList?currentPage=${currentPage + 1}">다음</a>
			<a class="page-link" href="${pageContext.request.contextPath}/board/getBoardList?currentPage=${nextTest}">nextTest</a>
			<a class="page-link" href="${pageContext.request.contextPath}/board/getBoardList?currentPage=${totalPage}">끝</a>
		</c:if>
	</ul>
	<div>
		<a class="btn btn-default"
			href="${pageContext.request.contextPath}/board/addBoard">게시글 입력</a>
	</div>
</div>
</body>
</html>