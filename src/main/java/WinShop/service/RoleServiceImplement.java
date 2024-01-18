package WinShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WinShop.dao.RoleDao;
import WinShop.entity.Role;

@Service
public class RoleServiceImplement implements IRoleService {

	@Autowired
	private RoleDao roleDao;

	public int setRole(long account_id, String role) {
		return roleDao.setRole(account_id, role);
	}

	public List<Role> getRolesData() {
		return roleDao.getRolesData();
	}

	public int create(Role role) {
		return roleDao.create(role);
	}

	public int edit(Role role) {
		return roleDao.edit(role);
	}

	public int delete(long id) {
		return roleDao.delete(id);
	}

	public List<Role> getRolesDataPagination(int start, int limit) {
		return roleDao.getRolesDataPagination(start, limit);
	}

	public Role getRoleById(long id) {
		return roleDao.getRoleById(id);
	}

	public String getRoleByAccountId(long account_id) {
		return roleDao.getRoleByAccountId(account_id);
	}

	public int setRoleByAccountId(long account_id, String role) {
		return roleDao.setRoleByAccountId(account_id, role);
	}
}
