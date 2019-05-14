package cafe.jjdev.mall.vo;

import java.util.List;

//request타입을 이렇게 만들기도 한다
//join쿼리 결과물처럼 db에서 출력하는 형태가 다를 수가 있기 때문에...

public class ProductCommonJoinProduct {
	private ProductCommon productCommon;
	private List<Product> list;
	
	public ProductCommon getProductCommon() {
		return productCommon;
	}
	public void setProductCommon(ProductCommon productCommon) {
		this.productCommon = productCommon;
	}
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "ProductCommonJoinProduct [productCommon=" + productCommon + ", list=" + list + "]";
	}
	
}
