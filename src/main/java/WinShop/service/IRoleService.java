package WinShop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import WinShop.entity.Role;

@Service
public interface IRoleService {
public int setRole(long account_id, String role);
	
	public List<Role> getRolesData();
	
	public int create(Role role);
	
	public int edit(Role role);
	
	public int delete(long id);
	
	public List<Role> getRolesDataPagination(int start, int limit);
	
	public Role getRoleById(long id);
	
	public String getRoleByAccountId(long account_id);
	
	public int setRoleByAccountId(long account_id, String role);
}
