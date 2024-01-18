package WinShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WinShop.dao.ProductDao;
import WinShop.dto.ProductDto;
import WinShop.entity.Product;

@Service
public class ProductServiceImplement implements IProductService {

	@Autowired
	private ProductDao productDao;

	public List<ProductDto> get16ProductsDistinct() {
		return productDao.get16ProductsDistinct();
	}

	public List<ProductDto> getNewProducts() {
		return productDao.getNewProducts();
	}

	public List<ProductDto> getTopSellProducts() {
		return productDao.getTopSellProducts();
	}

	public List<ProductDto> getAllProductsDistinct() {
		return productDao.getAllProductsDistinct();
	}

	public List<ProductDto> getProductsByCategoryId(int category_id) {
		return productDao.getProductsByCategoryId(category_id);
	}

	public List<ProductDto> getProductsPaginationForAll(int start, int limit) {
		return productDao.getProductsPaginationForAll(start, limit);
	}

	public List<ProductDto> getProductsPaginationForCategory(int category_id, int start, int limit) {
		return productDao.getProductsPaginationForCategory(category_id, start, limit);
	}

	public ProductDto getTheProductById(long product_id) {
		return productDao.getTheProductById(product_id);
	}

	public List<ProductDto> getImagesOfTheProduct(long product_id) {
		return productDao.getImagesOfTheProduct(product_id);
	}

	public List<ProductDto> getRelatedProductByCategoryId(int category_id, long product_id) {
		return productDao.getRelatedProductsByCategoryId(category_id, product_id);
	}

	public int create(Product product) {
		return productDao.create(product);
	}

	public int edit(Product product) {
		return productDao.edit(product);
	}

	public int delete(long id) {
		return productDao.delete(id);
	}

	public List<Product> getProductsData() {
		return productDao.getProductsData();
	}

	public List<Product> getProductsDataPagiantion(int start, int limit) {
		return productDao.getProductsDataPagination(start, limit);
	}

	public Product getProductById(long id) {
		return productDao.getProductById(id);
	}

	public List<String> getImagesByCategoryId(int category_id) {
		return productDao.getImagesByCategoryId(category_id);
	}

	public List<Long> getProductIdsByCategoryId(int category_id) {
		return productDao.getProductIdsByCategoryId(category_id);
	}

	public List<Product> searchProducts(String searchString) {
		return productDao.searchProducts(searchString);
	}

	public List<Product> searchProductsPagination(String searchString, int start, int limit) {
		return productDao.searchProductsPagination(searchString, start, limit);
	}

	public int updateQuantity(long id, int quantity) {
		return productDao.updateQuantity(id, quantity);
	}

	public int updateNewProducts() {
		return productDao.updateNewProducts();
	}

	public int updateTopSellProducts(long id) {
		return productDao.updateTopSellProducts(id);
	}

	public int updateNotTopSellProducts(long id) {
		return productDao.updateNotTopSellProducts(id);
	}
}
