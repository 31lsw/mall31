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
  
<!-- bootstrap javascript소스를 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script type="text/javascript">
	
    $(document).ready(function(){
    //    alert('jquery test');
    /* 입력폼 유효성 관련 요구사항
        1. 모든 폼은 공백 또는 "" 문자는 입력되면 안된다.
        2. 비밀번호는 4자이상 입력하여야 한다.
    */
        $('#addButton').click(function(){
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
                $('#addBoardForm').submit();
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

<title>BOARD ADD(spring mvc + mybatis 방식)</title>
</head>
<body>
<div class="container">
    <h1>BOARD ADD(spring mvc + mybatis 방식)</h1>
    <form id="addBoardForm" action="${pageContext.request.contextPath}/board/addBoard"
    	method="post"
    	enctype="multipart/form-data">
   	<!-- 
    	form태그 enctype속성의 default는 다음과 같다  	
    	enctype="application/x-www-form-urlencoder"
   	-->
        <div class="form-group">
            <label for="boardPw">boardPw :</label>
            <input class="form-control" name="boardPw" id="boardPw" type="password"/>
        </div>
        <div class="form-group">
            <label for="boardPw">boardTitle :</label>
            <input class="form-control" name="boardTitle" id="boardTitle" type="text"/>
        </div>
        <div class="form-group">
            <label for="boardContent">boardContent :</label>
            <textarea class="form-control" name="boardContent" id="boardContent" rows="20" cols="50"></textarea>
        </div>
        <div class="form-group">
            <label for="boardName">boardUser :</label>
            <input class="form-control" name="boardUser" id="boardUser" type="text"/>
        </div>
        <!-- 다중파일 업로드 -->
        <div id="fileDiv">
			<p>
				<label for="boardFile">boardFile :</label>
				<input type="file" multiple="multiple" id="boardFileList" name="boardFileList">
				<a href="#this" class="btn btn-primary btn-xs" id="delete" name="delete">파일삭제</a>
			</p>
		</div>
        <div>
        	<a class="btn btn-default" href="#this" id="addFile">파일 추가</a>
            <input class="btn btn-default" id="addButton" type="button" value="글입력"/>
            <input class="btn btn-default" type="reset" value="초기화"/>
            <a class="btn btn-default" href="${pageContext.request.contextPath}/board/getBoardList">글목록</a>
        </div>
    </form>
</div>
</body>
</html>