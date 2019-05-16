package cafe.jjdev.mall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.mall.mapper.ProductCommonMapper;
import cafe.jjdev.mall.vo.Product;
import cafe.jjdev.mall.vo.ProductCommon;

@Service
@Transactional
public class ProductCommonService {
	@Autowired
	private ProductCommonMapper productCommonMapper;
	
	// 색상선택 시 해당색상 사이즈, 재고량 보여주기
	public List<Product> getProductSizeAndStockByProductColorAndProductCommonNo(String productColorSelected, int productCommonNo) {
		System.out.println("ProductCommonService.getProductByProductColorAndProductCommonNo");
		System.out.println("[Param] productColor : " + productColorSelected);
		System.out.println("[Param] productCommonNo : " + productCommonNo);
		
		Product product = new Product();
		product.setProductColor(productColorSelected);
		product.setProductCommonNo(productCommonNo);
		
		return productCommonMapper.selectProductSizeAndStockByProductColorAndProductCommonNo(product);
	}
	
	// 상세보기
	public Map<String, ProductCommon> getProductCommonJoinProduct(int productCommonNo, String productColor){
		System.out.println("ProductCommonService.getProductCommonJoinProductByProductCommonNo");
		System.out.println("[Param] productCommonNo : " + productCommonNo);
		System.out.println("[Param] productColorSelected : " + productColor);
		// return값을 Map으로 가공해서 2개의 값을 보내줘야 됨 : 1. 컬러를 바꿀 상황에서 나타날 옵션 2. 선택컬러에 따라 변경해서 보여줘야 할 사이즈 옵션
		// 가공 후 return용 변수
		Map<String, ProductCommon> resultMap = new HashMap<String, ProductCommon>();
		
		// 메서드 호출 시  매개변수를 다르게 넣고 2번 호출해야 되므로 map을 이용함
		Map<String, Object> map = new HashMap<String, Object>();
		// 공통적으로 보내줘야 할 1번 정보는 productCommonNo만 셋팅해서 메서드 호출
		map.put("productCommonNo", productCommonNo);
		ProductCommon productCommon = productCommonMapper.selectProductCommonJoinProduct(map);
		resultMap.put("productCommon", productCommon);
		
		ProductCommon productCommonWithProductColor = null;
		if(productColor != null) {
			// 선택한 컬러가 있을 경우 2번 정보까지 보내줘야 함
			map.put("productColor", productColor);
			productCommonWithProductColor = productCommonMapper.selectProductCommonJoinProduct(map);
			resultMap.put("productCommonWithProductColor", productCommonWithProductColor);
		}
		
		
		
		return resultMap;
	}
	
	
	// 카테고리별 상품 리스트	
	public Map<String, Object> getProductCommonListByCategory(int categoryNo, int currentPage, String searchWord) {
		System.out.println("ProductCommonService.getProductListByCategory");
		System.out.println(categoryNo + " : categoryNo ProductCommonService");
		System.out.println(currentPage + " : currentPage ProductCommonService");
		System.out.println(searchWord + " : searchWord ProductCommonService");
		
		// 한 페이지에 표시될 게시글 갯수
		final int ROW_PER_PAGE = 10;
		// 게시글의 총 개수  return받는다
		int boardCount = productCommonMapper.selectProductCommonCount(); // 메서드는 전체 게시글 갯수를 return한다
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
		final int startRow = (currentPage - 1) * ROW_PER_PAGE;
		
		// 쿼리를 조회할 메서드 호출 시 매개변수 입력 값으로 사용할 Map객체 생성 후 값을 셋팅한다
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryNo", categoryNo);
		map.put("startRow", startRow);
		map.put("ROW_PER_PAGE", ROW_PER_PAGE);
		map.put("searchWord", "%"+searchWord+"%");
		// 게시글 목록 출력하기 위한 쿼리조회 결과 return받는다
		List<ProductCommon> list = productCommonMapper.selectProductCommonList(map);
		System.out.println(list + "<- list(return of selectProductCommonList) ProductCommonService.java");
		
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
		int previousTest = nextTest - 20; // -9, 1, 11, ...
		
		System.out.println(currentPage + "<- currentPage ProductCommonService.java");
		System.out.println(totalPage + "<- totalPage ProductCommonService.java");
		System.out.println(startPage + "<- startPage ProductCommonService.java");
		System.out.println(endPage + "<- endPage ProductCommonService.java");
		System.out.println(nextTest + "<- nextTest ProductCommonService.java");
		System.out.println(previousTest + "<- previousTest ProductCommonService.java");
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("list", list);
		returnMap.put("currentPage", currentPage);
		returnMap.put("totalPage", totalPage);
		returnMap.put("startPage", startPage);
		returnMap.put("endPage", endPage);
		returnMap.put("nextTest", nextTest);
		returnMap.put("previousTest", previousTest);
		System.out.println(returnMap + "<- returnMap BoardService.java");
		return returnMap;		
	}
	
	
	/*
	// 카테고리별 상품 리스트	
	public Map<String, Object> getProductCommonByCategory(int categoryNo, int currentPage) {
			System.out.println("ProductCommonService.getProductListByCategory");
			System.out.println(categoryNo + " : categoryNo ProductCommonService");
			System.out.println(currentPage + " : currentPage ProductCommonService");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("categoryNo", categoryNo);
			
			// 한 페이지에 표시될 게시글 갯수
			final int ROW_PER_PAGE = 10;
			// 게시글의 총 개수  return받는다
			int boardCount = productCommonMapper.selectProductCommonCount(); // 메서드는 전체 게시글 갯수를 return한다
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
			final int startRow = (currentPage - 1) * ROW_PER_PAGE;
			// 쿼리를 조회할 메서드 호출 시 매개변수 입력 값으로 사용할 Map객체 생성 후 beginRow,rowPerPage 두 값을 셋팅한다
			map.put("startRow", startRow);
			map.put("ROW_PER_PAGE", ROW_PER_PAGE);
			
			// 게시글 목록 출력하기 위한 쿼리조회 결과 return받는다
			List<ProductCommon> list = productCommonMapper.selectProductCommonList(map);
			System.out.println(list + "<- list(return of selectProductCommonList) ProductCommonService.java");
			
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
			int previousTest = nextTest - 20; // -9, 1, 11, ...
			
			System.out.println(currentPage + "<- currentPage ProductCommonService.java");
			System.out.println(totalPage + "<- totalPage ProductCommonService.java");
			System.out.println(startPage + "<- startPage ProductCommonService.java");
			System.out.println(endPage + "<- endPage ProductCommonService.java");
			System.out.println(nextTest + "<- nextTest ProductCommonService.java");
			System.out.println(previousTest + "<- previousTest ProductCommonService.java");
			
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("list", list);
			returnMap.put("currentPage", currentPage);
			returnMap.put("totalPage", totalPage);
			returnMap.put("startPage", startPage);
			returnMap.put("endPage", endPage);
			returnMap.put("nextTest", nextTest);
			returnMap.put("previousTest", previousTest);
			System.out.println(returnMap + "<- returnMap BoardService.java");
			return returnMap;		
	}
	*/
}

