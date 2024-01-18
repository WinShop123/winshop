package WinShop.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import WinShop.dto.PaginationDto;
import WinShop.entity.Category;
import WinShop.service.ICategoryService;
import WinShop.service.IImageService;
import WinShop.service.IPaginationService;
import WinShop.service.IProductService;

@Controller
public class AdminCategoryController extends AdminBaseController {
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IImageService imageService;
	
	@Autowired
	private IPaginationService paginationService;
	
	@RequestMapping("/admin/category")
	public ModelAndView Index() {
		mv.setViewName("admin/category/index");
		try {
			int totalData = categoryService.getCategoriesData().size();
			PaginationDto paginationIfo = paginationService.getInfoPagination(totalData, limit, 1);
			List<Category> pagination = categoryService.getCategoriesDataPagination(paginationIfo.getStart(), limit);
			mv.addObject("paginationinfo", paginationIfo);
			mv.addObject("totaldata", totalData);
			mv.addObject("pagination", pagination);
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping("/admin/categoryP{current}")
	public ModelAndView IndexPagination(@PathVariable int current) {
		mv.setViewName("admin/category/index");
		try {
			int totalData = categoryService.getCategoriesData().size();
			PaginationDto paginationIfo = paginationService.getInfoPagination(totalData, limit, current);
			List<Category> pagination = categoryService.getCategoriesDataPagination(paginationIfo.getStart(), limit);
			mv.addObject("paginationinfo", paginationIfo);
			mv.addObject("pagination", pagination);
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/createcategory", method = RequestMethod.GET)
	public ModelAndView Create() {
		mv.setViewName("admin/category/create");
		mv.addObject("newcategory", new Category());
		return mv;
	}
	
	@RequestMapping(value="/admin/createcategory", method = RequestMethod.POST)
	public ModelAndView Create(@ModelAttribute("newcategory") Category category, @RequestParam("logoupload") CommonsMultipartFile logo, @RequestParam("iconupload") CommonsMultipartFile icon, HttpSession s) {
		mv.setViewName("/admin/category/create");
		try {
			byte[] dataLogo = logo.getBytes();
			byte[] dataIcon = icon.getBytes();
			String logoSetPath = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
					+ "categories" + File.separator + logo.getOriginalFilename();
			String iconSetPath = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
					+ "categories" + File.separator + icon.getOriginalFilename();
			FileOutputStream oslogo = new FileOutputStream(logoSetPath);
			oslogo.write(dataLogo);
			oslogo.close();
			FileOutputStream osicon = new FileOutputStream(iconSetPath);
			osicon.write(dataIcon);
			osicon.close();
			category.setLogo(logo.getOriginalFilename());
			category.setIcon(icon.getOriginalFilename());
			categoryService.create(category);
			mv.setViewName("redirect:/admin/category");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/editcategory{id}", method = RequestMethod.GET)
	public ModelAndView Edit(@PathVariable int id) {
		mv.setViewName("/admin/category/edit");
		mv.addObject("editcategory", categoryService.getCategoryByCatid(id));
		return mv;
	}
	
	@RequestMapping(value = "/admin/editcategory{id}", method = RequestMethod.POST)
	public ModelAndView Edit(@PathVariable int id, @ModelAttribute("editcategory") Category category, @RequestParam("logoupload") CommonsMultipartFile logo, @RequestParam("iconupload") CommonsMultipartFile icon, HttpSession s) {
		mv.setViewName("/admin/category/edit");
		try {
			byte[] dataLogo = logo.getBytes();
			byte[] dataIcon = icon.getBytes();
//			String logoSetPath = "D:\\Java\\LearnJavaWeb\\HiroShop\\src\\main\\webapp\\assets\\img\\categories\\" + logo.getOriginalFilename();
//			String iconSetPath = "D:\\Java\\LearnJavaWeb\\HiroShop\\src\\main\\webapp\\assets\\img\\categories\\" + icon.getOriginalFilename();
			String logoSetPath = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
					+ "categories" + File.separator + logo.getOriginalFilename();
			String iconSetPath = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
					+ "categories" + File.separator + icon.getOriginalFilename();
			category.setLogo(categoryService.getCategoryByCatid(id).getLogo());
			category.setIcon(categoryService.getCategoryByCatid(id).getIcon());
			if(dataLogo.length > 0) {
				FileOutputStream oslogo = new FileOutputStream(logoSetPath);
				oslogo.write(dataLogo);
				oslogo.close();
				category.setLogo(logo.getOriginalFilename());
			}
			if(dataIcon.length > 0) {
				FileOutputStream osicon = new FileOutputStream(iconSetPath);
				osicon.write(dataIcon);
				osicon.close();
				category.setIcon(icon.getOriginalFilename());
			}
//			String dbLogo = "D:\\Java\\LearnJavaWeb\\HiroShop\\src\\main\\webapp\\assets\\img\\categories\\" + categoryService.getCategoryByCatid(id).getLogo();
//			String dbIcon = "D:\\Java\\LearnJavaWeb\\HiroShop\\src\\main\\webapp\\assets\\img\\categories\\" + categoryService.getCategoryByCatid(id).getIcon();
			String dbLogo = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
					+ "categories" + File.separator + categoryService.getCategoryByCatid(id).getLogo();
			String dbIcon = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
					+ "categories" + File.separator + categoryService.getCategoryByCatid(id).getIcon();
			categoryService.edit(category);
			if(dataLogo.length > 0) {
				File logoImage = new File(dbLogo);
				if(logoImage.exists()) {
					logoImage.delete();
				}
			}
			if(dataIcon.length > 0) {
				File iconImage = new File(dbIcon);
				if(iconImage.exists()) {
					iconImage.delete();
				}
			}
			mv.setViewName("redirect:/admin/category");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/deletecategory{id}", method = RequestMethod.GET)
	public ModelAndView Delete(@PathVariable int id) {
		mv.setViewName("admin/category/delete");
		mv.addObject("deletecategory", categoryService.getCategoryByCatid(id));
		return mv;
	}
	
	@RequestMapping(value = "/admin/deletecategory{id}", method = RequestMethod.POST)
	public ModelAndView Delete(@PathVariable int id, @ModelAttribute("deletecategory") Category category, HttpSession s) {
		mv.setViewName("admin/category/delete");
		try {
			List<String> imagesp = productService.getImagesByCategoryId(id);
			List<Long> ids = productService.getProductIdsByCategoryId(id);
			List<String> imagesi = new ArrayList<String>();
			for(Long item : ids) {
				List<String> imagesbyid = imageService.getImagesStringByProductId(item);
				for(String imagebyid : imagesbyid) {
					imagesi.add(imagebyid);
				}
			}
			String dbLogo = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
					+ "categories" + File.separator + categoryService.getCategoryByCatid(id).getLogo();
			String dbIcon = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
					+ "categories" + File.separator + categoryService.getCategoryByCatid(id).getIcon();
			categoryService.delete(id);
			File logoImage = new File(dbLogo);
			File iconImage = new File(dbIcon);
			if(logoImage.exists()) {
				logoImage.delete();
			}
			if(iconImage.exists()) {
				iconImage.delete();
			}
			for(String item : imagesp) {
				String path = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
						+ "products" + File.separator + item;
				File image = new File(path);
				if(image.exists()) {
					image.delete();
				}
			}
			for(String item : imagesi) {
				String path = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
						+ "products" + File.separator + item;
				File image = new File(path);
				if(image.exists()) {
					image.delete();
				}
			}
			mv.setViewName("redirect:/admin/category");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
}
