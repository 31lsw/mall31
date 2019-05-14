<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 
<script>
    $(document).ready(function(){
        $('#modifyButton').click(function(){
            if($('#boardCommentPw').val().length <4) {
                alert('boardCommentPw는 4자이상 이어야 합니다');
                $('#boardCommentPw').focus();
            } else if($('#boardCommentContent').val()=='') {
                alert('boardCommentContent을 입력하세요');
                $('#boardCommentContent').focus();
            } else if($('#boardCommentUser').val()=='') {
                alert('boardCommentUser을 입력하세요');
                $('#boardCommentUser').focus();
            } else {
                $('#modifyForm').submit();
            }
        });
    });
</script>
<title>BOARD MODIFY COMMENT FORM(spring mvc + mybatis 방식)</title>
</head>
<body>
<div class="container">
    <h1>BOARD MODIFY COMMENT FORM(spring mvc + mybatis 방식)</h1> 
    <form id="modifyForm" action="${pageContext.request.contextPath}/board/modifyBoardComment" method="post">
    	<!-- boardCommentNo(update쿼리용),boardNo(수정처리후 redirect용)는 hidden으로 처리해 넘겨준다 -->
        <div class="form-group">
            <input class="form-control" name="boardCommentNo" value="${boardComment.boardCommentNo}" type="hidden" readonly="readonly"/>
            <input class="form-control" name="boardNo" value="${boardComment.boardNo}" type="hidden" readonly="readonly"/>
        </div>
        <div class="form-group">
            <label for="boardCommentPw">비밀번호 확인 :</label>
            <input class="form-control" name="boardCommentPw" id="boardCommentPw" type="password"/>
        </div>    
        <div class="form-group">
            <label for="boardCommentUser">boardCommentUser :</label>
            <input class="form-control" value="${boardComment.boardCommentUser}" name="boardCommentUser" id="boardCommentUser" type="text"/>
        </div>
        <div class="form-group">
        	<label for="boardCommentUser">boardCommentContent :</label>
            <textarea class="form-control" id="boardCommentContent" name="boardCommentContent" rows="5" cols="50">${boardComment.boardCommentContent}</textarea>
        </div>
        <div>
            <input class="btn btn-default" id="modifyButton" type="button" value="댓글수정"/>
            <input class="btn btn-default" type="reset" value="초기화"/>
        </div>
    </form>
</div>
</body>
</html>