package cafe.jjdev.mall.vo;

// 특정 도메인 타입 객체는 데이터베이스의 특정 테이블과 필드 갯수가 일치해야 한다
// 진짜 테이블에 입력할 때 이용하는 객체

public class Board {
	private int boardNo;
	private String boardPw;
	private String boardTitle;
	private String boardContent;
	private String boardUser;
	private String boardDate;
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardUser() {
		return boardUser;
	}
	public void setBoardUser(String boardUser) {
		this.boardUser = boardUser;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public String getBoardPw() {
		return boardPw;
	}
	public void setBoardPw(String boardPw) {
		this.boardPw = boardPw;
	}
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardPw=" + boardPw + ", boardTitle=" + boardTitle + ", boardContent="
				+ boardContent + ", boardUser=" + boardUser + ", boardDate=" + boardDate + "]";
	}
	
	
}
