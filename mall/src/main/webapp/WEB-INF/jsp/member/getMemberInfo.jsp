<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 확인</title>
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
<!-- 한 명의 회원 정보를 보여주는 페이지 -->
<section id="memberInfoArea">
<table>
	<tr>
		<td>아이디 : </td>
		<td>${member.memberId}
	</tr>
	<tr>
		<td>이름 : </td>
		<td>${member.memberName}
	</tr>
	<tr>
		<td>연락처 : </td>
		<td>${member.memberPhone}
	</tr>
	<tr>
		<td>주소 : </td>
		<td>${member.memberAddress}
	</tr>
	<tr>
		<td>성별 : </td>
		<td>${member.memberGender}
	</tr>
	<tr>
		<td>권한 : </td>
		<td>${member.memberLevel}
	</tr>
	<tr>
		<td colspan="2">
			<a href="${pageContext.request.contextPath}/">메인 페이지로 이동</a>
			<a href="${pageContext.request.contextPath}/member/modifyMember">회원정보 수정</a>
		</td>
	</tr>
</table>

</section>

</body>
</html>