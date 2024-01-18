package WinShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WinShop.dao.BillDetailsDao;
import WinShop.entity.BillDetails;

@Service
public class BillDetailsIServiceImplement implements IBillDetailsService {

	@Autowired
	private BillDetailsDao billdetailsDao;

	public int addBillDetails(BillDetails billdetails) {
		return billdetailsDao.addBillDetails(billdetails);
	}

	public List<BillDetails> getBillDetailsData() {
		return billdetailsDao.getBillDetailsData();
	}

	public List<BillDetails> getBillDetailsDataPagination(int start, int limit) {
		return billdetailsDao.getBillDetailsDataPagination(start, limit);
	}

	public BillDetails getBillDetailsById(long id) {
		return billdetailsDao.getBillDetailsById(id);
	}

	public int edit(BillDetails billdetails) {
		return billdetailsDao.edit(billdetails);
	}

	public int delete(long id) {
		return billdetailsDao.delete(id);
	}

	public List<Long> topSellProducts() {
		return billdetailsDao.topSellProducts();
	}

	public List<Long> notTopSellProducts() {
		return billdetailsDao.notTopSellProducts();
	}

	public List<BillDetails> getBillDetailsByBillId(long bill_id) {
		return billdetailsDao.getBillDetailsByBillId(bill_id);
	}

}
