package WinShop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import WinShop.entity.Slide;
import WinShop.mapper.SlideMapper;

@Repository
public class SlideDao extends BaseDao {
	public List<Slide> getSlidesData() {
		String sql = "SELECT * FROM `slide`";
		List<Slide> list = jdbcTemplate.query(sql, new SlideMapper());
		return list;
	}
	
	public List<Slide> getSlidesDataPagination(int start, int limit) {
		String sql = "SELECT * FROM `slide` LIMIT " + start + ", " + limit;
		List<Slide> list = jdbcTemplate.query(sql, new SlideMapper());
		return list;
	}
	
	public Slide getSlideById(long id) {
		String sql = "SELECT * FROM `slide` WHERE id=" + id;
		Slide slide = jdbcTemplate.queryForObject(sql, new SlideMapper());
		return slide;
	}
	
	public int create(Slide slide) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO `slide`(`id`, `title`, `content`, `click`, `image`, `video`) ");
		sql.append("VALUES ");
		sql.append("(NULL, ");
		sql.append(" '" + slide.getTitle() + "', ");
		sql.append(" '" + slide.getContent() + "', ");
		sql.append(" '" + slide.getClick() + "', ");
		sql.append(" '" + slide.getImage() + "', ");
		sql.append(" '" + slide.getVideo() + "')");
		int create = jdbcTemplate.update(sql.toString());
		return create;
	}
	
	public int edit(Slide slide) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE `slide` SET ");
		sql.append("`title`='" + slide.getTitle() + "', ");
		sql.append("`content`='" + slide.getContent() + "', ");
		sql.append("`click`='" + slide.getClick() + "', ");
		sql.append("`image`='" + slide.getImage() + "', ");
		sql.append("`video`='" + slide.getVideo() + "' ");
		sql.append("WHERE id=" + slide.getId());
		int edit = jdbcTemplate.update(sql.toString());
		return edit;
	}
	
	public int delete(long id) {
		String sql = "DELETE FROM `slide` WHERE id=" + id;
		int delete = jdbcTemplate.update(sql);
		return delete;
	}
}
