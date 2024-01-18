package WinShop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import WinShop.entity.Category;

@Service
public interface ICategoryService {
	public List<Category> getCategoriesData();
	
	public Category getCategoryByCatid(int category_id);
	
	public int create(Category category);
	
	public int edit(Category category);
	
	public int delete(int id);
	
	public List<Category> getCategoriesDataPagination(int start, int limit);
}
