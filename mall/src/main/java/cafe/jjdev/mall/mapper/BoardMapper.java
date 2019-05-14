package cafe.jjdev.mall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.Board;

@Mapper
public interface BoardMapper {
	
	public int updateBoard(Board board);
	
	public int deleteBoard(Board board);
	
	//게시글 등록
	public int insertBoard(Board board);
	
	
	//게시글 목록
	public List<Board> selectBoardList(Map<String, Integer> map);
	//게시글 총갯수
	public int selectBoardCount();
	//게시글 상세보기
	public Board selectBoard(int boardNo);
	
	
	
	
}


