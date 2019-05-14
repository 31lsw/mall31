package cafe.jjdev.mall.vo;

	// 댓글 정보를 담는 VO

public class BoardComment {
	// field members
	private int boardCommentNo;
	private int boardNo;
	private String boardCommentPw;
	private String boardCommentContent;
	private String boardCommentUser;
	
	/*
	 * 커맨드 객체 사용 시,
	 * DB 테이블의 필드(칼럼)에 null값을 허용하지 않더라도
	 * VO 변수가 int 타입의 경우 spring boot에서 객체 생성 시
	 * 초기값이 null이 아닌 0으로 잡혀있다.
	 * 따라서 폼에서 자바스크립트를 통해 비번 입력을 강제해야 한다
	 * ex) 위의 field member 만들 때 복붙 실수로 아래와 같은 실수를 하는 경우
	 * private int boardCommentPw; //String 타입인데 int 타입으로 선언하는 실수를 함
	 * BoardComment 객체 생성 시 boardCommentPw(int)의 초기값이 0으로 잡힘
	 * board_comment 테이블의 board_comment_pw(VARCHAR)의 입력값으로 들어갈때
	 * int타입의 숫자값 0이지만 DB에 입력될 때는 VARCHAR타입의 문자값 0으로 입력되는 문제
	 * ( => null 비허용은 null인 것만 막는다. 0은 못막음.. 그냥 DB쪽은 댓글비번이 0으로 그대로 등록됨)
	 * 
	 * 올바르게 타입을 String으로 선언했다면 매퍼쪽에서 INSERT쿼리 실행 시 DB입력 과정에서 다음과 같은 null체크 오류가 떠야 정상이다.
	 * There was an unexpected error (type=Internal Server Error, status=500).
	 * ### Error updating database. Cause: java.sql.SQLIntegrityConstraintViolationException: Column 'board_comment_pw' cannot be null ###
	 * ( => 해당 칼럼은 null값 비허용인데 null값이 들어왔다는 에러)
	 */
	
	// setters and getters
	public int getBoardCommentNo() {
		return boardCommentNo;
	}
	public void setBoardCommentNo(int boardCommentNo) {
		this.boardCommentNo = boardCommentNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardCommentContent() {
		return boardCommentContent;
	}
	public void setBoardCommentContent(String boardCommentContent) {
		this.boardCommentContent = boardCommentContent;
	}
	public String getBoardCommentUser() {
		return boardCommentUser;
	}
	public void setBoardCommentUser(String boardCommentUser) {
		this.boardCommentUser = boardCommentUser;
	}
	public String getBoardCommentPw() {
		return boardCommentPw;
	}
	public void setBoardCommentPw(String boardCommentPw) {
		this.boardCommentPw = boardCommentPw;
	}
	// Override toString
	@Override // for checking an instance(=a object created from this class) on console
	public String toString() {
		return "BoardComment [boardCommentNo=" + boardCommentNo + ", boardNo=" + boardNo + ", boardCommentPw="
				+ boardCommentPw + ", boardCommentContent=" + boardCommentContent + ", boardCommentUser="
				+ boardCommentUser + "]";
	}
		
}
