package WinShop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import WinShop.entity.BillDetails;
import WinShop.mapper.BillDetailsMapper;

@Repository
public class BillDetailsDao extends BaseDao {
	public List<BillDetails> getBillDetailsData() {
		String sql = "SELECT * FROM `billdetails`";
		List<BillDetails> list = jdbcTemplate.query(sql, new BillDetailsMapper());
		return list;
	}
	
	public List<BillDetails> getBillDetailsDataPagination(int start, int limit) {
		String sql = "SELECT * FROM `billdetails` LIMIT " + start + ", " + limit;
		List<BillDetails> list = jdbcTemplate.query(sql, new BillDetailsMapper());
		return list;
	}
	
	public BillDetails getBillDetailsById(long id) {
		String sql = "SELECT * FROM `billdetails` WHERE id=" + id;
		BillDetails billdetails = jdbcTemplate.queryForObject(sql, new BillDetailsMapper());
		return billdetails;
	}
	
	public int addBillDetails(BillDetails billdetails) {
		StringBuffer  sql = new StringBuffer();
		sql.append("INSERT INTO `billdetails` ");
		sql.append("(`id`, ");
		sql.append(" `bill_id`, ");
		sql.append(" `product_id`, ");
		sql.append(" `quantity`, ");
		sql.append(" `total`) ");
		sql.append(" VALUES ");
		sql.append(" (NULL, ");
		sql.append("  " + billdetails.getBill_id() +", ");
		sql.append("  '" + billdetails.getProduct_id() + "', ");
		sql.append("  '" + billdetails.getQuantity() + "', ");
		sql.append("  '" + billdetails.getTotal() + "')");
		int insert = jdbcTemplate.update(sql.toString());
		return insert;
	}
	
	public int edit(BillDetails billdetails) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE `billdetails` SET ");
		sql.append("`bill_id`='" + billdetails.getBill_id() + "', ");
		sql.append("`product_id`='" + billdetails.getProduct_id() + "', ");
		sql.append("`quantity`='" + billdetails.getQuantity() + "', ");
		sql.append("`total`='" + billdetails.getTotal() + "' ");
		sql.append("WHERE id=" + billdetails.getId());
		int edit = jdbcTemplate.update(sql.toString());
		return edit;
	}
	
	public int delete(long id) {
		String sql = "DELETE FROM `billdetails` WHERE id=" + id;
		int delete = jdbcTemplate.update(sql);
		return delete;
	}
	
	public List<Long> topSellProducts() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT billdetails.product_id FROM `billdetails` ");
		sql.append("INNER JOIN `bill` ");
		sql.append("ON bill.id = billdetails.bill_id ");
		sql.append("WHERE DATEDIFF(CURRENT_DATE, bill.createat) <= 30 ");
		sql.append("AND product_id > 4 ");
		sql.append("AND bill.status = true ");
		sql.append("GROUP BY billdetails.product_id ");
		sql.append("HAVING SUM(billdetails.quantity) >= 30");
		List<Long> list = jdbcTemplate.queryForList(sql.toString(), Long.class);
		return list;
	}
	
	public List<Long> notTopSellProducts() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT billdetails.product_id FROM `billdetails` ");
		sql.append("INNER JOIN `bill` ");
		sql.append("ON bill.id = billdetails.bill_id ");
		sql.append("WHERE DATEDIFF(CURRENT_DATE, bill.createat) > 30 ");
		sql.append("AND product_id > 4 ");
		sql.append("AND bill.status = true ");
		sql.append("GROUP BY billdetails.product_id ");
		sql.append("HAVING SUM(billdetails.quantity) < 30");
		List<Long> list = jdbcTemplate.queryForList(sql.toString(), Long.class);
		return list;
	}
	
	public List<BillDetails> getBillDetailsByBillId(long bill_id) {
		String sql = "SELECT * FROM `billdetails` WHERE bill_id='" + bill_id + "'";
		List<BillDetails> list = jdbcTemplate.query(sql, new BillDetailsMapper());
		return list;
	}
}
