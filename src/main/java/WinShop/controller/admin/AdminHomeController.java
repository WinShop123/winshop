package WinShop.controller.admin;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import WinShop.entity.Account;
import WinShop.entity.Bill;
import WinShop.service.IBillService;

@Controller
public class AdminHomeController extends AdminBaseController {
	@Autowired
	private IBillService billService;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView Index(HttpSession session) {
		Account account = (Account) session.getAttribute("loginsession");
		if(account == null) {
			mv.setViewName("redirect:/login");
			return mv;
		}
		else {
			String role = (String) session.getAttribute("rolesession");
			int count = 0;
			if(role.equals("admin, user")) {
				count++;
			}
			if(count == 0) {
				mv.setViewName("redirect:/error/forbidden403");
				return mv;
			}
		}
		List<Bill> list = billService.latestBillsInAPeriodTime(30);
		BigDecimal revenue = new BigDecimal(0);
		int quantity = 0;
		for(Bill bill : list) {
			revenue = revenue.add(bill.getTotal());
			quantity += bill.getQuantity();
		}
		session.setAttribute("days", 30);
		session.setAttribute("revenue", revenue);
		session.setAttribute("soldproducts", quantity);
		mv.setViewName("admin/index");
		return mv;
	}
	
	@RequestMapping(value = "/admin/modifyday", method = RequestMethod.GET)
	@ResponseBody
	public String ModifyDay(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		int number = Integer.parseInt(request.getParameter("days"));
		BigDecimal revenue = new BigDecimal(0);
		int quantity = 0;
		if(number > 0) {
			List<Bill> list = billService.latestBillsInAPeriodTime(number);
			for(Bill bill : list) {
				revenue = revenue.add(bill.getTotal());
				quantity += bill.getQuantity();
			}
		}
		session.setAttribute("days", number);
		session.setAttribute("revenue", revenue);
		session.setAttribute("soldproducts", quantity);
		return "{\"days\":"+ number +  ",\"revenue\":" + revenue + ",\"soldproducts\":" + quantity + "}";
	}
	
	@RequestMapping("error/forbidden403")
	public ModelAndView forbidden403() {
		mv.setViewName("error/forbidden403");
		return mv;
	}
	
	@RequestMapping("admin/logout")
	public ModelAndView Logout(HttpSession session) {
		session.removeAttribute("loginsession");
		session.removeAttribute("rolesession");
		session.removeAttribute("cart");
		mv.setViewName("redirect:/login");
		return mv;
	}
}
