package WinShop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import WinShop.entity.Account;
import WinShop.mapper.AccountMapper;

@Repository
public class AccountDao extends BaseDao {
	public List<Account> getAccountsData() {
		String sql = "SELECT * FROM `account`";
		List<Account> list = jdbcTemplate.query(sql, new AccountMapper());
		return list;
	}
	
	public int addAcount(Account account) {
		StringBuffer  sql = new StringBuffer();
		sql.append("INSERT INTO `account` ");
		sql.append("(`name`, ");
		sql.append("`username`, ");
		sql.append("`password`, ");
		sql.append("`is_verified`) ");
		sql.append("VALUES ");
		sql.append("('" + account.getName() + "', ");
		sql.append("'" + account.getUsername() + "', ");
		sql.append("'" + account.getPassword() +"', ");
		sql.append("" + account.isIs_verified() + ")");
		int insert = jdbcTemplate.update(sql.toString());
		return insert;
	}
	
	public int edit(Account account) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE `account` SET ");
		sql.append("`name`='" + account.getName() + "', ");
		sql.append("`username`='" + account.getUsername() + "', ");
		sql.append("`password`='" + account.getPassword() + "', ");
		sql.append("`is_verified`=" + account.isIs_verified() + " ");
		sql.append("WHERE id='" + account.getId() + "'");
		int edit = jdbcTemplate.update(sql.toString());
		return edit;
	}
	
	public int delete(long id) {
		String sql = "DELETE FROM `account` WHERE id=" + id;
		int delete = jdbcTemplate.update(sql);
		return delete;
	}
	
	public Account getAccountById(long id) {
		String sql = "SELECT * FROM `account` WHERE id='" + id + "'";
		Account account = jdbcTemplate.queryForObject(sql, new AccountMapper());
		return account;
	}
	
	public Account getAccountByUsername(String username) {
		String sql = "SELECT * FROM `account` WHERE username='" + username + "'";
		Account account = jdbcTemplate.queryForObject(sql, new AccountMapper());
		return account;
	}
	
	public List<Account> getAccountsDataPagination(int start,int limit) {
		String sql = "SELECT *  FROM `account` LIMIT " + start + ", " + limit;
		List<Account> pagination = jdbcTemplate.query(sql, new AccountMapper());
		return pagination;
	}
	
	public int setNewPassword(String password, String username) {
		String sql = "UPDATE `account` SET password='" + password + "' WHERE username='" + username + "'";
		int update = jdbcTemplate.update(sql);
		return update;
	}
}
