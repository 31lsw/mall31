<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<style>
	#findPwformArea{
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
<!-- 회원 가입에 필요한 정보를 입력받는 jsp 페이지 -->
<section id="findPwformArea">
<form name="findPwform" method="post" action="${pageContext.request.contextPath}/member/findMemberPw">
<table>
	<tr>
		<td>
			<h1>비밀번호 찾기</h1>
		</td>
	</tr>
	<tr>
			<td> 회원가입 시 ID/Email 입력</td>
	</tr>
	
	<tr>
		<td>
			<label for="memberId">ID : </label>
		</td>
		<td>
			<input type="text" name="memberId" id="memberId">
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberEmail">Email : </label>
		</td>
		<td>
			<input type="text" name="memberEmail" id="memberEmail">
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="비밀번호 찾기">
			<a href="/">메인 페이지로</a>&nbsp;
		</td>
	</tr>
</table>
</form>
</section>



</body>
</html>