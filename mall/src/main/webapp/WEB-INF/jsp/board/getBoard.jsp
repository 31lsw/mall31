<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- jquery를 사용하기위한 CDN주소 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  
<!-- bootstrap javascript소스를 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script>
    $(document).ready(function(){
    //    alert('jquery test');
    /* 입력폼 유효성 관련 요구사항
        1. 모든 폼은 공백 또는 "" 문자는 입력되면 안된다.
        2. 비밀번호는 4자이상 입력하여야 한다.
    */
        $('#addButton').click(function(){
            if($('#boardCommentContent').val()=='') {
                alert('boardCommentContent을 입력하세요');
                $('#boardCommentContent').focus();
            } else if($('#boardCommentUser').val()=='') {
                alert('boardCommentUser을 입력하세요');
                $('#boardCommentUser').focus();
            } else if($('#boardCommentPw').val().length <4) {
                alert('boardCommentPw는 4자이상 이어야 합니다');
                $('#boardCommentPw').focus();
            } else {
                $('#addCommentForm').submit();
            }
        });
    });
</script>

<title>BOARD VIEW(spring mvc 방식 + mybatis)</title>
</head>
<body>

<!-- 게시글 출력 -->
<div class="container">
    <h1>BOARD VIEW(spring mvc + mybatis 방식)</h1>
     <table class="table">
         <tbody>
         	<tr>
                <td>board_no :</td>
                <td>${board.boardNo}</td>
            </tr>
            <tr>
                <td>board_title :</td>
                <td>${board.boardTitle}</td>
            </tr>
            <tr>
	            <td>board_content :</td>
	            <td>${board.boardContent}</td>
            </tr>
            <tr>
				<td>board_user :</td>
				<td>${board.boardUser}</td>
			</tr>
            <tr>
	            <td>board_date :</td>
	            <td>${board.boardDate}</td>
            </tr>
                        
            <tr>
            	<td>board_file :</td>
				<td>
					<c:forEach items="${boardFileList}" var="bf">
            		<div>
            			<a class="btn btn-default" 
            				href="${path}${bf.boardFileSaveName}.${bf.boardFileExt}">
            				${bf.boardFileSaveName}.${bf.boardFileExt}
            			</a>
            		</div>
	            	</c:forEach>
				</td>
            </tr>
            
            
        </tbody>
    </table>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/board/modifyBoard?boardNo=${board.boardNo}">수정</a>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/board/removeBoard?boardNo=${board.boardNo}">삭제</a>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/board/getBoardList">글목록</a>
</div>
<hr>
<!-- 댓글 출력 -->
<div class="container">
	<c:forEach items="${boardCommentList}" var="bc">
		<div>
			${bc.boardCommentContent}
			/ 작성자 : ${bc.boardCommentUser}
		</div>
		<div>
			<a class="btn btn-default" href="${pageContext.request.contextPath}/board/removeBoardComment?boardCommentNo=${bc.boardCommentNo}&boardNo=${bc.boardNo}">댓글삭제</a>
			<a class="btn btn-default" href="${pageContext.request.contextPath}/board/modifyBoardComment?boardCommentNo=${bc.boardCommentNo}">댓글수정</a>
		</div>
	</c:forEach>
</div>
<hr>
<!-- 댓글 입력 폼-->
<div class="container">
	<form id="addCommentForm" method="post" action="${pageContext.request.contextPath}/board/addBoardComment">
		<div>
			<%-- <input type="hidden" name="boardNo" value="${board.boardNo}"/> --%>
			<!-- param.필드명 : model에 셋팅된 모든 parameter에서 찾음 -->
			<input type="hidden" name="boardNo" value="${param.boardNo}"/>
		</div>
		<div>
			<textarea name="boardCommentContent" id="boardCommentContent" rows="5" cols="50"></textarea>
		</div>
		<div>
			boardCommentUser :
			<input type="text" name="boardCommentUser" id="boardCommentUser"/>
		</div>
		<div>
			boardCommentPw :
			<input type="password" name="boardCommentPw" id="boardCommentPw"/>
		</div>
		<div>
			<!--
					## (POST) choose one of 3 types in order to submit form ## 
					[1] <input class="btn btn-default" id="addButton" type="button" value="댓글입력"/>
					[2] <button type="submit">댓글입력</button>
					[3] <input class="btn btn-default" type="submit" value="댓글입력">
			-->
			<input class="btn btn-default" id="addButton" type="button" value="댓글입력"/>
			<input class="btn btn-default" type="reset" value="취소"/>
		</div>
	</form>
</div>
<hr>
<br>
</body>
</html>