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
import WinShop.entity.BillDetails;
import WinShop.service.IBillDetailsService;
import WinShop.service.IBillService;
import WinShop.service.IPaginationService;
import WinShop.service.IProductService;

@Controller
public class AdminBillDetailsController extends AdminBaseController {
	@Autowired
	private IBillDetailsService billdetailsService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IBillService billService;
	
	@Autowired
	private IPaginationService paginationService;
	
	@RequestMapping("/admin/billdetails")
	public ModelAndView Index() {
		mv.setViewName("admin/billdetails/index");
		try {
			int totalData = billdetailsService.getBillDetailsData().size();
			PaginationDto paginatioInfo = paginationService.getInfoPagination(totalData, limitbd, 1);
			List<BillDetails> pagination = billdetailsService.getBillDetailsDataPagination(paginatioInfo.getStart(), limitbd);
			mv.addObject("paginationinfo", paginatioInfo);
			mv.addObject("pagination", pagination);
			mv.addObject("products", productService.getProductsData());
			mv.addObject("bills", billService.getBillsData());
			mv.addObject("totaldata", totalData);
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping("/admin/billdetailsP{current}")
	public ModelAndView IndexPagination(@PathVariable int current) {
		mv.setViewName("admin/billdetails/index");
		try {
			int totalData = billdetailsService.getBillDetailsData().size();
			PaginationDto paginatioInfo = paginationService.getInfoPagination(totalData, limitbd, current);
			List<BillDetails> pagination = billdetailsService.getBillDetailsDataPagination(paginatioInfo.getStart(), limitbd);
			mv.addObject("paginationinfo", paginatioInfo);
			mv.addObject("pagination", pagination);
			mv.addObject("products", productService.getProductsData());
			mv.addObject("bills", billService.getBillsData());
			mv.addObject("totaldata", totalData);
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/createbilldetails", method = RequestMethod.GET)
	public ModelAndView Create() {
		mv.setViewName("admin/billdetails/create");
		try {
			mv.addObject("newbilldetails", new BillDetails());
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/createbilldetails", method = RequestMethod.POST)
	public ModelAndView Create(@ModelAttribute("newbilldetails") BillDetails billdetails) {
		mv.setViewName("admin/billdetails/create");
		try {
			billdetailsService.addBillDetails(billdetails);
			mv.setViewName("redirect:/admin/billdetails");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/editbilldetails{id}", method = RequestMethod.GET)
	public ModelAndView Edit(@PathVariable long id) {
		mv.setViewName("admin/billdetails/edit");
		try {
			mv.addObject("editbilldetails", billdetailsService.getBillDetailsById(id));
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/editbilldetails{id}", method = RequestMethod.POST)
	public ModelAndView Edit(@PathVariable long id, @ModelAttribute("editbilldetails") BillDetails billdetails) {
		mv.setViewName("admin/billdetails/edit");
		try {
			billdetailsService.edit(billdetails);
			mv.setViewName("redirect:/admin/billdetails");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/deletebilldetails{id}", method = RequestMethod.GET)
	public ModelAndView Delete(@PathVariable long id) {
		mv.setViewName("admin/billdetails/delete");
		try {
			mv.addObject("deletebilldetails", billdetailsService.getBillDetailsById(id));
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/deletebilldetails{id}", method = RequestMethod.POST)
	public ModelAndView Delete(@PathVariable long id, @ModelAttribute("deletebilldetails") BillDetails billdetails) {
		mv.setViewName("admin/billdetails/delete");
		try {
			billdetailsService.delete(id);
			mv.setViewName("redirect:/admin/billdetails");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
}
