package WinShop.controller.user;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import WinShop.entity.Product;
import WinShop.service.ICategoryService;

@Controller
public class UserBaseController {
	public ModelAndView mv = new ModelAndView();
	
	@Autowired
	public ICategoryService categoryService; 
	
	
	@PostConstruct
	public ModelAndView init() {
		mv.addObject("categories", categoryService.getCategoriesData());	
		mv.addObject("searchobject", new Product());
		return mv;
	}
}
