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
import WinShop.entity.Slide;
import WinShop.service.IPaginationService;
import WinShop.service.ISlideService;

@Controller
public class AdminSlideController extends AdminBaseController {
	@Autowired
	private ISlideService slideService;
	
	@Autowired
	private IPaginationService paginationService;
	
	@RequestMapping("/admin/slide")
	public ModelAndView Index() {
		mv.setViewName("admin/slide/index");
		try {
			int totalData = slideService.getSlidesData().size();
			PaginationDto paginatioInfo = paginationService.getInfoPagination(totalData, limit, 1);
			List<Slide> pagination = slideService.getSlidesDataPagination(paginatioInfo.getStart(), limit);
			mv.addObject("paginationinfo", paginatioInfo);
			mv.addObject("pagination", pagination);
			mv.addObject("totaldata", totalData);
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping("/admin/slideP{current}")
	public ModelAndView IndexPagination(@PathVariable int current) {
		mv.setViewName("admin/slide/index");
		try {
			int totalData = slideService.getSlidesData().size();
			PaginationDto paginatioInfo = paginationService.getInfoPagination(totalData, limit, current);
			List<Slide> pagination = slideService.getSlidesDataPagination(paginatioInfo.getStart(), limit);
			mv.addObject("paginationinfo", paginatioInfo);
			mv.addObject("pagination", pagination);
			mv.addObject("totaldata", totalData);
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/createslide", method = RequestMethod.GET)
	public ModelAndView Create() {
		mv.setViewName("admin/slide/create");
		try {
			mv.addObject("newslide", new Slide());
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/createslide", method = RequestMethod.POST)
	public ModelAndView Create(@ModelAttribute("newslide") Slide slide, HttpSession s, @RequestParam("imageupload") CommonsMultipartFile file) {
		mv.setViewName("admin/slide/create");
		try {
			byte[] data = file.getBytes();
			String path = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator
					+ "slides" + File.separator + file.getOriginalFilename();
			FileOutputStream os = new FileOutputStream(path);
			os.write(data);
			os.close();
			slide.setImage(file.getOriginalFilename());
			slideService.create(slide);
			mv.setViewName("redirect:/admin/slide");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/editslide{id}", method = RequestMethod.GET)
	public ModelAndView Edit(@PathVariable long id) {
		mv.setViewName("admin/slide/edit");
		try {
			mv.addObject("editslide", slideService.getSlideById(id));
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/editslide{id}", method = RequestMethod.POST)
	public ModelAndView Edit(@PathVariable long id, @ModelAttribute("editslide") Slide slide , HttpSession s,
			@RequestParam("imageupload") CommonsMultipartFile file) {
		mv.setViewName("admin/slide/edit");
		try {
			byte[] data = file.getBytes();
			slide.setImage(slideService.getSlideById(id).getImage());
			if(data.length > 0) {
				String path = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator
						+ "slides" + File.separator + file.getOriginalFilename();
				FileOutputStream os = new FileOutputStream(path);
				os.write(data);
				os.close();
				slide.setImage(file.getOriginalFilename());
				String dbPath = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator
						+ "slides" + File.separator + slideService.getSlideById(id).getImage();
				slideService.edit(slide);
				File dbImage = new File(dbPath);
				if(dbImage.exists()) {
					dbImage.delete();
				}
			}		
			mv.setViewName("redirect:/admin/slide");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/deleteslide{id}", method = RequestMethod.GET)
	public ModelAndView Delete(@PathVariable long id) {
		mv.setViewName("admin/slide/delete");
		try {
			mv.addObject("deleteslide", slideService.getSlideById(id));
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/admin/deleteslide{id}", method = RequestMethod.POST)
	public ModelAndView Delete(@PathVariable long id, @ModelAttribute("deleteslide") Slide slide, HttpSession s) {
		mv.setViewName("admin/slide/delete");
		try {
			String path = s.getServletContext().getRealPath("/") + "assets" + File.separator + "img" + File.separator
					+ "slides" + File.separator + slideService.getSlideById(id).getImage();
			slideService.delete(id);
			File image = new File(path);
			if(image.exists()) {
				image.delete();
			}
			mv.setViewName("redirect:/admin/slide");
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
}
