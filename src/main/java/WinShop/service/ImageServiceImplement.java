package WinShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WinShop.dao.ImageDao;
import WinShop.entity.Image;

@Service
public class ImageServiceImplement implements IImageService {

	
	@Autowired
	private ImageDao imageDao;

	public List<Image> getImagesByProductId(long product_id) {
		return imageDao.getImagesProductId(product_id);
	}

	public List<String> getImagesStringByProductId(long product_id) {
		return imageDao.getImagesStringByProductId(product_id);
	}

	public List<Image> getImagesData() {
		return imageDao.getImagesData(); 
	}

	public int create(Image image) {
		return imageDao.create(image);
	}

	public int edit(Image image) {
		return imageDao.edit(image);
	}

	public int delete(long id) {
		return imageDao.delete(id);
	}

	public List<Image> getImagesDataPagination(int start, int limit) {
		return imageDao.getImagesDataPagination(start, limit);
	}

	public Image getImageById(long id) {
		return imageDao.getImageById(id);
	}
}
