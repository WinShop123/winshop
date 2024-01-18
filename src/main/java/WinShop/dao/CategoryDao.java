package WinShop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import WinShop.entity.Category;
import WinShop.mapper.CategoryMapper;

@Repository
public class CategoryDao extends BaseDao {
	public List<Category> getCategoriesData() {
		String sql = "SELECT * FROM `category`";
		List<Category> list = jdbcTemplate.query(sql, new CategoryMapper());
		return list;
	}
	
	public Category getCategoryByCatId(int category_id) {
		String sql = "SELECT * FROM `category` WHERE id='" + category_id + "'";
		Category category = jdbcTemplate.queryForObject(sql, new CategoryMapper());
		return category;
	}
	
	public int create(Category category) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO `category` ");
		sql.append("(`id`, `name`, `logo`, `icon`) ");
		sql.append("VALUES (NULL, '" + category.getName() + "', '" + category.getLogo() + "', '" + category.getIcon() + "');");
		int insert = jdbcTemplate.update(sql.toString());
		return insert;
	}
	
	public int edit(Category category) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE `category` ");
		sql.append("SET `name` = '" + category.getName() + "', `logo` = '" + category.getLogo() + "', `icon` = '" + category.getIcon() + "' ");
		sql.append("WHERE `category`.`id` = " + category.getId());
		int edit = jdbcTemplate.update(sql.toString());
		return edit;
	}
	
	public int delete(int id) {
		String sql = "DELETE FROM `category` WHERE id=" + id;
		int delete = jdbcTemplate.update(sql);
		return delete;
	}
	
	public List<Category> getProductsDataPagination(int start, int limit) {
		String sql = "SELECT * FROM `category` LIMIT " + start + ", " + limit;
		List<Category> list = jdbcTemplate.query(sql, new CategoryMapper());
		return list;
	}
}
