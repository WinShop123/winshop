package WinShop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import WinShop.entity.Role;
import WinShop.mapper.RoleMapper;

@Repository
public class RoleDao extends BaseDao {
	public int setRole(long account_id, String role) {
		String sql = "INSERT INTO `role` (`id`, `account_id`, `role`) VALUES(NULL, '" + account_id + "', '" + role + "')";
		int insert = jdbcTemplate.update(sql);
		return insert;
	}
	
	public List<Role> getRolesData() {
		String sql = "SELECT * FROM `role`";
		List<Role> list = jdbcTemplate.query(sql, new RoleMapper());
		return list;
	}
	
	public int create(Role role) {
		String sql = "INSERT INTO `role` (`id`, `account_id`, `role`) VALUES(NULL, '" + role.getAccount_id() + "', '" + role.getRole() + "')";
		int insert = jdbcTemplate.update(sql);
		return insert;
	}
	
	public int edit(Role role) {
		String sql = "UPDATE `role` SET account_id='" + role.getAccount_id() + "', role='" + role.getRole() + "' "
				+ "WHERE id='" + role.getId() + "'";
		int edit = jdbcTemplate.update(sql);
		return edit;
	}
	
	public int delete(long id) {
		String sql = "DELETE FROM `role` WHERE id=" + id;
		int delete = jdbcTemplate.update(sql);
		return delete;
	}
	
	public List<Role> getRolesDataPagination(int start, int limit) {
		String sql = "SELECT * FROM `role` LIMIT " + start + ", " + limit;
		List<Role> pagination = jdbcTemplate.query(sql, new RoleMapper());
		return pagination;
	}
	
	public Role getRoleById(long id) {
		String sql = "SELECT * FROM `role` WHERE id=" + id;
		Role role = jdbcTemplate.queryForObject(sql, new RoleMapper());
		return role;
	}
	
	public String getRoleByAccountId(long account_id) {
		String sql = "SELECT role FROM `role` WHERE account_id = " + account_id;
		String roles = jdbcTemplate.queryForObject(sql, new Object[] {}, String.class);
		return roles;
	}
	
	public int setRoleByAccountId(long account_id, String role) {
		String sql = "UPDATE `role` SET `role`='" + role + "' WHERE account_id='" + account_id + "'";
		int update = jdbcTemplate.update(sql);
		return update;
	}
}
