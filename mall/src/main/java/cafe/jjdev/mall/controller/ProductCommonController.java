package cafe.jjdev.mall.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.service.ProductCommonService;

@Controller
public class ProductCommonController {
	@Autowired
	ProductCommonService productCommonService;
	
	
	
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
}