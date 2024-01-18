package WinShop.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import WinShop.entity.Account;
import WinShop.service.IBillDetailsService;
import WinShop.service.IBillService;
import WinShop.service.IProductService;
import WinShop.entity.BillDetails;

@Controller
public class UserBillController extends UserBaseController {
	@Autowired
	private IBillService billService;
	
	@Autowired
	private IBillDetailsService billdetailsService;
	
	@Autowired
	private IProductService productService;
	
	@RequestMapping("/bill")
	public ModelAndView Index(HttpSession session) {
		mv.setViewName("user/bill/bill");
		Account logined = (Account) session.getAttribute("loginsession");
		mv.addObject("bills", billService.getBillsByUsername(logined.getUsername()));
		return mv;
	}
	
	@RequestMapping("/billdetails/{id}")
	public ModelAndView BillDetails(@PathVariable long id) {
		mv.setViewName("user/bill/billdetails");
		mv.addObject("billdetails", billdetailsService.getBillDetailsByBillId(id));
		mv.addObject("products", productService.getProductsData());
		return mv;
	}
	
	@RequestMapping("/deletebill{id}")
	public String DeleteBill(@PathVariable long id, HttpServletRequest request) {
		List<BillDetails> list = billdetailsService.getBillDetailsByBillId(id);
		for(BillDetails item : list) {
			productService.updateQuantity(item.getProduct_id(), productService.getProductById(item.getProduct_id()).getQuantity() + item.getQuantity());
		}
		billService.delete(id);
		return "redirect:" + request.getHeader("Referer");
	}
}
