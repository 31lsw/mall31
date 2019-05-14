<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑몰 회원정보 수정</title>
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
<section id="modifyformArea">
<form name="modifyform" method="post" action="${pageContext.request.contextPath}/member/modifyMember" >
<table>
	<tr>
		<td>
			<h1>회원정보 수정</h1>
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberId">아이디 : </label>
		</td>
		<td>
			<input type="text" name="memberId" id="memberId" value="${loginMember.memberId}" readonly="readonly">
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberPw">비밀번호 : </label>
		</td>
		<td>
			<input type="password" name="memberPw" id="memberPw" placeholder="비밀번호를 입력해주세요">
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberName">이름 : </label>
		</td>
		<td>
			<input type="text" name="memberName" id="memberName" value="${loginMember.memberName}" readonly="readonly">
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberPhone">연락처 : </label>
		</td>
		<td>
			<input type="text" name="memberPhone" id="memberPhone" value="${member.memberPhone}">
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberAddress">주소 : </label>
		</td>
		<td>
			<input type="text" name="memberAddress" id="memberAddress" value="${member.memberAddress}">
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberGender">성별 : </label>
		</td>
		<td>
			<input type="radio" name="memberGender" id="memberGender" value="남">남자 
			<input type="radio" name="memberGender" id="memberGender" value="여">여자 
		</td>
	</tr>
	<tr>
		<td>
			<label for="memberLevel">권한 : hidden처리 </label>
		</td>
		<td>
			<input type="hidden" name="memberLevel" id="memberLevel" value="${loginMember.memberLevel}" readonly="readonly">
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="javascript:modifyform.submit()">수정 완료</a>&nbsp;
			<a href="javascript:modifyform.reset()">다시 작성</a>&nbsp;
		</td>
	</tr>
</table>
</form>
</section>

</body>
</html>