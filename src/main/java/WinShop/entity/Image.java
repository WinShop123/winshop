package WinShop.entity;

public class Image {
	private long id;
	private long product_id;
	private String image;
	private boolean is_slideimage;

	public Image() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isIs_slideimage() {
		return is_slideimage;
	}

	public void setIs_slideimage(boolean is_slideimage) {
		this.is_slideimage = is_slideimage;
	}
}
