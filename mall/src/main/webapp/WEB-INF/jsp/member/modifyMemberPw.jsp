<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1>회원 비밀번호 수정 폼</h1>
<form name="modifyForm" method="post" action="${pageContext.request.contextPath}/member/modifyMemberPw">
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
			<label for="memberCurrentPw">현재 비밀번호 : </label>
		</td>
		<td>
			<input type="password" name="memberCurrentPw" id="memberCurrentPw">
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberPw">변경할 비밀번호 : </label>
		</td>
		<td>
			<input type="password" name="memberPw" id="memberPw">
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="변경하기">
			<a href="javascript:modifyForm.reset()">다시 작성</a>&nbsp;
		</td>
	</tr>
</table>

</form>

</body>
</html>