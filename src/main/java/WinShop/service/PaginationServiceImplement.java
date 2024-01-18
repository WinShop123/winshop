package WinShop.service;

import org.springframework.stereotype.Service;

import WinShop.dto.PaginationDto;

@Service
public class PaginationServiceImplement implements IPaginationService {

	public PaginationDto getInfoPagination(int totalData, int limit, int current) {
		PaginationDto pagination = new PaginationDto();
		pagination.setLimit(limit);
		pagination.setTotal(setTotalPages(limit, totalData));
		pagination.setCurrent(checkCurrent(current, pagination.getTotal()));
		pagination.setStart(findStart(limit, current));
		pagination.setEnd(findEnd(pagination.getStart(), limit, totalData));
		return pagination;
	}
	
	public int findEnd(int start, int limit, int totalData) {
		return start + limit > totalData ? totalData : start + limit - 1;
	}
	
	public int findStart(int limit, int current) {
		return (current - 1) * limit;
	}
	
	private int checkCurrent(int current, int total) {
		if(current > total) {
			return total;
		}
		if(current < 1) {
			return 1;
		}
		return current;
	}
	
	private int setTotalPages(int limit, int totalData) {
		int totalPage = totalData / limit;
		totalPage = totalData > totalPage * limit ? totalPage + 1 : totalPage;
		return totalPage;
	}
}
