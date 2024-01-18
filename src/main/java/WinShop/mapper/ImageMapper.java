package WinShop.mapper;

import WinShop.entity.Image;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ImageMapper implements RowMapper<Image> {

	public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
		Image image = new Image();
		image.setId(rs.getLong("id"));
		image.setProduct_id(rs.getLong("product_id"));
		image.setImage(rs.getString("image"));
		image.setIs_slideimage(rs.getBoolean("isSlideImage"));
		return image;
	}

}
