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
import WinShop.entity.Role;
import WinShop.service.IAccountService;
import WinShop.service.IPaginationService;
import WinShop.service.IRoleService;

@Controller
public class AdminRoleController extends AdminBaseController {
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IPaginationService paginationService;
	
	@RequestMapping("/admin/role")
	public ModelAndView Index() {
		mv.setViewName("admin/role/index");
		try {
			int totalData = roleService.getRolesData().size();
			PaginationDto paginatioInfo = paginationService.getInfoPagination(totalData, limitr, 1);
			List<WinShop.entity.Role> pagination = roleService.getRolesDataPagination(paginatioInfo.getStart(), limitr);
			mv.addObject("paginationinfo", paginatioInfo);
			mv.addObject("pagination", pagination);
			mv.addObject("accounts", accountService.getAccountsData());
			mv.addObject("totaldata", totalData);
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping("/admin/roleP{current}")
	public ModelAndView IndexPagination(@PathVariable int current) {
		mv.setViewName("admin/role/index");
		try {
			int totalData = roleService.getRolesData().size();
			PaginationDto paginatioInfo = paginationService.getInfoPagination(totalData, limitr, current);
			List<Role> pagination = roleService.getRolesDataPagination(paginatioInfo.getStart(), limitr);
			mv.addObject("paginationinfo", paginatioInfo);
			mv.addObject("pagination", pagination);
			mv.addObject("accounts", accountService.getAccountsData());
			mv.addObject("totaldata", totalData);
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/createrole", method = RequestMethod.GET)
	public ModelAndView Create() {
		mv.setViewName("admin/role/create");
		try {
			mv.addObject("newrole", new Role());
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/createrole", method = RequestMethod.POST)
	public ModelAndView Create(@ModelAttribute("newrole") Role role) {
		mv.setViewName("admin/role/create");
		try {
			roleService.create(role);
			mv.setViewName("redirect:/admin/role");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/editrole{id}", method = RequestMethod.GET)
	public ModelAndView Edit(@PathVariable long id) {
		mv.setViewName("admin/role/edit");
		try {
			mv.addObject("editrole", roleService.getRoleById(id));
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/editrole{id}", method = RequestMethod.POST)
	public ModelAndView Edit(@PathVariable long id, @ModelAttribute("editrole") Role role) {
		mv.setViewName("admin/role/edit");
		try {
			roleService.edit(role);
			mv.setViewName("redirect:/admin/role");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/deleterole{id}", method = RequestMethod.GET)
	public ModelAndView Delete(@PathVariable long id) {
		mv.setViewName("admin/role/delete");
		try {
			mv.addObject("deleterole", roleService.getRoleById(id));
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/deleterole{id}", method = RequestMethod.POST)
	public ModelAndView Delete(@PathVariable long id, @ModelAttribute("deleterole") Role role) {
		mv.setViewName("admin/role/delete");
		try {
			roleService.delete(id);
			mv.setViewName("redirect:/admin/role");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
}
