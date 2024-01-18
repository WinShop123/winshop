package WinShop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import WinShop.entity.BillDetails;

public class BillDetailsMapper implements RowMapper<BillDetails> {

	public BillDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		BillDetails billdetails = new BillDetails();
		billdetails.setId(rs.getLong("id"));
		billdetails.setBill_id(rs.getLong("bill_id"));
		billdetails.setProduct_id(rs.getLong("product_id"));
		billdetails.setQuantity(rs.getInt("quantity"));
		billdetails.setTotal(rs.getBigDecimal("total"));
		return billdetails;
	}

}
