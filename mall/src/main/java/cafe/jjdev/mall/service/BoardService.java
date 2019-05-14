package cafe.jjdev.mall.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cafe.jjdev.mall.commons.PathStr;
import cafe.jjdev.mall.mapper.BoardCommentMapper;
import cafe.jjdev.mall.mapper.BoardFileMapper;
import cafe.jjdev.mall.mapper.BoardMapper;
import cafe.jjdev.mall.vo.Board;
import cafe.jjdev.mall.vo.BoardComment;
import cafe.jjdev.mall.vo.BoardFile;
import cafe.jjdev.mall.vo.BoardRequest;

@Service
@Transactional
public class BoardService {
	@Autowired private BoardMapper boardMapper;
	@Autowired private BoardCommentMapper boardCommentMapper;
	@Autowired private BoardFileMapper boardFileMapper;
	
	// add, modify, get, remove
	
	
	
	// 수정폼에서 삭제대기 파일처리
	public Map<String, Object> modifyBoardFileDeleteState(BoardFile boardFile) {
		System.out.println("BoardService.modifyBoardFileDeleteState");
		System.out.println("boardFile : " + boardFile);
		Map<String, Object> map = null;
		// 게시글 정보 조회
		Board board = boardMapper.selectBoard(boardFile.getBoardNo());
		System.out.println("board : " + board);
		int 바꼈냐안바꼈냐 = boardFileMapper.updateBoardFileDeleteState(boardFile.getBoardFileNo());
		System.out.println(바꼈냐안바꼈냐 + "<- updateBoardFileDeleteState 결과");
		// 게시글 첨부 파일 조회
		List<BoardFile> boardFileList = boardFileMapper.selectBoardFileByFK(boardFile.getBoardNo());
		System.out.println("boardFileList : " + boardFileList);
		// 셋팅
		map = new HashMap<String, Object>();
		map.put("board", board);
		map.put("boardFileList", boardFileList);
		
		// 리턴
		return map;
	}
	
	
	
	
	
