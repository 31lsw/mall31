<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<style>
	#loginFormArea{
		margin : auto;
		width : 400px;
		border : 1px solid gray;
	}
	table{
		width : 380px;
		margin : auto;
		text-align : center;
	}	
</style>
</head>
<body>
<section id="loginFormArea">
<form name="loginform" action="${pageContext.request.contextPath}/member/login" method="post">
<h1>LOGIN</h1>
<table>
	<tr>
		<td colspan="2">
			<h3>쇼핑몰 로그인 페이지</h3>
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberId">아이디 : </label>
		</td>
		<td>
			<input type="text" name="memberId" id="memberId">
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberPw">비밀번호 : </label>
		</td>
		<td>
			<input type="password" name="memberPw" id="memberPw">
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="javascript:loginform.submit()">로그인</a>&nbsp;&nbsp;
			<a href="#this">회원 가입</a>
		</td>
	</tr>
</table>
</form>
</section>
	
	<%-- 
	선생님 기본 코드
	<div>
		<ol>
			<c:if test="${loginMember == null}">
				<li><a href="/member/login">로그인</a></li>
			</c:if>
			<c:if test="${loginMember != null}">
				<li><a href="/member/logout">로그아웃</a></li>
			</c:if>
		</ol>
	</div>
	 --%>
	
</body>
</html>