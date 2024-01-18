package WinShop.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import WinShop.entity.Product;
import WinShop.service.ICategoryService;
import WinShop.service.IImageService;
import WinShop.service.IPaginationService;
import WinShop.service.IProductService;

@Controller
public class AdminProductController extends AdminBaseController {
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IImageService imageService;
	
	@Autowired
	private IPaginationService paginationService;
	
	@RequestMapping("/admin/product")
	public ModelAndView Index() {
		mv.setViewName("admin/product/index");
		try {
			int totalData = productService.getProductsData().size();
			PaginationDto paginationInfo = paginationService.getInfoPagination(totalData, limitp, 1);
			List<Product> pagination = productService.getProductsDataPagiantion(paginationInfo.getStart(), limitp);
			mv.addObject("paginationinfo", paginationInfo);
			mv.addObject("pagination", pagination);
			mv.addObject("categories", categoryService.getCategoriesData());
			mv.addObject("totaldata", totalData);
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping("/admin/productP{current}")
	public ModelAndView IndexPagination(@PathVariable int current) {
		mv.setViewName("admin/product/index");
		try {
			int totalData = productService.getProductsData().size();
			PaginationDto paginationInfo = paginationService.getInfoPagination(totalData, limitp, current);
			List<Product> pagination = productService.getProductsDataPagiantion(paginationInfo.getStart(), limitp);
			mv.addObject("paginationinfo", paginationInfo);
			mv.addObject("pagination", pagination);
			mv.addObject("categories", categoryService.getCategoriesData());
			mv.addObject("totaldata", totalData);
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/createproduct", method = RequestMethod.GET)
	public ModelAndView Create() {
		mv.setViewName("admin/product/create");
		mv.addObject("newproduct", new Product());
		return mv;
	}
	
	@RequestMapping(value = "/admin/createproduct", method = RequestMethod.POST)
	public ModelAndView Create(@ModelAttribute("newproduct") Product product, HttpSession s, @RequestParam("imageupload") CommonsMultipartFile file) {
		mv.setViewName("admin/product/create");
		try {
			byte[] data = file.getBytes();
			//Upload directly to project folder
			//String path = "D:\\Java\\LearnJavaWeb\\HiroShop\\src\\main\\webapp\\assets\\img\\products\\" + file.getOriginalFilename();
			if(data.length > 0) {
				String path = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
						+ "products" + File.separator + file.getOriginalFilename();
				FileOutputStream os = new FileOutputStream(path);
				os.write(data);
				os.close();
			}
			
			product.setImage(file.getOriginalFilename());
			product.setCreateat(formattedCurrentDate());
			product.setIs_new(true);
			product.setIs_topsell(false);
			productService.create(product);
			mv.setViewName("redirect:/admin/product");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/editproduct{id}", method = RequestMethod.GET)
	public ModelAndView Edit(@PathVariable long id) {
		mv.setViewName("admin/product/edit");
		mv.addObject("editproduct", productService.getProductById(id));
		return mv;
	}
	
	@RequestMapping(value = "/admin/editproduct{id}", method = RequestMethod.POST)
	public ModelAndView Edit(@PathVariable long id, @ModelAttribute("editproduct") Product product, @RequestParam("imageupload") CommonsMultipartFile file, HttpSession s) {
		mv.setViewName("admin/product/edit");
		try {
			byte[] data = file.getBytes();
			product.setImage(productService.getProductById(id).getImage());
			product.setCreateat(productService.getProductById(id).getCreateat());
			if(data.length > 0) {
				String path = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
						+ "products" + File.separator + file.getOriginalFilename();
				FileOutputStream os = new FileOutputStream(path);
				os.write(data);
				os.close();
				product.setImage(file.getOriginalFilename());
				String currentPath = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
						+ "products" + File.separator + productService.getProductById(id).getImage();
				productService.edit(product);
				File imageFile = new File(currentPath); 
				if(imageFile.exists()) { 
					imageFile.delete(); 
				}	
			}
			mv.setViewName("redirect:/admin/product");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/deleteproduct{id}", method = RequestMethod.GET)
	public ModelAndView Delete(@PathVariable long id) {
		mv.setViewName("admin/product/delete");
		mv.addObject("deleteproduct", productService.getProductById(id));
		return mv;
	}
	
	@RequestMapping(value = "/admin/deleteproduct{id}", method = RequestMethod.POST)
	public ModelAndView Delete(@PathVariable long id, @ModelAttribute("deleteproduct") Product product, HttpSession s) {
		mv.setViewName("admin/product/delete");
		try {
			List<String> images = imageService.getImagesStringByProductId(id);
			String imagePath = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
			+ "products" + File.separator + productService.getProductById(id).getImage();
			productService.delete(id);
			File imageFile = new File(imagePath);
			if(imageFile.exists()) {
				imageFile.delete();
			}
			for(String item : images) {
				String dbImage = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
						+ "products" + File.separator + item;
				File deleteImage = new File(dbImage);
				if(deleteImage.exists()) {
					deleteImage.delete();
				}
			}
			mv.setViewName("redirect:/admin/product");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	public String formattedCurrentDate() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String nowString = now.format(df);
		return nowString;
	}
}
