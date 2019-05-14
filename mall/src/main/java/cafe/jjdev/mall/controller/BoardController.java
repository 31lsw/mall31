package cafe.jjdev.mall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.commons.PathStr;
//import cafe.jjdev.mall.service.BoardCommentService;
import cafe.jjdev.mall.service.BoardService;
import cafe.jjdev.mall.vo.Board;
import cafe.jjdev.mall.vo.BoardFile;
//import cafe.jjdev.mall.vo.BoardComment;
import cafe.jjdev.mall.vo.BoardRequest;

@Controller
public class BoardController {
	// 경로셋팅 -> 두번째 아이디어
	//final String path = "D:\\jjdev\\sts-workspace\\mall\\src\\main\\webapp\\upload\\"; // 편법
	// 하지만 어디에서든지 쓸수 있기 위해 
	// PathStr에 클래스 멤버 만듦(세번째 아이디어)
	
	@Autowired
	private BoardService boardService;
	
	
	
	//게시글 작성시 작성폼(addBoard.jsp)로 포워딩
	@GetMapping(value="/board/addBoard")
	public String addBoard() {	
		return "/board/addBoard";
	}
	
	//게시글 등록시 작성폼값 받아 등록처리 후 게시글목록 getBoardList.jsp로 리다이렉션
	@PostMapping("/board/addBoard")
	public String addBoard(BoardRequest boardRequest) {
		System.out.println("@PostMapping(\"/board/addBoard\")");
		System.out.println("[Method called] BoardController.addBoard");
		System.out.println("[Method param] boardRequest : "+ boardRequest);
		System.out.println(boardRequest.getBoardFileList().isEmpty() +"<- 파일업로드 리스트.... boardRequest.getBoardFileList()");
		
	/*
			String path_temp = ServletUriComponentsBuilder.fromCurrentContextPath().path("upload").toUriString();
			String contextPath = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
			System.out.println("contextPath : " + contextPath);
			String path = contextPath;
			System.out.println("path : " + path);
			
			// ServletUriComponentBuilder.fromCurrentContextPath()
			// 				== request.getContextPath() : request 객체가 필요함 (옛날 방법)
	 */
		
		// 경로셋팅 -> 첫번째 아이디어 
		//final String path = "D:\\jjdev\\sts-workspace\\mall\\src\\main\\webapp\\upload\\"; // 편법. -> 유효범위를 클래스단위로 넓혀놨음(두번째 아이디어). 클래스 멤버화 함
		//String path = "D:\\jjdev\\sts-workspace\\mall\\src\\main\\resources\\static\\upload\\"; // 나중에 알아보기
		//업로드 경로가 담긴 변수 path를  addBoard메서드 2번째 매개변수 입력값으로 전달
		
		boardService.addBoard(boardRequest, PathStr.UPLOAD_PATH);
												// int result = boardService.addBoard(board); <- 기존코드
												// System.out.println(result + "<- result BoardController.java");
		return "redirect:"+"/board/getBoardList";
		
	}
	
	//게시글 조회시 글조회(getBoard.jsp)통해 글과 댓글을 같이 보여준다
	@GetMapping(value = "/board/getBoard")
	public String getBoardAndCommentList(Model model, @RequestParam (value = "boardNo", required=true) int boardNo) {
		System.out.println("getBoardAndCommentList BoardController.java");
		System.out.println("boardNo : " + boardNo);
		
		//서비스 쪽 메서드 호출해 요청 결과 return받는다
		Map<String, Object> map = boardService.getBoardAndCommentList(boardNo);
		
		model.addAttribute("board", map.get("board"));
		model.addAttribute("boardCommentList", map.get("boardCommentList"));
		model.addAttribute("boardFileList", map.get("boardFileList"));
		model.addAttribute("path", PathStr.UPLOAD_PATH);
		System.out.println(map.get("board") + "<-map.get(\"board\")");
		System.out.println(map.get("boardCommentList") + "<-map.get(\"boardCommentList\")");
		System.out.println(map.get("boardFileList") + "<-map.get(\"boardFileList\")");
		
		return "board/getBoard";
	}
		
