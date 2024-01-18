package WinShop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import WinShop.dto.ProductDto;
import WinShop.entity.Product;
import WinShop.mapper.ProductDtoMapper;
import WinShop.mapper.ProductMapper;

@Repository
public class ProductDao extends BaseDao {
	private final boolean YES = true;
	private final boolean NO = false;
	
	public StringBuffer productsDistinctBuffer() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("product.category_id, ");
		sql.append("product.id, ");
		sql.append("product.name, ");
		sql.append("product.image, ");
		sql.append("product.dimension, ");
		sql.append("product.material, ");
		sql.append("product.accessories, ");
		sql.append("product.quantity, ");
		sql.append("product.price, ");
		sql.append("product.description, ");
		sql.append("image.id as image_id, ");
		sql.append("image.image as image_img, ");
		sql.append("image.isSlideImage, ");
		sql.append("product.sale_off, ");
		sql.append("product.is_new, ");
		sql.append("product.is_topsell, ");
		sql.append("product.createat ");
		sql.append("FROM product INNER JOIN image ");
		sql.append("ON product.id = image.product_id ");
		sql.append("WHERE image.isSlideImage = true ");
		return sql;
	}
	
	public StringBuffer productsBuffer() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("product.category_id, ");
		sql.append("product.id, ");
		sql.append("product.name, ");
		sql.append("product.image, ");
		sql.append("product.dimension, ");
		sql.append("product.material, ");
		sql.append("product.accessories, ");
		sql.append("product.quantity, ");
		sql.append("product.price, ");
		sql.append("product.description, ");
		sql.append("image.id as image_id, ");
		sql.append("image.image as image_img, ");
		sql.append("image.isSlideImage, ");
		sql.append("product.sale_off, ");
		sql.append("product.is_new, ");
		sql.append("product.is_topsell, ");
		sql.append("product.createat ");
		sql.append("FROM product INNER JOIN image ");
		sql.append("ON product.id = image.product_id ");
		return sql;
	}
	
	public StringBuffer newOrTopSellProductsBuff(boolean is_new, boolean is_topsell) {
		StringBuffer sql = productsDistinctBuffer();
		sql.append("AND 1=1 ");
		if(is_new == true) {
			sql.append("AND product.is_new=true ");
		}
		if(is_topsell == true) {
			sql.append("AND product.is_topsell = true ");
		}
		sql.append("GROUP BY product.id, image.product_id ");
		sql.append("ORDER BY RAND() ");
		sql.append("LIMIT 8");
		return sql;
	}
	
	public StringBuffer get16ProductsDistinctBuffer() {
		StringBuffer sql = productsDistinctBuffer();
		sql.append("GROUP BY product.id, image.product_id ");
		sql.append("ORDER BY RAND() ");
		sql.append("LIMIT 16");
		return sql;
	}
	
	public StringBuffer getAllProductsDistinctBuffer() {
		StringBuffer sql = productsDistinctBuffer();
		sql.append("GROUP BY product.id, image.product_id ");
		return sql;
	}
	
	public StringBuffer getProductsByCategoryIdBuffer(int category_id) {
		StringBuffer sql = productsDistinctBuffer();
		sql.append("AND product.category_id='" + category_id + "' ");
		sql.append("GROUP BY product.id, image.product_id ");
		return sql;
	}
	
	public StringBuffer getRelatedProductsByCategoryIdBuffer(int category_id, long product_id) {
		StringBuffer sql = productsDistinctBuffer();
		sql.append("AND product.category_id='" + category_id + "' ");
		sql.append("AND product.id<>'" + product_id + "' ");
		sql.append("GROUP BY product.id, image.product_id ");
		return sql;
	}
	
	public StringBuffer getTheProductByIdBuffer(long product_id) {
		StringBuffer sql = productsDistinctBuffer();
		sql.append("AND product.id='" + product_id + "' ");
		sql.append("GROUP BY product.id, image.product_id ");
		return sql;
	}
	
	public StringBuffer getImagesOfTheProductBuffer(long product_id) {
		StringBuffer sql = productsBuffer();
		sql.append("WHERE product.id='" + product_id + "' ");
		return sql;
	}
	
	//16 products display for Home
	public List<ProductDto> get16ProductsDistinct() {
		String sql = get16ProductsDistinctBuffer().toString();
		List<ProductDto> list = jdbcTemplate.query(sql, new ProductDtoMapper());
		return list;
	}
	
	//New products for Home
	public List<ProductDto> getNewProducts() {
		String sql = newOrTopSellProductsBuff(YES, NO).toString();
		List<ProductDto> list = jdbcTemplate.query(sql, new ProductDtoMapper());
		return list;
	}
	
	//Topsell products for Home
	public List<ProductDto> getTopSellProducts() {
		String sql = newOrTopSellProductsBuff(NO, YES).toString();
		List<ProductDto> list = jdbcTemplate.query(sql, new ProductDtoMapper());
		return list;
	}
	
	//Products by CategoryId
	public List<ProductDto> getProductsByCategoryId(int category_id) {
		String sql = getProductsByCategoryIdBuffer(category_id).toString();
		List<ProductDto> list = jdbcTemplate.query(sql, new ProductDtoMapper());
		return list;
	}
	
	//All products
	public List<ProductDto> getAllProductsDistinct() {
		String sql = getAllProductsDistinctBuffer().toString();
		List<ProductDto> list = jdbcTemplate.query(sql, new ProductDtoMapper());
		return list;
	}
	
	//All products for all products page
	public List<ProductDto> getProductsPaginationForAll(int start, int limit) {
		StringBuffer sql = getAllProductsDistinctBuffer();
		sql.append("LIMIT " + start + ", " + limit);
		List<ProductDto> list = jdbcTemplate.query(sql.toString(), new ProductDtoMapper());
		return list;
	}
	
	//Products By Category Id for category page 
	public List<ProductDto> getProductsPaginationForCategory(int category_id, int start, int limit) {
		StringBuffer sql = getProductsByCategoryIdBuffer(category_id);
		sql.append("LIMIT " + start + ", " + limit);
		List<ProductDto> list = jdbcTemplate.query(sql.toString(), new ProductDtoMapper());
		return list;
	}
	
	//Product by it's id
	public ProductDto getTheProductById(long product_id) {
		StringBuffer sql = getTheProductByIdBuffer(product_id);
		ProductDto product = jdbcTemplate.queryForObject(sql.toString(), new ProductDtoMapper());
		return product;
	}
	
	//Images' info of the got product by id
	public List<ProductDto> getImagesOfTheProduct(long product_id) {
		StringBuffer sql = getImagesOfTheProductBuffer(product_id);
		List<ProductDto> images = jdbcTemplate.query(sql.toString(), new ProductDtoMapper());
		return images;
	}
	
	//Related Products
	public List<ProductDto> getRelatedProductsByCategoryId(int category_id, long product_id) {
		String sql = getRelatedProductsByCategoryIdBuffer(category_id, product_id).toString();
		List<ProductDto> relatedproducts = jdbcTemplate.query(sql, new ProductDtoMapper());
		return relatedproducts;
	}
	
