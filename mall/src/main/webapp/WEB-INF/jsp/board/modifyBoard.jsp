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
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 
<script>
	
    $(document).ready(function(){
        $('#modifyButton').click(function(){
            if($('#boardPw').val().length <4) {
                alert('boardPw는 4자이상 이어야 합니다');
                $('#boardPw').focus();
            } else if($('#boardTitle').val()=='') {
                alert('boardTitle을 입력하세요');
                $('#boardTitle').focus();
            } else if($('#boardContent').val()=='') {
                alert('boardContent을 입력하세요');
                $('#boardContent').focus();
            } else if($('#boardUser').val()=='') {
                alert('boardUser을 입력하세요');
                $('#boardUser').focus();
            } else {
                $('#modifyForm').submit();
            }
            
        });
        
        $("#addFile").on("click", function(e){ //파일 추가 버튼
			e.preventDefault();
			fn_addFile();
		});
		
		$("a[name='delete']").on("click", function(e){ //삭제 버튼
			e.preventDefault();
			fn_deleteFile($(this));
		});
        
    });
    
    function fn_addFile(){
		var str = "<p><input type='file' name='boardFileList'><a href='#this' class='btn btn-primary btn-xs' name='delete'>파일삭제</a></p>";
		$("#fileDiv").append(str);
		$("a[name='delete']").on("click", function(e){ //삭제 버튼
			e.preventDefault();
			fn_deleteFile($(this));
		});
	}
	
	function fn_deleteFile(obj){
		obj.parent().remove();
	}
</script>

<title>BOARD MODIFY FORM(spring mvc + mybatis 방식)</title>
</head>
<body>
<div class="container">
    <h1>BOARD MODIFY FORM(spring mvc + mybatis 방식)</h1> 
    <form id="modifyForm" action="${pageContext.request.contextPath}/board/modifyBoard?deleteState=1"
    	method="post"
    	enctype="multipart/form-data">
    	
        <div class="form-group">boardNo :
            <input class="form-control" name="boardNo" value="${board.boardNo}" type="text" readonly="readonly"/>
        </div>
        <div class="form-group">
            <label for="boardPw">비밀번호 확인 :</label>
            <input class="form-control" name="boardPw" id="boardPw" type="password"/>
        </div>    
        <div class="form-group">
            <label for="boardTitle">boardTitle :</label>
            <input class="form-control" value="${board.boardTitle}" name="boardTitle" id="boardTitle" type="text"/>
        </div>
        <div class="form-group">
         	<label for="boardTitle">boardContent :</label>
            <textarea class="form-control" id="boardContent" name="boardContent" rows="5" cols="50">${board.boardContent}</textarea>
       </div>
				
		<div id="fileDiv">
			
			<label for="boardFile">boardFile :</label>
			
				<c:forEach items="${boardFileList}" var="bf">
					<c:if test="${bf.boardFileDeleteState == 0}">
						<p>
							<input name="boardNo" id="boardNo" type="hidden" value="${bf.boardNo}" />
							<a href="${path}${bf.boardFileSaveName}.${bf.boardFileExt}" name="boardFileSaveName" id="boardFileSaveName" value="${bf.boardFileSaveName}">${bf.boardFileOriginName}.${bf.boardFileExt}</a>
							<input type="file" name="boardFileList"> <name="deleteState" value="1">
							<a href="${pageContext.request.contextPath}/board/modifyBoardFileDeleteState?boardFileNo=${bf.boardFileNo}&boardNo=${bf.boardNo}">파일삭제 12312</a>
							<%-- <a href="${pageContext.request.contextPath}/board/modifyBoardFileDeleteState?boardFileNo=${bf.boardFileNo}&boardNo=${bf.boardNo}" class="btn btn-primary btn-xs" id="delete" name="delete">파일삭제</a> --%>
						</p>
					</c:if>
				</c:forEach>
		</div>


	</form>
    <div>
    	<a class="btn btn-default" href="#this" id="addFile">파일 추가</a>
        <input class="btn btn-default" id="modifyButton" type="button" value="글수정"/>
        <input class="btn btn-default" type="reset" value="초기화"/>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/board/getBoardList">글목록</a>
    </div>
</div>
</body>
</html>