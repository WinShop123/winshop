package WinShop.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WinShop.dao.CartDao;
import WinShop.dto.CartDto;
import WinShop.entity.Cart;

@Service
public class CartServiceImplement implements ICartService {
	
	
	@Autowired
	private CartDao cartDao;

	public HashMap<Long, CartDto> addToCart(long id, int quantity, HashMap<Long, CartDto> cart, String username) {
		return cartDao.addToCart(id, quantity, cart, username);
	}

	public HashMap<Long, CartDto> editCartItem(long id, int quantity, HashMap<Long, CartDto> cart, String username) {
		return cartDao.editCartItem(id, quantity, cart, username);
	}

	public HashMap<Long, CartDto> deleteCartItem(long id, HashMap<Long, CartDto> cart, String username) {
		return cartDao.deleteCartItem(id, cart, username);
	}

	public int totalQuantity(HashMap<Long, CartDto> cart) {
		return cartDao.totalQuantity(cart);
	}

	public BigDecimal totalPrice(HashMap<Long, CartDto> cart) {
		return cartDao.totalPrice(cart);
	}

	public int deleteCartByUsername(String username) {
		return cartDao.deleteCartByUsername(username);
	}

	public int create(Cart cart) {
		return cartDao.create(cart);
	}

	public int edit(Cart cart) {
		return cartDao.edit(cart);
	}

	public int deleteBySession(long id, String username) {
		return cartDao.deleteBySession(id, username);
	}

	public List<Cart> getCartsDataByUsername(String username) {
		return cartDao.getCartsDataByUsername(username);
	}

	public HashMap<Long, CartDto> createCartSession(List<Cart> cart, String username) {
		return cartDao.createCartSession(cart, username);
	}

	public List<Cart> getCartsData() {
		return cartDao.getCartData();
	}

	public List<Cart> getCartsDataPagination(int start, int limit) {
		return cartDao.getCartDataPagination(start, limit);
	}

	public int delete(long id) {
		return cartDao.delete(id);
	}

	public Cart getCartById(long id) {
		return cartDao.getCartById(id);
	}
}
