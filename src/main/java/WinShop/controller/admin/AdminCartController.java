package WinShop.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import WinShop.dto.PaginationDto;
import WinShop.entity.Cart;
import WinShop.service.ICartService;
import WinShop.service.IPaginationService;
import WinShop.service.IProductService;

@Controller
public class AdminCartController extends AdminBaseController {
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private IProductService productcartService;
	
	@Autowired
	private IPaginationService paginationcartService;
	
	@RequestMapping("/admin/cart")
	public ModelAndView Index() {
		mv.setViewName("admin/cart/index");
		try {
			int totalData = cartService.getCartsData().size();
			PaginationDto paginatioInfo = paginationcartService.getInfoPagination(totalData, limitc, 1);
			List<Cart> pagination = cartService.getCartsDataPagination(paginatioInfo.getStart(), limitc);
			mv.addObject("paginationinfo", paginatioInfo);
			mv.addObject("pagination", pagination);
			mv.addObject("products", productcartService.getProductsData());
			mv.addObject("totaldata", totalData);
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping("/admin/cartP{current}")
	public ModelAndView IndexPagination(@PathVariable int current) {
		mv.setViewName("admin/cart/index");
		try {
			int totalData = cartService.getCartsData().size();
			PaginationDto paginatioInfo = paginationcartService.getInfoPagination(totalData, limitc, current);
			List<Cart> pagination = cartService.getCartsDataPagination(paginatioInfo.getStart(), limitc);
			mv.addObject("paginationinfo", paginatioInfo);
			mv.addObject("pagination", pagination);
			mv.addObject("products", productcartService.getProductsData());
			mv.addObject("totaldata", totalData);
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/createcart", method = RequestMethod.GET)
	public ModelAndView Create() {
		mv.setViewName("admin/cart/create");
		try {
			mv.addObject("newcart", new Cart());
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/createcart", method = RequestMethod.POST)
	public ModelAndView Create(@ModelAttribute("newcart") Cart cart) {
		mv.setViewName("admin/cart/create");
		try {
			cartService.create(cart);
			mv.setViewName("redirect:/admin/cart");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/editcart{id}", method = RequestMethod.GET)
	public ModelAndView Edit(@PathVariable long id) {
		mv.setViewName("admin/cart/edit");
		try {
			Cart cart = cartService.getCartById(id);
			mv.addObject("editcart", cart);
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/editcart{id}", method = RequestMethod.POST)
	public ModelAndView Edit(@PathVariable long id, @ModelAttribute("editcart") Cart cart) {
		mv.setViewName("admin/cart/edit");
		try {
			cartService.edit(cart);
			mv.setViewName("redirect:/admin/cart");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/deletecart{id}", method = RequestMethod.GET)
	public ModelAndView Delete(@PathVariable long id) {
		mv.setViewName("admin/cart/delete");
		try {
			mv.addObject("deletecart", cartService.getCartById(id));
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/deletecart{id}", method = RequestMethod.POST)
	public ModelAndView Delete(@PathVariable long id, @ModelAttribute("deletecart") Cart cart) {
		mv.setViewName("admin/cart/delete");
		try {
			cartService.delete(id);
			mv.setViewName("redirect:/admin/cart");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
}
