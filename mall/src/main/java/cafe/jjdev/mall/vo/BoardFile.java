package cafe.jjdev.mall.vo;

public class BoardFile {
	private int boardFileNo;
	
	private int boardNo;
	private String boardFileOriginName;
	private String boardFileSaveName;
	private String boardFileExt;
	private long boardFileSize;
	private String boardFileType;
	private int boardFileDeleteState;
	
	
	//파일 경로를 담는 필드도 추가하는 게 좋다고 생각한다.
	//private String boardFilePath;
	public int getBoardFileNo() {
		return boardFileNo;
	}
	public void setBoardFileNo(int boardFileNo) {
		this.boardFileNo = boardFileNo;
	}
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardFileOriginName() {
		return boardFileOriginName;
	}
	public void setBoardFileOriginName(String boardFileOriginName) {
		this.boardFileOriginName = boardFileOriginName;
	}
	public String getBoardFileSaveName() {
		return boardFileSaveName;
	}
	public void setBoardFileSaveName(String boardFileSaveName) {
		this.boardFileSaveName = boardFileSaveName;
	}
	public String getBoardFileExt() {
		return boardFileExt;
	}
	public void setBoardFileExt(String boardFileExt) {
		this.boardFileExt = boardFileExt;
	}
	public long getBoardFileSize() {
		return boardFileSize;
	}
	public void setBoardFileSize(long boardFileSize) {
		this.boardFileSize = boardFileSize;
	}
	public String getBoardFileType() {
		return boardFileType;
	}
	public void setBoardFileType(String boardFileType) {
		this.boardFileType = boardFileType;
	}
	public int getBoardFileDeleteState() {
		return boardFileDeleteState;
	}
	public void setBoardFileDeleteState(int boardFileDeleteState) {
		this.boardFileDeleteState = boardFileDeleteState;
	}
	@Override
	public String toString() {
		return "BoardFile [boardFileNo=" + boardFileNo + ", boardNo=" + boardNo + ", boardFileOriginName="
				+ boardFileOriginName + ", boardFileSaveName=" + boardFileSaveName + ", boardFileExt=" + boardFileExt
				+ ", boardFileSize=" + boardFileSize + ", boardFileType=" + boardFileType + ", boardFileDeleteState="
				+ boardFileDeleteState + "]";
	}
		
	
}