	//게시글 삭제시 비번입력폼(removeBoard.jsp)로 포워딩
	@GetMapping("/board/removeBoard")
	public String removeBoard(Model model, @RequestParam(value = "boardNo", required=true) int boardNo) {
		System.out.println(boardNo + " : boardNo");
		model.addAttribute("boardNo", boardNo);
		return "board/removeBoard";
		
	}
	
	//게시글 삭제시 비번입력폼에서 값입력받고 삭제처리
	@PostMapping("/board/removeBoard")
	public String removeBoard(Board board) {
		System.out.println(board.getBoardNo() +"<- boardNo");
		System.out.println(board.getBoardPw() +"<- boardPw");
		boardService.removeBoard(board);
		
		return "redirect:"+"/board/getBoardList";
	
	}
	
	//게시글 수정시 수정폼에 기존글정보 뿌려준다
	@GetMapping("/board/modifyBoard")
	public String modifyBoard(Model model, @RequestParam (value = "boardNo", required=true) int boardNo) {
		System.out.println("@GetMapping(\"/board/modifyBoard\")");
		System.out.println("boardNo : " + boardNo);
		//기존 정보 조회
		Map<String, Object> map = boardService.getBoard(boardNo);
		System.out.println(map + "<- map BoardController.java");
		//리턴값 뷰쪽에서 참조할 수 있도록 셋팅
		model.addAttribute("board", map.get("board"));
		model.addAttribute("boardFileList", map.get("boardFileList"));
		model.addAttribute("path", PathStr.UPLOAD_PATH);
		//포워딩
		return "board/modifyBoard";	
	}
	@GetMapping("/board/modifyBoardFileDeleteState")
	public String modifyBoardFileDeleteState(Model model, BoardFile boardFile) {
		System.out.println("@GetMapping(\"/board/modifyBoardFileDeleteState\")");
		System.out.println("[Param] boardFile: " + boardFile);
		Map<String, Object> map = boardService.modifyBoardFileDeleteState(boardFile);
		System.out.println("[return] map : " + map);
		model.addAttribute("board", map.get("board"));
		model.addAttribute("boardFileList", map.get("boardFileList"));
		model.addAttribute("path", PathStr.UPLOAD_PATH);
		//포워딩
		return "board/modifyBoard";
		
	}
	
	//수정폼 값들 입력받고 수정처리
	@PostMapping("/board/modifyBoard")
	public String modifyBoard(BoardRequest boardRequest, List<BoardFile> boardFileList) {
		System.out.println("@PostMapping(\"/board/modifyBoard\")");
		System.out.println("boardRequest : " + boardRequest);
		System.out.println("boardFileList : " + boardFileList);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardRequest", boardRequest);
		map.put("boardFileList", boardFileList);
		map.put("path", PathStr.UPLOAD_PATH);
		//boardService.modifyBoard(map);
		return "redirect:"+"/board/getBoard?boardNo="+boardRequest.getBoardNo();
	}
	
	//게시글 목록요청시 게시글목록(getBoardList.jsp)로 뿌려준다
	@GetMapping(value = "/board/getBoardList")
	public String getBoardList(Model model, @RequestParam (value = "currentPage", required=false, defaultValue="1") int currentPage) {
		System.out.println("getBoardList BoardController.java");
		System.out.println(currentPage + "<- currentPage <- getBoardList BoardController.java");
		//서비스 쪽으로 메서드 호출받아 요청 처리 return받는다
		Map<String, Object> map = boardService.getBoardList(currentPage);
		System.out.println(map + "<- map(return of getBoardList) BoardController.java");
		model.addAttribute("boardCount", map.get("boardCount"));
		model.addAttribute("list", map.get("list"));
		model.addAttribute("currentPage", map.get("currentPage"));
		model.addAttribute("totalPage", map.get("totalPage"));
		model.addAttribute("startPage", map.get("startPage"));
		model.addAttribute("endPage", map.get("endPage"));
		model.addAttribute("nextTest", map.get("nextTest"));
		model.addAttribute("previousTest", map.get("previousTest"));
		return "board/getBoardList";
	}
	
}