package WinShop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import WinShop.entity.Bill;
import WinShop.mapper.BillMapper;

@Repository
public class BillDao extends BaseDao {
	public long getTheLastBillId() {
		String sql = "SELECT MAX(id) FROM `bill`";
		long id = jdbcTemplate.queryForObject(sql, new Object[] {}, Long.class);
		return id;
	}
	
	public List<Bill> getBillsData() {
		String sql = "SELECT * FROM `bill`";
		List<Bill> list = jdbcTemplate.query(sql, new BillMapper());
		return list;
	}
	
	public List<Bill> getBillsDataPagination(int start, int limit) {
		String sql = "SELECT * FROM `bill` LIMIT " + start + ", " + limit;
		List<Bill> list = jdbcTemplate.query(sql, new BillMapper());
		return list;
	}
	
	public Bill getBillById(long id) {
		String sql = "SELECT * FROM `bill` WHERE id=" + id;
		Bill bill = jdbcTemplate.queryForObject(sql, new BillMapper());
		return bill;
	}
	
	public int addBill(Bill bill) {
		StringBuffer  sql = new StringBuffer();
		sql.append("INSERT INTO `bill` ");
		sql.append("(`id`, ");
		sql.append("`username`, ");
		sql.append("`phone`, ");
		sql.append("`address`, ");
		sql.append("`quantity`, ");
		sql.append("`total`, ");
		sql.append("`note`, ");
		sql.append("`createat`, ");
		sql.append("`status`) ");
		sql.append("VALUES ");
		sql.append("(NULL, ");
		sql.append("'" + bill.getUsername() + "', ");
		sql.append("'" + bill.getPhone() + "', ");
		sql.append("'" + bill.getAddress() + "', ");
		sql.append("'" + bill.getQuantity() + "', ");
		sql.append("'" + bill.getTotal() + "', ");
		sql.append("'" + bill.getNote() + "', ");
		sql.append("'" + bill.getCreateat() + "', ");
		sql.append("" + bill.isStatus() + ")");
		int insert = jdbcTemplate.update(sql.toString());
		return insert;
	}
	
	public int edit(Bill bill) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE `bill` SET ");
		sql.append("`username`='" + bill.getUsername() + "', ");
		sql.append("`phone`='" + bill.getPhone() + "', ");
		sql.append("`address`='" + bill.getAddress() + "', ");
		sql.append("`quantity`='" + bill.getQuantity() + "', ");
		sql.append("`total`='"+ bill.getTotal() + "', ");
		sql.append("`note`='" + bill.getNote() + "' ");
		sql.append("WHERE bill.id=" + bill.getId());
		int edit = jdbcTemplate.update(sql.toString());
		return edit;
	}
	
	public int delete(long id) {
		String sql = "DELETE FROM `bill` WHERE id=" + id;
		int delete = jdbcTemplate.update(sql);
		return delete;
	}
	
	public List<Bill> latestBillsInAPeriodTime(int number) {
		String sql = "SELECT * FROM `bill` WHERE DATEDIFF(CURRENT_DATE, bill.createat) <= " + number + " AND status=true";
		List<Bill> list = jdbcTemplate.query(sql, new BillMapper());
		return list;
	}
	
	public List<Bill> getBillsByUsername(String username) {
		String sql = "SELECT * FROM `bill` WHERE username='" + username + "'";
		List<Bill> list = jdbcTemplate.query(sql, new BillMapper());
		return list;
	}
	
	public int confirmBill(long id) {
		String sql = "UPDATE `bill` SET status=true WHERE id=" + id;
		int update = jdbcTemplate.update(sql);
		return update;
	}
}
