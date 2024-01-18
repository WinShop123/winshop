package WinShop.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WinShop.dao.AccountDao;
import WinShop.entity.Account;

@Service
public class AccountServiceImplement implements IAccountService {

	@Autowired
	private AccountDao accountDao;

	public Account getAccountById(long id) {
		return accountDao.getAccountById(id);
	}

	public int addAccount(Account account) {
		account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(12)));
		account.setIs_verified(false);
		return accountDao.addAcount(account);
	}

	public Account getAccountByUsername(String username) {
		return accountDao.getAccountByUsername(username);
	}

	public List<Account> getAccountsData() {
		return accountDao.getAccountsData();
	}

	public int edit(Account account) {
		return accountDao.edit(account);
	}

	public int delete(long id) {
		return accountDao.delete(id);
	}

	public List<Account> getAccountsDataPagination(int start, int limit) {
		return accountDao.getAccountsDataPagination(start, limit);
	}

	public int setNewPassword(String password, String username) {
		return accountDao.setNewPassword(password, username);
	}
}
