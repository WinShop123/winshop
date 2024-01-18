package WinShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WinShop.dao.CategoryDao;
import WinShop.entity.Category;

@Service
public class CategoryServiceImplement implements ICategoryService {

	
	@Autowired
	private CategoryDao categoryDao;

	public List<Category> getCategoriesData() {
		return categoryDao.getCategoriesData();
	}

	public Category getCategoryByCatid(int category_id) {
		return categoryDao.getCategoryByCatId(category_id);
	}

	public int create(Category category) {
		return categoryDao.create(category);
	}

	public int edit(Category category) {
		return categoryDao.edit(category);
	}

	public int delete(int id) {
		return categoryDao.delete(id);
	}

	public List<Category> getCategoriesDataPagination(int start, int limit) {
		return categoryDao.getProductsDataPagination(start, limit);
	}
}
