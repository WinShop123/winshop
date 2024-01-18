package WinShop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import WinShop.dto.ProductDto;
import WinShop.entity.Product;

@Service
public interface IProductService {

	public List<ProductDto> get16ProductsDistinct();
	
	public List<ProductDto> getNewProducts();
	
	public List<ProductDto> getTopSellProducts();
	
	public List<ProductDto> getAllProductsDistinct();
	
	public List<ProductDto> getProductsByCategoryId(int category_id);
	
	public List<ProductDto> getProductsPaginationForAll(int start, int limit);
	
	public List<ProductDto> getProductsPaginationForCategory(int category_id, int start, int limit);
	
	public ProductDto getTheProductById(long product_id);
	
	public List<ProductDto> getImagesOfTheProduct(long product_id);
	
	public List<ProductDto> getRelatedProductByCategoryId(int category_id, long product_id);
	
	public int create(Product product);
	
	public int edit(Product product);
	
	public int delete(long id);
	
	public List<Product> getProductsData();
	
	public List<Product> getProductsDataPagiantion(int start, int limit);
	
	public Product getProductById(long id);
	
	public List<String> getImagesByCategoryId(int category_id);
	
	public List<Long> getProductIdsByCategoryId(int category_id);
	
	public List<Product> searchProducts(String searchString);
	
	public List<Product> searchProductsPagination(String searchString, int start, int limit);
	
	public int updateQuantity(long id, int quantity);
	
	public int updateNewProducts();
	
	public int updateTopSellProducts(long id);
	
	public int updateNotTopSellProducts(long id);
}
