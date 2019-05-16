package cafe.jjdev.mall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.Product;
import cafe.jjdev.mall.vo.ProductCommon;

@Mapper
public interface ProductCommonMapper {
	
	// 4
	public List<Product> selectProductSizeAndStockByProductColorAndProductCommonNo(Product product); // productColor, productCommonNo가 셋팅된 커맨드 객체가 들어감
	// 1
	public List<ProductCommon> selectProductCommonList(Map<String, Object> map);
	// 2
	public ProductCommon selectProductCommonJoinProduct(Map<String, Object> productMap);
	// 3 게시글 총갯수
	public int selectProductCommonCount();
	
	
	
}
