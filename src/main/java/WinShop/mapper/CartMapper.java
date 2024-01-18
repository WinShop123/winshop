package WinShop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import WinShop.entity.Cart;

public class CartMapper implements RowMapper<Cart> {

	public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cart cart = new Cart();
		cart.setId(rs.getLong("id"));
		cart.setProduct_id(rs.getLong("product_id"));
		cart.setUsername(rs.getString("username"));
		cart.setQuantity(rs.getInt("quantity"));
		cart.setTotal(rs.getBigDecimal("total"));
		return cart;
	}

}
