package cafe.jjdev.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.BoardFile;

@Mapper
public interface BoardFileMapper {
	
	//삭제 대기 파일
	public int updateBoardFileDeleteState(int boardFileNo);
	
	//파일수정
	public int updateBoardFile(BoardFile boardFile);
	
	//파일등록
	public int insertBoardFile(BoardFile boardFile);
	//해당글 첨부된 파일조회
	public List<BoardFile> selectBoardFileByFK(int boardNo);
	//해당글 삭제시 첨부파일 같이 삭제
	public int deleteBoardFileByBoardNo(int boardNo);
	
	
}
