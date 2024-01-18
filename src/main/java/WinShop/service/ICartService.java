package WinShop.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import WinShop.dto.CartDto;
import WinShop.entity.Cart;

@Service
public interface ICartService {
public HashMap<Long, CartDto> addToCart(long id, int quantity, HashMap<Long, CartDto> cart, String username);
	
	public HashMap<Long, CartDto> editCartItem(long id, int quantity, HashMap<Long, CartDto> cart, String username);
	
	public HashMap<Long, CartDto> deleteCartItem(long id, HashMap<Long, CartDto> cart, String username);
	
	public int totalQuantity(HashMap<Long, CartDto> cart);
	
	public BigDecimal totalPrice(HashMap<Long, CartDto> cart);
	
	public int deleteCartByUsername(String username);
	
	public int create(Cart cart);
	
	public int edit(Cart cart);
	
	public int deleteBySession(long id, String username);
	
	public List<Cart> getCartsDataByUsername(String username);
	
	public HashMap<Long, CartDto> createCartSession(List<Cart> cart, String username);
	
	public List<Cart> getCartsData();
	
	public List<Cart> getCartsDataPagination(int start, int limit);
	
	public int delete(long id);
	
	public Cart getCartById(long id);
}
