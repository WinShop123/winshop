package WinShop.controller.user;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import WinShop.dto.CartDto;
import WinShop.dto.ProductDto;
import WinShop.entity.Account;
import WinShop.entity.Cart;
import WinShop.entity.Role;
import WinShop.service.IBillDetailsService;
import WinShop.service.ICartService;
import WinShop.service.IProductService;
import WinShop.service.ISlideService;

@Controller
public class UserHomeController extends UserBaseController {
	
	@Autowired
	private ISlideService slideService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IBillDetailsService billdetailsDervice;
	
	@Autowired
	private ICartService cartService;
	
	
	@RequestMapping(value = { "/", "/hompage" })
	public ModelAndView Index(HttpSession session) {
		mv.setViewName("user/index");
		productService.updateNewProducts();
	
		List<Long> topsellProducts = billdetailsDervice.topSellProducts();
		for(Long id : topsellProducts) {
			productService.updateTopSellProducts(id);
		}
		List<Long> notTopSellProducts = billdetailsDervice.notTopSellProducts();
		for(Long id : notTopSellProducts) {
			productService.updateNotTopSellProducts(id);
		}
		
		List<ProductDto> c = productService.getNewProducts();
		System.out.println(c.size());
		
		String computerName = "";
		
		try {
			InetAddress localMachine = InetAddress.getLocalHost();
			computerName = localMachine.getHostName();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Account loginedAccount = (Account) session.getAttribute("loginsession");
		
		if(loginedAccount == null) {
			Account account = new Account();
			account.setUsername(computerName);
			account.setName("");
			
			session.setAttribute("loginsession", account);
			
			Role role = new Role();
			role.setRole("");
			session.setAttribute("rolesession", role);
			List<Cart> list = cartService.getCartsDataByUsername(account.getUsername());
			HashMap<Long, CartDto> cart = new HashMap<Long, CartDto>();
			if(!list.isEmpty()) {
				cart = cartService.createCartSession(list, account.getUsername());
				session.setAttribute("cart", cart);
				session.setAttribute("totalQuantity", cartService.totalQuantity(cart));
				session.setAttribute("totalPrice", cartService.totalPrice(cart));
			}
		}
		
		mv.addObject("isHomepage", "Homepage");
		mv.addObject("slides", slideService.getSlidesData());
		mv.addObject("products", productService.get16ProductsDistinct());
		mv.addObject("newproducts", productService.getNewProducts());
		mv.addObject("topsellproducts", productService.getTopSellProducts());
		return mv;
	}
}
