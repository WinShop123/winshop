package WinShop.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import WinShop.dto.PaginationDto;
import WinShop.dto.ProductDto;
import WinShop.entity.Product;
import WinShop.service.IPaginationService;
import WinShop.service.IProductService;

@Controller
public class UserProductController  extends UserBaseController {
	@Autowired
	IProductService productService;
	
	@Autowired
	IPaginationService paginationServie;
	
	private final int limit = 12;
	
	@RequestMapping("/all")
	public ModelAndView Index() {
		mv.setViewName("user/product/allproducts");
		int totalData = productService.getAllProductsDistinct().size();
		PaginationDto paginationInfo = new PaginationDto();
		List<ProductDto> pagination = new ArrayList<ProductDto>();
		if(totalData > 0) {
			paginationInfo = paginationServie.getInfoPagination(totalData, limit, 1);
			pagination = productService.getProductsPaginationForAll(paginationInfo.getStart(), limit);
		}
		mv.addObject("paginationinfo", paginationInfo);
		mv.addObject("pagination", pagination);
		return mv;
	}
	
	@RequestMapping("/all/p{current}")
	public ModelAndView IndexPagination(@PathVariable String current) {
		mv.setViewName("user/product/allproducts");
		int totalData = productService.getAllProductsDistinct().size();
		PaginationDto paginationInfo = new PaginationDto();
		List<ProductDto> pagination = new ArrayList<ProductDto>();
		if(totalData > 0) {
			paginationInfo = paginationServie.getInfoPagination(totalData, limit, Integer.parseInt(current));
			pagination = productService.getProductsPaginationForAll(paginationInfo.getStart(), limit);
		}
		mv.addObject("paginationinfo", paginationInfo);
		mv.addObject("pagination", pagination);
		return mv;
	}
	
	@RequestMapping("/category{id}")
	public ModelAndView ProductsCategory(@PathVariable String id) {
		mv.setViewName("user/product/category");
		int totalData = productService.getProductsByCategoryId(Integer.parseInt(id)).size();
		PaginationDto paginationInfo = new PaginationDto();
		List<ProductDto> pagination = new ArrayList<ProductDto>();
		if(totalData > 0) {
			paginationInfo = paginationServie.getInfoPagination(totalData, limit, 1);
			pagination = productService.getProductsPaginationForCategory(Integer.parseInt(id), paginationInfo.getStart(), limit);
		}
		mv.addObject("paginationinfo", paginationInfo);
		mv.addObject("pagination", pagination);
		mv.addObject("catid", Integer.parseInt(id));
		mv.addObject("category", categoryService.getCategoryByCatid(Integer.parseInt(id)));
		return mv;
	}
	
	@RequestMapping("/category{id}/p{current}")
	public ModelAndView ProductsCategoryPagination(@PathVariable String id,@PathVariable String current) {
		mv.setViewName("user/product/category");
		int totalData = productService.getProductsByCategoryId(Integer.parseInt(id)).size();
		PaginationDto paginationInfo = new PaginationDto();
		List<ProductDto> pagination = new ArrayList<ProductDto>();
		if(totalData > 0) {
			paginationInfo = paginationServie.getInfoPagination(totalData, limit, Integer.parseInt(current));
			pagination = productService.getProductsPaginationForCategory(Integer.parseInt(id), paginationInfo.getStart(), limit);
		}
		mv.addObject("paginationinfo", paginationInfo);
		mv.addObject("pagination", pagination);
		mv.addObject("catid", Integer.parseInt(id));
		mv.addObject("category", categoryService.getCategoryByCatid(Integer.parseInt(id)));
		return mv;
	}
	
	@RequestMapping("/details/{id}")
	public ModelAndView ProductDetails(@PathVariable long id) {
		mv.setViewName("user/product/details");
		int catid = productService.getTheProductById(id).getCategory_id();
		mv.addObject("productdetails", productService.getTheProductById(id));
		mv.addObject("productimages", productService.getImagesOfTheProduct(id));
		mv.addObject("relatedproducts", productService.getRelatedProductByCategoryId(catid, id));
		return mv;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchProducts(@ModelAttribute("searchobject") Product product) {
		mv.setViewName("user/product/search");
		try {
			String searchString = product.getName().trim();
			List<Product> all = productService.getProductsData();
			List<Product> search = productService.searchProducts(searchString);
			if(all.size() == search.size()) {
				searchString = "";
			}
			int totalData = productService.searchProducts(searchString).size();
			PaginationDto paginationInfo = paginationServie.getInfoPagination(totalData, limit, 1);
			List<Product> pagination = productService.searchProductsPagination(searchString, paginationInfo.getStart(), limit);
			mv.addObject("paginationinfo", paginationInfo);
			mv.addObject("pagination", pagination);
			mv.addObject("totaldata", totalData);
			mv.addObject("searchstring", searchString);
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			return mv;
		}
	}
	
	@RequestMapping(value = "/search{searchString}/p{current}", method = RequestMethod.GET)
	public ModelAndView searchProductsPagination(@PathVariable String searchString, @PathVariable int current) {
		mv.setViewName("user/product/search");
		int totalData = productService.searchProducts(searchString).size();
		PaginationDto paginationInfo = paginationServie.getInfoPagination(totalData, limit, current);
		List<Product> pagination = productService.searchProductsPagination(searchString, paginationInfo.getStart(), limit);
		mv.addObject("paginationinfo", paginationInfo);
		mv.addObject("pagination", pagination);
		mv.addObject("totaldata", totalData);
		return mv;
	}
}
