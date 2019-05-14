package cafe.jjdev.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.BoardComment;

// 댓글 처리에 사용할 매퍼

@Mapper
public interface BoardCommentMapper {
	
	
	//댓글 수정폼에 뿌려줄 해당 댓글 정보를 조회한다
	public BoardComment selectBoardCommentListByPK(int boardCommentNo);
	//수정폼에 입력된 값으로 댓글정보를 수정한다
	public int updateBoardComment(BoardComment boardComment);
	
	// 댓글을 등록한다
	public int insertBoardComment(BoardComment boardComment);
	// 게시글 번호를 통해 해당글에 등록된 댓글들을 조회한다
	public List<BoardComment> selectBoardCommentListByBoardNo(int boardNo);
	
	// 댓글 번호를 통해 특정 댓글만 삭제한다
	public int deleteBoardCommentByCommentNo(int boardCommentNo, String boardCommentPw);
	// 게시글 번호를 통해 해당글에 등록된 모든 댓글을 삭제한다
	public int deleteBoardCommentByBoardNo(int boardNo);
	
	
	
}