//	Product
	
	public StringBuffer createBuffer(Product product) {
		StringBuffer  sql = new StringBuffer();
		sql.append("INSERT INTO `product` ");
		sql.append("(`id`, `category_id`, `name`, `image`, `dimension`, `material`, `accessories`, `quantity`, `price`, `description`, `sale_off`, `is_new`, `is_topsell`, `createat`) ");
		sql.append("VALUES ");
		sql.append("(NULL, ");
		sql.append(" '" + product.getCategory_id() + "', ");
		sql.append(" '" + product.getName() + "', ");
		sql.append(" '" + product.getImage() + "', ");
		sql.append(" '" + product.getDimension() + "', ");
		sql.append(" '" + product.getMaterial() + "', ");
		sql.append(" '" + product.getAccessories() + "', ");
		sql.append(" '" + product.getQuantity() + "', ");
		sql.append(" '" + product.getPrice() + "', ");
		sql.append(" '" + product.getDescription() + "', ");
		sql.append(" '" + product.getSale_off() + "', ");
		sql.append(" " + product.isIs_new() + ", ");
		sql.append(" " + product.isIs_topsell() + ", ");
		sql.append(" '" + product.getCreateat() + "')");
		return sql;
	}
	
	public StringBuffer editBuffer(Product product) {
		StringBuffer  sql = new StringBuffer();
		sql.append("UPDATE `product` SET ");
		sql.append("`category_id`='" + product.getCategory_id() + "', ");
		sql.append("`name`='" + product.getName() + "', ");
		sql.append("`image`='" + product.getImage() + "', ");
		sql.append("`dimension`='" + product.getDimension() + "', ");
		sql.append("`material`='" + product.getMaterial() + "', ");
		sql.append("`accessories`='" + product.getAccessories() + "', ");
		sql.append("`quantity`='" + product.getQuantity() + "', ");
		sql.append("`price`='" + product.getPrice() + "', ");
		sql.append("`description`='" + product.getDescription() + "', ");
		sql.append("`sale_off`='" + product.getSale_off() + "', ");
		sql.append("`is_new`=" + product.isIs_new() + ", ");
		sql.append("`is_topsell`=" + product.isIs_topsell() + ", ");
		sql.append("`createat`='" + product.getCreateat() + "' ");
		sql.append("WHERE product.id = '" + product.getId() + "'");
		return sql;
	}
	
	public List<Product> getProductsData() {
		String sql = "SELECT * FROM `product`";
		List<Product> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}
	
	public List<Product> getProductsDataPagination(int start, int limit) {
		String sql = "SELECT * FROM `product` LIMIT " + start + ", " + limit;
		List<Product> list = jdbcTemplate.query(sql, new ProductMapper());
		return list;
	}
	
	public int create(Product product) {
		String sql = createBuffer(product).toString();
		int insert = jdbcTemplate.update(sql);
		return insert;
	}
	
	public int edit(Product product) {
		String sql = editBuffer(product).toString();
		int edit = jdbcTemplate.update(sql);
		return edit;
	}
	
	public int delete(long id) {
		String sql = "DELETE FROM `product` WHERE product.id='" + id + "'";
		int delete = jdbcTemplate.update(sql);
		return delete;
	}
	
	public Product getProductById(long id) {
		String sql = "SELECT * FROM `product` WHERE product.id='" + id + "'";
		Product product = jdbcTemplate.queryForObject(sql, new ProductMapper());
		return product;
	}
	
	public List<String> getImagesByCategoryId(int category_id) {
		String sql = "SELECT product.image  FROM `product` WHERE product.category_id=" + category_id;
		List<String> images = jdbcTemplate.queryForList(sql, String.class);
		return images;
	}
	
	public List<Long> getProductIdsByCategoryId(int category_id) {
		String sql = "SELECT product.id FROM `product` WHERE product.category_id=" + category_id;
		List<Long> ids = jdbcTemplate.queryForList(sql, Long.class);
		return ids;
	}
	
	public List<Product> searchProducts(String searchString) {
		String sql = "SELECT * FROM `product` WHERE name LIKE '%" + searchString + "%'";
		List<Product> products = jdbcTemplate.query(sql, new ProductMapper());
		return products;
	}
	
	public List<Product> searchProductsPagination(String searchString, int start, int limit) {
		String sql = "SELECT * FROM `product` WHERE name LIKE '%" + searchString + "%' LIMIT " + start + ", " + limit;
		List<Product> products = jdbcTemplate.query(sql, new ProductMapper());
		return products;
	}
	
	public int updateQuantity(long id, int quantity) {
		String sql = "UPDATE `product` SET `quantity`='" + quantity + "' WHERE id=" + id;
		int update = jdbcTemplate.update(sql);
		return update;
	}
	
	public int updateNewProducts() {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE `product` SET ");
		sql.append("product.is_new = false ");
		sql.append("WHERE DATEDIFF(CURRENT_DATE, product.createat) > 30 ");
		sql.append("AND product.id > 4");
		int update = jdbcTemplate.update(sql.toString());
		return update;
	}
	
	public int updateTopSellProducts(long id) {
		String sql = "UPDATE `product` SET product.is_topsell = true WHERE id=" + id;
		int update = jdbcTemplate.update(sql);
		return update;
	}
	
	public int updateNotTopSellProducts(long id) {
		String sql = "UPDATE `product` SET product.is_topsell = false WHERE id=" + id;
		int update = jdbcTemplate.update(sql);
		return update;
	}
}
