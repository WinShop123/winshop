package WinShop.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import WinShop.dto.CartDto;
import WinShop.dto.ProductDto;
import WinShop.entity.Cart;
import WinShop.mapper.CartMapper;

@Repository
public class CartDao extends BaseDao {
	@Autowired
	private ProductDao productDao;
	
	public int create(Cart cart) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO `cart` (`id`, `product_id`, `username`, `quantity`, `total`) ");
		sql.append("VALUES ");
		sql.append("(NULL, '" + cart.getProduct_id() + "', ");
		sql.append(" '" + cart.getUsername() + "', ");
		sql.append(" '" + cart.getQuantity() + "', ");
		sql.append(" '" + cart.getTotal() + "')");
		int create = jdbcTemplate.update(sql.toString());
		return create;
	}
	
	public int create(CartDto cart, String username) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO `cart` (`id`, `product_id`, `username`, `quantity`, `total`) ");
		sql.append("VALUES ");
		sql.append("(NULL, '" + cart.getProduct().getId() + "', ");
		sql.append(" '" + username + "', ");
		sql.append(" '" + cart.getQuantity() + "', ");
		sql.append(" '" + cart.getTotalPrice() + "')");
		int create = jdbcTemplate.update(sql.toString());
		return create;
	}
	
	public int edit(Cart cart) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE `cart` SET `product_id`='" + cart.getProduct_id() + "', ");
		sql.append("`username`='" + cart.getUsername() + "', ");
		sql.append("`quantity`='" + cart.getQuantity() + "', ");
		sql.append("`total`='" + cart.getTotal() + "' ");
		sql.append("WHERE id=" + cart.getId());
		int edit = jdbcTemplate.update(sql.toString());
		return edit;
	}
	
	public int updateBySession(CartDto cart, String username) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE `cart` SET ");
		sql.append("`quantity`='" + cart.getQuantity() + "', ");
		sql.append("`total`='" + cart.getTotalPrice() + "' ");
		sql.append("WHERE product_id=" + cart.getProduct().getId() + " ");
		sql.append("AND username='" + username + "'");
		int update = jdbcTemplate.update(sql.toString());
		return update;
	}
	
	public int deleteBySession(long id, String username) {
		String sql = "DELETE FROM `cart` WHERE product_id=" + id + " AND username='" + username + "'";
		int delete = jdbcTemplate.update(sql);
		return delete;
	}
	
	public int delete(long id) {
		String sql = "DELETE FROM `cart` WHERE id=" + id;
		int delete = jdbcTemplate.update(sql);
		return delete;
	}
	
	public HashMap<Long, CartDto> addToCart(long id, int quantity, HashMap<Long, CartDto> cart, String username) {
		try {
			if(quantity <= 0) {
				return cart;
			}
			CartDto item = new CartDto();
			ProductDto product = productDao.getTheProductById(id);
			if(product != null && cart.containsKey(id)) {
				item = cart.get(id);
				if(product.getQuantity() < (item.getQuantity() + quantity)) {
					return cart;
				}
				item.setQuantity(item.getQuantity() + quantity);
				BigDecimal totalNoSaleoff = BigDecimal.valueOf(item.getQuantity()).multiply(product.getPrice());
				if(item.getProduct().getSale_off() > 0) {
					double saleOff = item.getProduct().getSale_off() / 100.0;
					item.setTotalPrice(totalNoSaleoff.subtract(totalNoSaleoff.multiply(BigDecimal.valueOf(saleOff).setScale(2, RoundingMode.HALF_UP))));
				}
				else {
					item.setTotalPrice(totalNoSaleoff);
				}
				cart.put(id, item);
				updateBySession(item, username);
				
			}
			else if(product != null && cart.containsKey(id) == false) {
				if(product.getQuantity() < quantity) {
					return cart;
				}
				item.setQuantity(quantity);
				BigDecimal totalNoSaleoff = BigDecimal.valueOf(item.getQuantity()).multiply(product.getPrice());
				if(product.getSale_off() > 0) {
					double saleOff = product.getSale_off() / 100.0;
					item.setTotalPrice(totalNoSaleoff.subtract(totalNoSaleoff.multiply(BigDecimal.valueOf(saleOff).setScale(2, RoundingMode.HALF_UP))));
				}
				else {
					item.setTotalPrice(totalNoSaleoff);
				}
				item.setProduct(product);
				cart.put(id, item);
				create(item, username);
			}
			return cart;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return cart;
		}
	}
	
	public HashMap<Long, CartDto> editCartItem(long id, int quantity, HashMap<Long, CartDto> cart, String username) {
		CartDto item = new CartDto();
		try {
			ProductDto product = productDao.getTheProductById(id);
			if(product != null && cart.containsKey(id)) {
				item = cart.get(id);
				if(product.getQuantity() < quantity || quantity <= 0 ) {
					CartDto empty = new CartDto();
					cart.put((long)-1, empty);
					return cart;
				}
				item.setQuantity(quantity);
				BigDecimal totalNoSaleoff = BigDecimal.valueOf(item.getQuantity()).multiply(product.getPrice());
				if(item.getProduct().getSale_off() > 0) {
					double saleOff = item.getProduct().getSale_off() / 100.0;
					item.setTotalPrice(totalNoSaleoff.subtract(totalNoSaleoff.multiply(BigDecimal.valueOf(saleOff).setScale(2, RoundingMode.HALF_UP))));
				}
				else {
					item.setTotalPrice(totalNoSaleoff);
				}
			}
			cart.put(id, item);
			updateBySession(item, username);
			return cart;
		} 
		catch (Exception e) {
			System.out.println("Có lỗi: " + e);
			return cart;
		}
	}
	
	public HashMap<Long, CartDto> deleteCartItem(long id, HashMap<Long, CartDto> cart,String username) {
		try {
			if(cart.containsKey(id)) {
				cart.remove(id);
			}
			deleteBySession(id, username);
			return cart;
		} 
		catch (Exception e) {
			System.out.println("Có lỗi: " + e);
			return cart;
		}
	}
	
	public int totalQuantity(HashMap<Long, CartDto> cart) {
		int totalQuantity = 0;
		for(Map.Entry<Long, CartDto> item : cart.entrySet()) {
			if(item.getKey() != ((long)-1)) {
				totalQuantity += item.getValue().getQuantity();
			}
		}
		return totalQuantity;
	}
	
	public BigDecimal totalPrice(HashMap<Long, CartDto> cart) {
		BigDecimal totalPrice = new BigDecimal("0");
		for(Map.Entry<Long, CartDto> item : cart.entrySet()) {
			if(item.getKey() != ((long)-1)) {
				totalPrice = totalPrice.add(item.getValue().getTotalPrice());
			}
		}
		return totalPrice;
	}
	
	public int deleteCartByUsername(String username) {
		String sql = "DELETE FROM `cart` WHERE username='" + username + "'";
		int delete = jdbcTemplate.update(sql);
		return delete;
	}
	
	public List<Cart> getCartsDataByUsername(String username) {
		String sql = "SELECT * FROM `cart` WHERE username='" + username + "'";
		List<Cart> list = jdbcTemplate.query(sql, new CartMapper());
		return list;
	}
	
	public HashMap<Long, CartDto> createCartSession(List<Cart> cart, String username) {
		HashMap<Long, CartDto> cartSession = new HashMap<Long, CartDto>();
		for(Cart item : cart) {
			ProductDto product = productDao.getTheProductById(item.getProduct_id());
			if(product != null) {
				CartDto itemCart = new CartDto();
				itemCart.setProduct(product);
				itemCart.setQuantity(item.getQuantity());
				BigDecimal totalNoSaleOff = BigDecimal.valueOf(itemCart.getQuantity()).multiply(product.getPrice());
				if(product.getSale_off() > 0) {
					double saleoff = product.getSale_off() / 100.0;
					itemCart.setTotalPrice(totalNoSaleOff.subtract(totalNoSaleOff.multiply(BigDecimal.valueOf(saleoff).setScale(2, RoundingMode.HALF_UP))));
					updateBySession(itemCart, username);
				}
				else {
					itemCart.setTotalPrice(item.getTotal());
				}
				cartSession.put(item.getProduct_id(), itemCart);
			}
		}
		return cartSession;
	}
	
	public List<Cart> getCartData() {
		String sql = "SELECT * FROM `cart`";
		List<Cart> list = jdbcTemplate.query(sql, new CartMapper());
		return list;
	}
	
	public List<Cart> getCartDataPagination(int start, int limit) {
		String sql = "SELECT * FROM `cart` LIMIT " + start + ", " + limit;
		List<Cart> pagination = jdbcTemplate.query(sql, new CartMapper());
		return pagination;
	}
	
	public Cart getCartById(long id) {
		String sql = "SELECT * FROM `cart` WHERE id=" + id;
		Cart cart = jdbcTemplate.queryForObject(sql, new CartMapper());
		return cart;
	}
}
