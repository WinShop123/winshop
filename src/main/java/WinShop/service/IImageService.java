package WinShop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import WinShop.entity.Image;

@Service
public interface IImageService {
    public List<Image> getImagesByProductId(long product_id);
	
	public List<String> getImagesStringByProductId(long product_id);
	
	public List<Image> getImagesData();
	
	public int create(Image image);
	
	public int edit(Image image);
	
	public int delete(long id);
	
	public List<Image> getImagesDataPagination(int start, int limit);
	
	public Image getImageById(long id);
}
