package WinShop.service;

import org.springframework.stereotype.Service;

import WinShop.dto.PaginationDto;

@Service
public interface IPaginationService {
	public PaginationDto getInfoPagination(int totalData, int limit, int current);
}
