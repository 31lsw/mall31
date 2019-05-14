package cafe.jjdev.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.mall.mapper.BoardCommentMapper;
import cafe.jjdev.mall.vo.BoardComment;

// 댓글 처리에 사용할 서비스

@Service
@Transactional
public class BoardCommentService {
	@Autowired // Dependency Injection
	private BoardCommentMapper boardCommentMapper;
	
	
	// 댓글수정 STEP01: board_comment 테이블의 PK로 댓글 정보 조회 후 결과를 수정폼으로 렌더링 (댓글 수정폼에 기존 정보 조회 후 뿌려주도록)
	public BoardComment getBoardComment(int boardCommentNo) {
		System.out.println("[Service Method called] BoardCommentService.getBoardComment]"); // 어느 메서드가 호출됐는지 확인하는 코드
		System.out.println("[Param input] boardComment : " + boardCommentNo); // 호출된 메서드의 파라미터에 입력된 값 확인
		// 조회결과 return해준다
		return boardCommentMapper.selectBoardCommentListByPK(boardCommentNo);
	}
	
	// 댓글수정 STEP02: 수정폼 값들 입력받아 수정처리
	public void modifyBoardComment(BoardComment boardComment) { //BoardComment 커맨드 객체 통해 파라미터 입력값(boardCommentNo, boardCommentPw)전달받는다
		System.out.println("[Service Method called] BoardCommentService.modifyBoardComment]"); // 어느 메서드가 호출됐는지 확인하는 코드
		System.out.println("[Param input] boardComment : " + boardComment); // 호출된 메서드의 파라미터에 입력된 값 확인
		
		int updateBoardCommentResult = boardCommentMapper.updateBoardComment(boardComment);
		System.out.println(updateBoardCommentResult + " : 댓글수정 쿼리실행 결과");
	}
	
	// 댓글 등록
	public void addBoardComment(BoardComment boardComment) {
		System.out.println("[Service Method called] BoardCommentService.addBoardComment]"); // 어느 메서드가 호출됐는지 확인하는 코드
		System.out.println("[Param input] boardComment : " + boardComment); // 호출된 메서드의 파라미터에 입력된 값 확인
		
		int insertBoardComment = boardCommentMapper.insertBoardComment(boardComment);
		System.out.println(insertBoardComment + " : 댓글등록 쿼리실행 결과");
	}
	
	// 댓글 삭제
	public void removeBoardComment(BoardComment boardComment) {
		System.out.println("[Service Method called] BoardCommentService.removeBoardComment]"); // 어느 메서드가 호출됐는지 확인하는 코드
		System.out.println("[Param input] boardComment : " + boardComment); // 호출된 메서드의 파라미터에 입력된 값 확인
		//개별 댓글 삭제
		int deleteBoardCommentResult = boardCommentMapper.deleteBoardCommentByCommentNo(boardComment.getBoardCommentNo(), boardComment.getBoardCommentPw());
		System.out.println(deleteBoardCommentResult + " : 댓글삭제 쿼리실행 결과");
	}
	

}
