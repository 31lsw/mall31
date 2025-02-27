package cafe.jjdev.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.mall.mapper.CategoryMapper;
import cafe.jjdev.mall.vo.Category;

@Service
@Transactional
public class CategoryService {
	@Autowired
	CategoryMapper categoryMapper;
	
	public List<Category> getCategoryList(){
		
		return categoryMapper.selectCategoryList();
	}
}
