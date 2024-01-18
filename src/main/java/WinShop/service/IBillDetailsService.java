package WinShop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import WinShop.entity.BillDetails;

@Service
public interface IBillDetailsService {

	public List<BillDetails> getBillDetailsData();
	
	public List<BillDetails> getBillDetailsDataPagination(int start, int limit);
	
	public BillDetails getBillDetailsById(long id);
	
	public int addBillDetails(BillDetails billdetails);
	
	public int edit(BillDetails billdetails);
	
	public int delete(long id);
	
	public List<Long> topSellProducts();
	
	public List<Long> notTopSellProducts();
	
	public List<BillDetails> getBillDetailsByBillId(long bill_id);
}
