package WinShop.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
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
import WinShop.entity.Image;
import WinShop.service.IImageService;
import WinShop.service.IPaginationService;
import WinShop.service.IProductService;

@Controller
public class AdminImageController extends AdminBaseController {
	@Autowired
	IImageService imageService;
	
	@Autowired
	IProductService productService;
	
	@Autowired
	IPaginationService paginationService;
	
	@RequestMapping("/admin/image")
	public ModelAndView Index() {
		mv.setViewName("admin/image/index");
		try {
			int totalData = imageService.getImagesData().size();
			PaginationDto paginationInfo = paginationService.getInfoPagination(totalData, limiti, 1);
			List<Image> pagination = imageService.getImagesDataPagination(paginationInfo.getStart(), limiti);
			mv.addObject("paginationinfo", paginationInfo);
			mv.addObject("pagination", pagination);
			mv.addObject("totaldata", totalData);
			mv.addObject("products", productService.getProductsData());
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping("/admin/imageP{current}")
	public ModelAndView IndexPagination(@PathVariable int current) {
		mv.setViewName("admin/image/index");
		try {
			int totalData = imageService.getImagesData().size();
			PaginationDto paginationInfo = paginationService.getInfoPagination(totalData, limiti, current);
			List<Image> pagination = imageService.getImagesDataPagination(paginationInfo.getStart(), limiti);
			mv.addObject("paginationinfo", paginationInfo);
			mv.addObject("pagination", pagination);
			mv.addObject("totaldata", totalData);
			mv.addObject("products", productService.getProductsData());
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/createimage", method = RequestMethod.GET)
	public ModelAndView Create() {
		mv.setViewName("admin/image/create");
		mv.addObject("newimage", new Image());
		return mv;
	}
	
	@RequestMapping(value = "/admin/createimage", method = RequestMethod.POST)
	public ModelAndView Create(@ModelAttribute("newimage") Image image, @RequestParam("imageupload") CommonsMultipartFile file, HttpSession s) {
		mv.setViewName("admin/image/create");
		try {
			byte[] data = file.getBytes();
			String path = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
					+ "products" + File.separator + file.getOriginalFilename();
			FileOutputStream os = new FileOutputStream(path);
			os.write(data);
			os.close();
			image.setImage(file.getOriginalFilename());
			imageService.create(image);
			mv.setViewName("redirect:/admin/image");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/editimage{id}", method = RequestMethod.GET)
	public ModelAndView Edit(@PathVariable long id) {
		mv.setViewName("admin/image/edit");
		try {
			mv.addObject("editimage", imageService.getImageById(id));
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/editimage{id}", method = RequestMethod.POST)
	public ModelAndView Edit(@PathVariable long id, @ModelAttribute("editimage") Image image, @RequestParam("imageupload") CommonsMultipartFile file, HttpSession s) {
		mv.setViewName("admin/image/edit");
		try {
			byte[] data = file.getBytes();
			image.setImage(imageService.getImageById(id).getImage());
			if(data.length > 0) {
				String path = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
						+ "products" + File.separator + file.getOriginalFilename();
				FileOutputStream os = new FileOutputStream(path);
				os.write(data);
				os.close();
				image.setImage(file.getOriginalFilename());
				String dbImagePath = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
						+ "products" + File.separator + imageService.getImageById(id).getImage();
				imageService.edit(image);
				File dbImage = new File(dbImagePath);
				if(dbImage.exists()) {
					dbImage.delete();
				}
				mv.setViewName("redirect:/admin/image");
			}
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/deleteimage{id}", method = RequestMethod.GET)
	public ModelAndView Delete(@PathVariable long id) {
		mv.setViewName("admin/image/delete");
		try {
			mv.addObject("deleteimage", imageService.getImageById(id));
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/deleteimage{id}", method = RequestMethod.POST)
	public ModelAndView Delete(@PathVariable long id, @ModelAttribute("deleteimage") Image image, HttpSession s) {
		mv.setViewName("admin/image/delete");
		try {
			String dbImagePath = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator 
					+ "products" + File.separator + imageService.getImageById(id).getImage();
			imageService.delete(id);
			File dbImage = new File(dbImagePath);
			if(dbImage.exists()) {
				dbImage.delete();
			}
			mv.setViewName("redirect:/admin/image");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
}
