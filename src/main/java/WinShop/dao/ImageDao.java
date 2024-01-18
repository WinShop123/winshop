package WinShop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import WinShop.entity.Image;
import WinShop.mapper.ImageMapper;

@Repository
public class ImageDao extends BaseDao {
	public List<Image> getImagesProductId(long product_id) {
		String sql = "SELECT * FROM `image` WHERE product_id='" + product_id + "'";
		List<Image> list = jdbcTemplate.query(sql, new ImageMapper());
		return list;
	}
	
	public List<String> getImagesStringByProductId(long product_id) {
		String sql = "SELECT image.image FROM `image` WHERE image.product_id='" + product_id + "'";
		List<String> images = jdbcTemplate.queryForList(sql, String.class);
		return images;
	}
	
	public List<Image> getImagesData() {
		String sql = "SELECT * FROM `image`";
		List<Image> list = jdbcTemplate.query(sql, new ImageMapper());
		return list;
	}
	
	public List<Image> getImagesDataPagination(int start, int limit) {
		String sql = "SELECT * FROM `image` LIMIT " + start + ", " + limit;
		List<Image> pagination = jdbcTemplate.query(sql, new ImageMapper());
		return pagination;
	}
	
	public int create(Image image) {
		StringBuffer  sql = new StringBuffer();
		sql.append("INSERT INTO `image` ");
		sql.append("(`id`, `product_id`, `image`, `isSlideImage`) ");
		sql.append("VALUES ");
		sql.append("(NULL, ");
		sql.append(" '" + image.getProduct_id() + "', ");
		sql.append(" '" + image.getImage() + "', ");
		sql.append(" " + image.isIs_slideimage() + ")");
		int insert = jdbcTemplate.update(sql.toString());
		return insert;
	}
	
	public int edit(Image image) {
		StringBuffer  sql = new StringBuffer();
		sql.append("UPDATE `image` SET ");
		sql.append("`product_id`='" + image.getProduct_id() + "', ");
		sql.append("`image`='" + image.getImage() + "', ");
		sql.append("`isSlideImage`=" + image.isIs_slideimage() + " ");
		sql.append("WHERE image.id=" + image.getId());
		int edit = jdbcTemplate.update(sql.toString());
		return edit;
	}
	
	public int delete(long id) {
		String sql = "DELETE FROM `image` WHERE image.id='" + id + "'";
		int delete = jdbcTemplate.update(sql);
		return delete;
	}
	
	public Image getImageById(long id) {
		String sql = "SELECT * FROM `image` WHERE image.id=" + id;
		Image image = jdbcTemplate.queryForObject(sql, new ImageMapper());
		return image;
	}
}
