package WinShop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import WinShop.dto.ProductDto;

public class ProductDtoMapper implements RowMapper<ProductDto> {

	public ProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDto product = new ProductDto();
		product.setCategory_id(rs.getInt("category_id"));
		product.setId(rs.getLong("id"));
		product.setName(rs.getString("name"));
		product.setImage(rs.getString("image"));
		product.setDimension(rs.getString("dimension"));
		product.setMaterial(rs.getString("material"));
		product.setAccessories(rs.getString("accessories"));
		product.setQuantity(rs.getInt("quantity"));
		product.setPrice(rs.getBigDecimal("price"));
		product.setDescription(rs.getString("description"));
		product.setImage_id(rs.getLong("image_id"));
		product.setImage_img(rs.getString("image_img"));
		product.setIs_slideimage(rs.getBoolean("isSlideImage"));
		product.setSale_off(rs.getInt("sale_off"));
		product.setIs_new(rs.getBoolean("is_new"));
		product.setIs_topsell(rs.getBoolean("is_topsell"));
		product.setCreateat(rs.getString("createat"));
		return product;
	}

}
