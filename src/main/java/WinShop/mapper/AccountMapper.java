package WinShop.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import WinShop.entity.Account;

public class AccountMapper implements RowMapper<Account> {

	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account account = new Account();
		account.setId(rs.getLong("id"));
		account.setName(rs.getString("name"));
		account.setUsername(rs.getString("username"));
		account.setPassword(rs.getString("password"));
		account.setIs_verified(rs.getBoolean("is_verified"));
		return account;
	}
	
}
