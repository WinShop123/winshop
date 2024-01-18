package WinShop.dto;

import java.math.BigDecimal;

public class ProductDto {
	private int category_id;
	private long id;
	private String name;
	private String image;
	private String dimension;
	private String material;
	private String accessories;
	private int quantity;
	private BigDecimal price;
	private String description;
	private long image_id;
	private String image_img;
	private boolean is_slideimage;
	private int sale_off;
	private boolean is_new;
	private boolean is_topsell;
	private String createat;

	public ProductDto() {
		super();
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getImage_id() {
		return image_id;
	}

	public void setImage_id(long image_id) {
		this.image_id = image_id;
	}

	public String getImage_img() {
		return image_img;
	}

	public void setImage_img(String image_img) {
		this.image_img = image_img;
	}

	public boolean isIs_slideimage() {
		return is_slideimage;
	}

	public void setIs_slideimage(boolean is_slideimage) {
		this.is_slideimage = is_slideimage;
	}

	public int getSale_off() {
		return sale_off;
	}

	public void setSale_off(int sale_off) {
		this.sale_off = sale_off;
	}

	public boolean isIs_new() {
		return is_new;
	}

	public void setIs_new(boolean is_new) {
		this.is_new = is_new;
	}

	public boolean isIs_topsell() {
		return is_topsell;
	}

	public void setIs_topsell(boolean is_topsell) {
		this.is_topsell = is_topsell;
	}

	public String getCreateat() {
		return createat;
	}

	public void setCreateat(String createat) {
		this.createat = createat;
	}
}
