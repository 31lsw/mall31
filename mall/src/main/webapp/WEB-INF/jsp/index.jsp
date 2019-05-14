<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>INDEX</h1>
	<h3>쇼핑몰 메인 페이지(github에서 수정)</h3>
	
	<div>
		<ol>
			<c:if test="${loginMember == null}">
				<li><a href="/member/addMember">회원 가입</a></li>
				<li><a href="/member/login">로그인</a></li>
				<li><a href="/member/findMemberPw">비밀번호 찾기</a></li>
			</c:if>
			<c:if test="${loginMember != null}">
				<li>${loginMember.memberName}(${loginMember.memberId})님 ${loginMember.memberLevel}로 로그인 중</li>
				<li><a href="/member/logout">로그아웃</a></li>
				<li><a href="/member/modifyMemberPw">비밀번호 변경</a></li>
				<li><a href="/member/getMemberInfo">개인정보확인</a></li>
				<li><a href="/member/modifyMember">회원정보 수정</a></li>
				<li><a href="/member/removeMember">회원 탈퇴</a></li>
			</c:if>
		</ol>
	</div>
	<!-- 쇼핑몰 메뉴 -->
	<div>
		<ul>
			<c:forEach var="category" items="${categoryList}">
				<li>
					<%-- <a href="${pageContext.request.contextPath}/product/getProductCommonByCategory?categoryNo=${category.categoryNo}">${category.categoryName}</a> --%>
					<a href="${pageContext.request.contextPath}/product/getProductListByCategory?categoryNo=${category.categoryNo}">${category.categoryName}</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
