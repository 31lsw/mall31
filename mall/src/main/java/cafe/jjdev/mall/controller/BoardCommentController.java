package cafe.jjdev.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.service.BoardCommentService;
import cafe.jjdev.mall.vo.BoardComment;

// 댓글 처리에 사용될 컨트롤러

@Controller
public class BoardCommentController {
	@Autowired // Dependency Injection
	private BoardCommentService boardCommentService;
	
	
	// 댓글수정 GET요청 처리(댓글 수정폼 modifyBoardComment.jsp로 포워딩 후 기존 댓글정보 뿌려준다)
	@GetMapping("/board/modifyBoardComment")
	public String modifyBoardComment(Model model, @RequestParam (value = "boardCommentNo", required=true) int boardCommentNo) { // BoardComment VO 커맨드 객체통해 값을 전송받는다 (boardCommentNo - 업데이트 쿼리실행때 사용, boardNo- 수정폼으로 hidden처리로 전달되서, 수정 이후 해당글로 리다이렉트시 사용되도록)
		System.out.println("@GetMapping(\"board/modifyBoardComment\"))"); // 무슨 요청인지 확인하는 코드
		System.out.println("[Controller Method called] BoardCommentController.modifyBoardComment] -> GET"); // 어느 메서드가 호출됐는지 확인하는 코드
		System.out.println("[Param input] boardCommentNo : " + boardCommentNo); // 호출된 메서드의 파라미터에 입력된 값 확인
		
		// 댓글 PK통해 기존 댓글 정보 조회 후 boardComment객체에 담는다
		BoardComment boardComment = boardCommentService.getBoardComment(boardCommentNo);
		System.out.println("[RETURN from boardCommentService.getBoardComment] boardComment : " + boardComment + "<- for using VIEW Renderring AFTER setting 'model(Model object)'");
		// model객체에 셋팅
		model.addAttribute("boardComment", boardComment);
		// 댓글 수정폼으로 포워딩 후 기존 정보 뿌려준다
		return "board/modifyCommentBoard";
		
	}
	// 댓글수정 POST요청 처리 (수정폼에서 수정사항 커맨드객체 통해 매개변수에 입력받는다)
	@PostMapping("/board/modifyBoardComment")
	public String modifyBoardComment(BoardComment boardComment) {
		System.out.println("@PostMapping(\"/board/modifyBoardComment\")"); // 무슨 요청인지 확인하는 코드
		System.out.println("[Controller Method called] BoardCommentController.modifyBoardComment] -> POST"); // 어느 메서드가 호출됐는지 확인하는 코드
		System.out.println("[Param input] boardComment : " + boardComment); // 호출된 메서드의 파라미터에 입력된 값 확인
		// 수정정보 담은 boardComment객체를 전달해 수정처리한다 
		boardCommentService.modifyBoardComment(boardComment);
		// 이후 해당 게시글로 리다이렉션
		return "redirect:"+"/board/getBoard?boardNo="+boardComment.getBoardNo();
		
	}
	
	// 댓글등록 POST요청 처리 (폼에서 댓글작성자, 댓글비번, 댓글내용을 입력받는다)
	@PostMapping("/board/addBoardComment")
	public String addBoardComment(BoardComment boardComment) { // 폼의 파라미터명과 일치하는 BoardComment VO 만들어서 커맨드객체를 이용해 값을 전송 받는다
		System.out.println("@PostMapping(\"/board/addBoardComment\")"); // 무슨 요청인지 확인하는 코드
		System.out.println("[Controller Method called] BoardCommentController.addBoardComment]"); // 어느 메서드가 호출됐는지 확인하는 코드
		System.out.println("[Param input] boardComment : " + boardComment); // 호출된 메서드의 파라미터에 입력된 값 확인
		
		boardCommentService.addBoardComment(boardComment); // 서비스쪽 메서드로 요청받은 정보 전달한다
		return "redirect:"+"/board/getBoard?boardNo="+boardComment.getBoardNo(); // 댓글 등록 처리후 요청을 끝냈으니 리다이렉트 처리를 통해 댓글이 등록된 해당글을 보여준다(이 응답을 통해 클라이언트는 먼저한 컨트롤러로 또다른 요청하는 흐름)
	}
	
	//댓글 삭제 GET요청 처리(댓글 비번 입력폼removeBoardComment.jsp로 포워딩)
	@GetMapping("/board/removeBoardComment")
	public String removeBoardComment(Model model, BoardComment boardComment) {
		System.out.println("@GetMapping(\"/board/removeBoardComment\")");
		System.out.println("[Controller Method called] BoardCommentController.removeBoardComment -> GET");
		System.out.println("[Param input] boardComment : " + boardComment);
		System.out.println("[Param input] model : " + model);
		
		model.addAttribute("boardComment", boardComment);
		
		return "board/removeBoardComment";
	}

	// 댓글삭제 비번확인폼 POST
	@PostMapping("/board/removeBoardComment")
	public String removeBoardComment(BoardComment boardComment) {
		System.out.println("@PostMapping(\"/board/removeBoardComment\")");
		System.out.println("[Controller Method called] BoardCommentController.removeBoardComment -> POST");
		boardCommentService.removeBoardComment(boardComment);
		return "redirect:"+"/board/getBoard?boardNo="+boardComment.getBoardNo();
	}
		
}
