<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑몰 회원가입</title>
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
<!-- 회원 가입에 필요한 정보를 입력받는 jsp 페이지 -->
<section id="joinformArea">
<form name="joinform" method="post" action="${pageContext.request.contextPath}/member/addMember" >
<table>
	<tr>
		<td>
			<h1>회원 가입 페이지</h1>
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
		<td>
			<label for="memberName">이름 : </label>
		</td>
		<td>
			<input type="text" name="memberName" id="memberName">
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberPhone">연락처 : </label>
		</td>
		<td>
			<input type="text" name="memberPhone" id="memberPhone">
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberAddress">주소 : </label>
		</td>
		<td>
			<input type="text" name="memberAddress" id="memberAddress">
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberGender">성별 : </label>
		</td>
		<td>
			<input type="radio" name="memberGender" id="memberGender" value="남" checked="checked">남자 
			<input type="radio" name="memberGender" id="memberGender" value="여">여자 
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberLevel">권한 : 기본을 employee로 잡고 hidden처리함 </label>
		</td>
		<td>
			<input type="hidden" name="memberLevel" id="memberLevel" value="employee" readonly="readonly">
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="javascript:joinform.submit()">회원 가입</a>&nbsp;
			<a href="javascript:joinform.reset()">다시 작성</a>&nbsp;
		</td>
	</tr>
</table>
</form>
</section>

</body>
</html>