	// getBoard.jsp
	public Map<String, Object> getBoardAndCommentList(int boardNo) {
		System.out.println("getBoardAndCommentList BoardService.java");
		
		// 1. 게시글 정보
		Board board = boardMapper.selectBoard(boardNo);
		
		// 2. 댓글 정보
		List<BoardComment> boardCommentList = boardCommentMapper.selectBoardCommentListByBoardNo(boardNo);
		
		// 3. 파일 정보
		List<BoardFile> boardFileList = boardFileMapper.selectBoardFileByFK(boardNo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board", board);
		map.put("boardCommentList", boardCommentList);
		map.put("boardFileList", boardFileList);
		// 기존 코드 : map.put("boardFile", boardFile); // 또는 map.put("boardFile", boardFile)의 null의 유무에 따라 if로 처리분기를 나눈다
		
		return map;
	}
	
	// modifyBoard.jsp 수정폼에 뿌려줄 해당글 정보 가져올 때
	public Map<String, Object> getBoard(int boardNo) {
		System.out.println("getBoard BoardService.java");
		System.out.println("boardNo : " + boardNo);
		Map<String, Object> map = null;
		// 게시글 정보 조회
		Board board = boardMapper.selectBoard(boardNo);
		System.out.println("board : " + board);
		// 게시글 첨부 파일 조회
		List<BoardFile> boardFileList = boardFileMapper.selectBoardFileByFK(boardNo);
		System.out.println("boardFileList : " + boardFileList);
		// 셋팅
		map = new HashMap<String, Object>();
		map.put("board", board);
		map.put("boardFileList", boardFileList);
		// 리턴
		return map;
		
	}
	
	// [미완] modifyBoard.jsp 수정처리
	public void modifyBoard(Map<String, Object> map) {
		System.out.println("modifyBoard BoardService.java");
		BoardRequest boardRequest = (BoardRequest) map.get("boardRequest");
		List<BoardFile> boardFileList = (List<BoardFile>) map.get("boardFileList");
		String path = (String) map.get("path");
		//게시글 업데이트 쿼리
		Board board = new Board();
		board.setBoardPw(boardRequest.getBoardPw());
		board.setBoardTitle(boardRequest.getBoardTitle());
		board.setBoardContent(boardRequest.getBoardContent());
		board.setBoardUser(boardRequest.getBoardUser());
		int result = boardMapper.updateBoard(board);
		System.out.println("result :" + result);
		// 실제 파일 삭제
		deleteFile(boardFileList);
		// 파일 정보 업데이트
				
		List<MultipartFile> multipartFileList = boardRequest.getBoardFileList();
		System.out.println("[BoardService.modifyBoard] multipartFileList : " + multipartFileList);
		for(MultipartFile multipartFile : multipartFileList) {
			if(multipartFile.getSize() > 0) {
				String originalFileName = multipartFile.getOriginalFilename();
				String contentType = multipartFile.getContentType();
				String name = multipartFile.getName();
				long size = multipartFile.getSize();
				System.out.println("originalFileName:"+originalFileName);
				System.out.println("contentType:"+contentType);
				System.out.println("name:"+name);
				System.out.println("size:"+size);
								
				// abc.hwp -> a:0, b:1, ...
				int i = originalFileName.lastIndexOf("."); // 마지막 .값 인덱스 위치를 찾는다
				String originName = originalFileName.substring(0, i);
				String ext = originalFileName.substring(i+1).toLowerCase(); // toLowerCase() : DB에 들어갈 때의 문자열을 '소문자'로 하기위해서 (MySQL은 대소문자를 구분한다)
				//... 아직 파일의 나머지 다른 부분은 채울(setting)할 수 없다
				UUID uuid = UUID.randomUUID(); // 여기까지는 random UUID지 문자열 값은 아니다
				String saveName = uuid.toString().replace("-", ""); //UUID 중간에 생기는 -가 싫으면 : .replace("-", "") 를 추가한다
				// String saveName = originName + "_" + System.currentTimeMillis();
				
				BoardFile boardFile = new BoardFile();
				boardFile.setBoardFileSize(multipartFile.getSize());
				boardFile.setBoardFileType(multipartFile.getContentType());
				boardFile.setBoardFileOriginName(originName);
				boardFile.setBoardFileSaveName(saveName);
				boardFile.setBoardFileExt(ext);
				boardFile.setBoardNo(board.getBoardNo()); // *selectKey : 앞에서 게시물 저장 시 auto increment로 생성된 boardNo값 가져온다  
				System.out.println(boardFile +" : boardFile");
				
				// 3. 폴더에 저장  : 테이블에 저장하지 않고, 서버폴더에 저장한다. 파일때문에 테이블이 너무 커지니까!
				//다른 방법도 많지만 bean파일 생성해 이용할 거다. saveName의 이름으로 bean파일을 만든 후 확장자도 ext로 써서 파일저장!
				// src/main/resource 하위의 static 폴더에 저장
				//String path = ServletUriComponentsBuilder.fromCurrentContextPath().path("upload").toUriString();
		        //System.out.println(path);
				//String path = "D:\\jjdev\\sts-workspace\\mall\\src\\main\\resources\\static\\";
				//String path = "c:\\temp\\"; // c:/temp/ (-> 스프링에서는 이렇게 적어도 "c:\\temp\"(윈도우 경로방식)로 스스로 역슬래쉬 처리해준다
				File file = new File(path+saveName+"."+ext); //새로운 bean 파일 만들거다. 이름이 들어가고 확장자가 들어간다
				try {
					multipartFile.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(); // 파일이동 예외발생 시 런타임 익셉션을 발생시킨다 -> transaction처리를 위해
					//원래는 파일전송 시 예외처리를 할 수 있도록 `사용자 정의 예외`를 스스로 만들어서 쓰는 게 맞다.(물론 그 예외의 부모는 RuntimeException로 상속)
				}
				int result2 = boardFileMapper.updateBoardFile(boardFile);
				System.out.println("result2 :" + result);
			}	
		}
		
		
		/*
		 * List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(map, req);
		 * Map<String,Object> tempMap = null; for(int i = 0; i<list.size();i++) {
		 * tempMap = list.get(i);
		 * 
		 * if(tempMap.get("IS_NEW").equals("Y")) { sampleDAO.insertFile(tempMap); }else
		 * { sampleDAO.updateFile(tempMap); } }
		 */ 
	}
	
	// addBoard.jsp
	public void addBoard(BoardRequest boardRequest, String path) {
		System.out.println("[Method Called] BoardService.addBoard");
		System.out.println(boardRequest + " : boardRequest [BoardService.addBoard 1stParam]");
		System.out.println(path + " : path [BoardService.addBoard 2ndParam]");
		// 예외처리 : 런타임 익셉션 (unchecked exception으로...) -> transaction 처리를 위해 try,catch 예외처리를 하지 않는다
		// boardRequest의 값을 2개로 분리해서 이용함 -> board(게시판DB영), boardFile(파일DB용)로 재셋팅 해서 2개의 메서드 호출 시 각각 이용
		
		// 1. BoardRequest -> Board
		Board board = new Board();
		//... 나머지 채우고
		board.setBoardPw(boardRequest.getBoardPw());
		board.setBoardTitle(boardRequest.getBoardTitle());
		board.setBoardContent(boardRequest.getBoardContent());
		board.setBoardUser(boardRequest.getBoardUser());
		System.out.println("[BoardService.addBoard] board : " + board);
		
		// 게시물 저장
		int result = boardMapper.insertBoard(board); // board.setBoardNo(ai;auto increment값);
		System.out.println(result + " : result insertBoard 결과");
		
		// 위 코드에서 글이 insert되는데 여기서 boardNo을 알 방법이 없다
		//board객체 셋팅된 정보로 insert쿼리를 날리고 나서 결과를 다시 board객체에 넣어주세요. 그 객체통해 boardNo를 get하고 그 값은 아래 boardFile.setBoardNo의 매개변수에 이용한다
		//insert쿼리 뒤에 방금 insert된 키값을 받을 수 있다 (문법적 이슈)
		//필요에 따라 vo타입은 여러개 만들 수 있다 -> 스스로 패키지를 나눠서 저장을 해야겠다는 아이디어
				
		// 2. BoardRequest -> MultipartFile -> BoardFile	
		// 업로드 파일 리스트
		List<MultipartFile> multipartFileList = boardRequest.getBoardFileList();
		System.out.println("[BoardService.addBoard] multipartFileList : " + multipartFileList);
		for(MultipartFile multipartFile : multipartFileList) {
			if(multipartFile.getSize() > 0) {
				String originalFileName = multipartFile.getOriginalFilename();
				String contentType = multipartFile.getContentType();
				String name = multipartFile.getName();
				long size = multipartFile.getSize();
				System.out.println("originalFileName:"+originalFileName);
				System.out.println("contentType:"+contentType);
				System.out.println("name:"+name);
				System.out.println("size:"+size);
								
				// abc.hwp -> a:0, b:1, ...
				int i = originalFileName.lastIndexOf("."); // 마지막 .값 인덱스 위치를 찾는다
				String originName = originalFileName.substring(0, i);
				String ext = originalFileName.substring(i+1).toLowerCase(); // toLowerCase() : DB에 들어갈 때의 문자열을 '소문자'로 하기위해서 (MySQL은 대소문자를 구분한다)
				//... 아직 파일의 나머지 다른 부분은 채울(setting)할 수 없다
				UUID uuid = UUID.randomUUID(); // 여기까지는 random UUID지 문자열 값은 아니다
				String saveName = uuid.toString().replace("-", ""); //UUID 중간에 생기는 -가 싫으면 : .replace("-", "") 를 추가한다
				// String saveName = originName + "_" + System.currentTimeMillis();
				
				BoardFile boardFile = new BoardFile();
				boardFile.setBoardFileSize(multipartFile.getSize());
				boardFile.setBoardFileType(multipartFile.getContentType());
				boardFile.setBoardFileOriginName(originName);
				boardFile.setBoardFileSaveName(saveName);
				boardFile.setBoardFileExt(ext);
				boardFile.setBoardNo(board.getBoardNo()); // *selectKey : 앞에서 게시물 저장 시 auto increment로 생성된 boardNo값 가져온다  
				System.out.println(boardFile +" : boardFile");
				
				// 3. 폴더에 저장  : 테이블에 저장하지 않고, 서버폴더에 저장한다. 파일때문에 테이블이 너무 커지니까!
				//다른 방법도 많지만 bean파일 생성해 이용할 거다. saveName의 이름으로 bean파일을 만든 후 확장자도 ext로 써서 파일저장!
				// src/main/resource 하위의 static 폴더에 저장
				//String path = ServletUriComponentsBuilder.fromCurrentContextPath().path("upload").toUriString();
		        //System.out.println(path);
				//String path = "D:\\jjdev\\sts-workspace\\mall\\src\\main\\resources\\static\\";
				//String path = "c:\\temp\\"; // c:/temp/ (-> 스프링에서는 이렇게 적어도 "c:\\temp\"(윈도우 경로방식)로 스스로 역슬래쉬 처리해준다
				File file = new File(path+saveName+"."+ext); //새로운 bean 파일 만들거다. 이름이 들어가고 확장자가 들어간다
				try {
					multipartFile.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(); // 파일이동 예외발생 시 런타임 익셉션을 발생시킨다 -> transaction처리를 위해
					//원래는 파일전송 시 예외처리를 할 수 있도록 `사용자 정의 예외`를 스스로 만들어서 쓰는 게 맞다.(물론 그 예외의 부모는 RuntimeException로 상속)
				}
				boardFileMapper.insertBoardFile(boardFile);
			}	
		}
		
	}
		
	// removeBoard.jsp
	public void removeBoard(Board board) {
		System.out.println("removeBoard BoardService.java");
		System.out.println(board.getBoardNo() + "<- boardNo");
		System.out.println(board.getBoardPw() + "<- boardPw");
		
		/*
			1. 댓글 테이블 삭제 SQLException -> @Mapper -> RuntimeException
			2. 파일 삭제 File.delete() -> SecurityException(*부모가 RuntimeException)
			3. 파일 테이블 삭제 SQLException -> @Mapper -> RuntimeException
			4. 보드 테이블 삭제 SQLException -> @Mapper -> RuntimeException
		*/
		
		// 댓글DB정보+실제첨부파일+파일DB정보가 삭제되야 해당 게시글이 삭제됨(FK로 Restrict로 잡혀있으니까)
		// 1. 게시글에 달린 모든 댓글 삭제
		int commentResult = boardCommentMapper.deleteBoardCommentByBoardNo(board.getBoardNo());
		
		// 2. 게시글에 달린 모든 업로드 파일 삭제 이후 -> 3. 업로드 파일 DB 삭제 순으로 처리해야됨 : 메서드 처리
		if(boardFileMapper.selectBoardFileByFK(board.getBoardNo()) != null) {
			List<BoardFile> fileList = boardFileMapper.selectBoardFileByFK(board.getBoardNo());
			deleteFile(fileList); //실제파일 삭제처리 메서드 .. 아래에 만듦 
			deleteFileResult(board); //파일DB 삭제처리 메서드 .. 아래에 만듦
		}
		// 4. 해당 게시글 삭제
		int boardResult = boardMapper.deleteBoard(board);
	}
	// 실제파일 삭제 메서드 (해당 파일 삭제)
	public void deleteFile(List<BoardFile> fileList) {
		System.out.println(fileList + " :  fileList ");
		for(BoardFile boardFile : fileList) {
			// 콘솔 확인
			System.out.println(boardFile.getBoardFileSaveName() + " : getBoardFileSaveName()");
			System.out.println(boardFile.getBoardFileExt() + " : getBoardFileExt()");
			
			// 실제 파일 삭제후 해당파일 DB가 삭제되야됨
			File file = new File(
					PathStr.UPLOAD_PATH + boardFile.getBoardFileSaveName() + "." + boardFile.getBoardFileExt());
	
			if (file.exists()) { // 파일존재여부확인
				if (file.delete()) { //파일삭제
					System.out.println("파일삭제 성공");
				} else {
					System.out.println("파일삭제 실패");
				}
				
			} else {
				System.out.println("파일이 존재하지 않습니다.");
			}
		}
		
	}
	// 해당파일 DB 삭제 메서드 (파일 테이블 삭제) 
	public int deleteFileResult(Board board) {	
		int deleteFileResult = boardFileMapper.deleteBoardFileByBoardNo(board.getBoardNo());
		return deleteFileResult;
	}
		
	// getBoardList.jsp
	public Map<String, Object> getBoardList(int currentPage){
		System.out.println("getBoardList BoardService.java");
		System.out.println(currentPage + "<- currentPage getBoardList BoardService.java");
		
		// 한 페이지에 표시될 게시글 갯수
		final int ROW_PER_PAGE = 10;
		// 게시글의 총 개수  return받는다
		int boardCount = boardMapper.selectBoardCount(); // 메서드는 전체 게시글 갯수를 return한다
		System.out.println(boardCount + "<- 전체 게시글 갯수 BoardService.java"); // 콘솔 확인
		// 게시판 전체 페이지(totalPage) 구하기 : 현재 페이지 기준에서 다음/마지막 버튼 표시 유무를 구분하기 위함
		int totalPage = (int) Math.ceil((double) boardCount / ROW_PER_PAGE);
		// currentPage는 totalPage를 초과할 수 없다 또한 적어도 1이상이어야 한다
		if(currentPage > totalPage) {
			currentPage = totalPage; //currentPage의 최대값은 totalPage까지(임계치)
		}else if(currentPage < 1) {
			currentPage = 1; //currentPage의 최소값은 1
		}
		
		// 처음 표시할 게시글 row위치
		final int beginRow = (currentPage - 1) * ROW_PER_PAGE;
		// 쿼리를 조회할 메서드 호출 시 매개변수 입력 값으로 사용할 Map객체 생성 후 beginRow,rowPerPage 두 값을 셋팅한다
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", ROW_PER_PAGE);
		// 게시글 목록 출력하기 위한 쿼리조회 결과 return받는다
		List<Board> list = boardMapper.selectBoardList(map);
		System.out.println(list + "<- list(return of selectBoardList) BoardService.java");
		
		// 하단 넘버링이 시작될 번호(startPage) 구하기
		int startPage = ((currentPage - 1) / 10) * 10 + 1; // 1, 11, 21, ...
		
		// 한 페이지당 하단 표시될 넘버링 갯수(numsPerPage)
		//final int numsPerPage = 10; // 1~10, 11~20, 21~30 처럼 10개씩 표시
		
		// 하단 넘버링이 끝나는 번호(endPage) 구하기
		int endPage = startPage + 9; // 10, 20, 30, ...
		//int endPage = startPage - 1 + numsPerPage; // 10, 20, 30, ...
		
		// endPage는 totalPage를 초과할 수 없다
		if(endPage > totalPage) {
			endPage = totalPage; //endPage의 임계치는 totalPage까지
		}
		
		// test.... previous, next
		int nextTest = endPage + 1; // 11, 21, 31, ...
		int previousTest = endPage - 9; // 1, 11, 21, ...
		
		System.out.println(currentPage + "<- currentPage BoardService.java");
		System.out.println(totalPage + "<- totalPage BoardService.java");
		System.out.println(startPage + "<- startPage BoardService.java");
		System.out.println(endPage + "<- endPage BoardService.java");
		System.out.println(nextTest + "<- nextTest BoardService.java");
		System.out.println(previousTest + "<- previousTest BoardService.java");
		
		// Controller 에 return 값: VIEW에서 필요한 요소는? 게시글 데이터(list), 게시글 갯수(boardCount), 페이징 요소(startPage, endPage, ...)
		
		//하나의 data type만 return이 가능하므로 서로 다른 type인 두 return 값을 Map객체에 셋팅하자
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("boardCount", boardCount);
		returnMap.put("list", list);
		returnMap.put("currentPage", currentPage);
		returnMap.put("totalPage", totalPage);
		returnMap.put("startPage", startPage);
		returnMap.put("endPage", endPage);
		returnMap.put("nextTest", nextTest);
		returnMap.put("previousTest", previousTest);
		System.out.println(returnMap + "<- returnMap BoardService.java");
		
		//셋팅된 Map객체 return		
		return returnMap;
	}
	
}