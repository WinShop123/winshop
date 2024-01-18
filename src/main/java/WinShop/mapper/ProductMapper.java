package WinShop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import WinShop.entity.Product;

public class ProductMapper implements RowMapper<Product> {

	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setId(rs.getLong("id"));
		product.setCategory_id(rs.getInt("category_id"));
		product.setName(rs.getString("name"));
		product.setImage(rs.getString("image"));
		product.setDimension(rs.getString("dimension"));
		product.setMaterial(rs.getString("material"));
		product.setAccessories(rs.getString("accessories"));
		product.setQuantity(rs.getInt("quantity"));
		product.setPrice(rs.getBigDecimal("price"));
		product.setDescription(rs.getString("description"));
		product.setSale_off(rs.getInt("sale_off"));
		product.setIs_new(rs.getBoolean("is_new"));
		product.setIs_topsell(rs.getBoolean("is_topsell"));
		product.setCreateat(rs.getString("createat"));
		return product;
	}

}
