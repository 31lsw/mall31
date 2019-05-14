<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
</head>
<body>


<h1>회원 탈퇴 폼</h1>
<form name="modifyForm" method="post" action="${pageContext.request.contextPath}/member/removeMember">
<table>
	<tr>
		<td>
			<h3>비밀번호 수정</h3>
		</td>
	</tr>
	<tr>
		<!-- memberId는 hidden처리해서 넘겨준다 -->
		<td>	
			<input type="hidden" name="memberId" id="memberId" value="${loginMember.memberId}" readonly="readonly">
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberPw">계정 비밀번호 : </label>
		</td>
		<td>
			<input type="password" name="memberPw" id="memberPw" placeholder="비밀번호를 입력해주세요">
		</td>
	</tr>
	<tr>
		<td> "회원 탈퇴에 동의하십니까?" </td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="동의">
			<a href="/index">메인 페이지로 이동</a>&nbsp;
		</td>
	</tr>
</table>
</form>

</body>
</html>