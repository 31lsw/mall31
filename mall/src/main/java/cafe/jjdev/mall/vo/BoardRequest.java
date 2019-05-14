package cafe.jjdev.mall.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


// 이런 객체 이름을 BoardRequest, BoardForm, .... 등 다 같은 용도로 씀
// 커맨드 객체의 표준
// 커맨드 객체를 쓰기 위해선 DB내 테이블의 필드명과 이 클래스의 필드명이 일치해야 한다

public class BoardRequest {
	private int boardNo;
	private String boardPw;
	private String boardTitle;
	private String boardContent;
	private String boardUser;
	private String boardDate;
	private List<MultipartFile> boardFileList;
	
	public List<MultipartFile> getBoardFileList() {
		return boardFileList;
	}
	public void setBoardFileList(List<MultipartFile> boardFileList) {
		this.boardFileList = boardFileList;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardPw() {
		return boardPw;
	}
	public void setBoardPw(String boardPw) {
		this.boardPw = boardPw;
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
	
	
}
