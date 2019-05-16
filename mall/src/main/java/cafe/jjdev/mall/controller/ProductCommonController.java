package cafe.jjdev.mall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.service.ProductCommonService;
import cafe.jjdev.mall.vo.Product;
import cafe.jjdev.mall.vo.ProductCommon;

@Controller
public class ProductCommonController {
	@Autowired
	ProductCommonService productCommonService;
	
	
	// 주문페이지
	@GetMapping("/product/getProductCommonJoinProductByProductCommonNo")
	public String getProductCommonJoinProduct(Model model
															//,Product product
															,@RequestParam(value = "productCommonNo") int productCommonNo
															,@RequestParam(value = "productColor", required=false) String productColor) {
		// 매개변수 입력값 확인
		System.out.println("ProductCommonController.getProductCommonJoinProduct[GET]");
		System.out.println("productCommonNo : " + productCommonNo);
		System.out.println("productColorSelected : " + productColor); //선택 컬러
		
		// 상품조회
		Map<String, ProductCommon> map = productCommonService.getProductCommonJoinProduct(productCommonNo, productColor); //productCommonNo, productColor
		ProductCommon productCommonWithProductColor = map.get("productCommonWithProductColor");
		ProductCommon productCommon = map.get("productCommon");
		
		// 선택한 컬러가 경우가 있을 경우, 선택 컬러에 대한 사이즈와 재고 조회
		List<Product> resultProductSizeAndStock = null;
		if(productColor != null) {		
			resultProductSizeAndStock = productCommonService.getProductSizeAndStockByProductColorAndProductCommonNo(productColor, productCommonNo);
			model.addAttribute("productColorSelected", productColor); // 선택 컬러
			model.addAttribute("resultProductSizeAndStock", resultProductSizeAndStock); // 선택 컬러에 대한 사이즈와 재고 조회결과 셋팅
		}
		// 상품조회 결과
		model.addAttribute("productCommon", productCommon);
		model.addAttribute("productCommonWithProductColor", productCommonWithProductColor);
		
		return "/product/getProductCommonJoinProduct";
	}

	@GetMapping("/product/getProductListByCategory")
	public String getProductListByCategory(Model model 
										,@RequestParam(value = "categoryNo") int categoryNo
										,@RequestParam(value= "currentPage", defaultValue = "1") int currentPage
										,@RequestParam(value= "searchWord", defaultValue = "") String searchWord) {
		System.out.println("getProductListByCategory");
		System.out.println(categoryNo + " : categoryNo ProductController");
		System.out.println(currentPage + " : currentPage ProductController");
		System.out.println(searchWord + " : searchWord ProductController");
		Map<String, Object> map = productCommonService.getProductCommonListByCategory(categoryNo, currentPage, searchWord);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("categoryNo", categoryNo);
		model.addAttribute("currentPage", map.get("currentPage"));
		model.addAttribute("totalPage", map.get("totalPage"));
		model.addAttribute("startPage", map.get("startPage"));
		model.addAttribute("endPage", map.get("endPage"));
		model.addAttribute("nextTest", map.get("nextTest"));
		model.addAttribute("previousTest", map.get("previousTest"));		
		return "product/getProductListByCategory";
		
	}
	
	
	/*	
	// 카테고리별 상품리스트
	@GetMapping("/product/getProductCommonByCategory")
	public String getProductCommonByCategory(Model model, @RequestParam(value = "categoryNo") int categoryNo
														 ,@RequestParam(value="currentPage", defaultValue = "1") int currentPage) {
		System.out.println("getProductListByCategory");
		System.out.println(categoryNo + " : categoryNo ProductController");
		System.out.println(currentPage + " : currentPage ProductController");
		Map<String, Object> map = productCommonService.getProductCommonByCategory(categoryNo, currentPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("categoryNo", categoryNo);
		model.addAttribute("currentPage", map.get("currentPage"));
		model.addAttribute("totalPage", map.get("totalPage"));
		model.addAttribute("startPage", map.get("startPage"));
		model.addAttribute("endPage", map.get("endPage"));
		model.addAttribute("nextTest", map.get("nextTest"));
		model.addAttribute("previousTest", map.get("previousTest"));		
		return "product/getProductCommonByCategory";
		
	}
	*/
}