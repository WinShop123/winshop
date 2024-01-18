package WinShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WinShop.dao.BillDao;
import WinShop.entity.Bill;

@Service
public class BillServiceImplement implements IBillService {

	@Autowired
	private BillDao billDao;

	public int addBill(Bill bill) {
		return billDao.addBill(bill);
	}

	public long getTheLastBillId() {
		return billDao.getTheLastBillId();
	}

	public List<Bill> getBillsData() {
		return billDao.getBillsData();
	}

	public List<Bill> getBillsDataPagination(int start, int limit) {
		return billDao.getBillsDataPagination(start, limit);
	}

	public Bill getBillById(long id) {
		return billDao.getBillById(id);
	}

	public int edit(Bill bill) {
		return billDao.edit(bill);
	}

	public int delete(long id) {
		return billDao.delete(id);
	}

	public List<Bill> latestBillsInAPeriodTime(int number) {
		return billDao.latestBillsInAPeriodTime(number);
	}

	public List<Bill> getBillsByUsername(String username) {
		return billDao.getBillsByUsername(username);
	}

	public int confirmBill(long id) {
		return billDao.confirmBill(id);
	}
	


}
