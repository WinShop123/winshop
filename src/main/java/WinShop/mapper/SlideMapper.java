package WinShop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import WinShop.entity.Slide;

public class SlideMapper implements RowMapper<Slide> {

	public Slide mapRow(ResultSet rs, int rowNum) throws SQLException {
		Slide slide = new Slide();
		slide.setId(rs.getInt("id"));
		slide.setTitle(rs.getString("title"));
		slide.setContent(rs.getString("content"));
		slide.setClick(rs.getString("click"));
		slide.setImage(rs.getString("image"));
		slide.setVideo(rs.getString("video"));
		return slide;
	}

}
