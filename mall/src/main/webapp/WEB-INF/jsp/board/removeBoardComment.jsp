<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
 
<!-- jquery를 사용하기위한 CDN주소 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<title>BOARD COMMENT REMOVE FORM(spring mvc + mybatis 방식)</title>
</head>
<body>
<div class="container">
<h1>BOARD COMMENT REMOVE FORM(spring mvc + mybatis 방식)</h1>
	<form  class="form-inline" id="removeCommentForm" action="${pageContext.request.contextPath}/board/removeBoardComment" method="post">
		
		<!-- boardCommentNo와 boardNo값 히든처리 -->
	    <input name="boardCommentNo" value="${param.boardCommentNo}" type="hidden"/>
	    <input name="boardNo" value="${param.boardNo}" type="hidden"/>
		
		<%-- 
	    <input name="boardCommentNo" value="${boardComment.boardCommentNo}" type="hidden"/>
	    <input name="boardNo" value="${boardComment.boardNo}" type="hidden"/>
	     --%>
	    
	    <div class="form-group">
	        <label for="boardCommentPw">댓글 비밀번호확인 :</label>
	        <input class="form-control" id="boardCommentPw" name="boardCommentPw" type="password" placeholder="댓글 비번 입력">
	    </div>
	    
	    <div class="form-group">
	        <input class="btn btn-default" id="removeButton" type="submit" value="삭제"/>
	    </div>
	
	</form>
</div>
</body>
</html>