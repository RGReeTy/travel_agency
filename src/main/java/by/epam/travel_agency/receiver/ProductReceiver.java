package by.epam.pharmacy.receiver;

import java.util.ArrayList;
import java.util.List;

import main.java.by.epam.travel_agency.dao.DAOException;

public class ProductReceiver {

	private ProductDao productDao = ProductDao.getInstance();

	private static ProductReceiver instance = new ProductReceiver();

	private ProductReceiver() {
	}

	public static ProductReceiver getInstance() {
		return instance;
	}

	public List<Product> receiverProductFindAll() throws ReceiverException {
		ArrayList<Product> products = null;
		try {
			products = (ArrayList<Product>) instance.productDao.findAll();
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverProductFindAll method", e);
		}
		return products;
	}

	public Product receiverProductFindById(Integer id) throws ReceiverException {
		Product product = null;
		try {
			product = instance.productDao.findEntityById(id);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverUserfindById method", e);
		}
		return product;
	}

	public List<Product> receiverProductFindType(String type) throws ReceiverException {
		ArrayList<Product> products = null;
		try {
			products = (ArrayList<Product>) instance.productDao.findEntitiesByType(type);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverProductFindType method", e);
		}
		return products;
	}

	public boolean receiverProductDelete(Integer id) throws ReceiverException {
		try {
			if (receiverCheckActiveOrder(id)) {
				return false;
			}
			instance.productDao.delete(id);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverProductDelete method", e);
		}
		return true;
	}

	public boolean receiverProductDelete(Product product) throws ReceiverException {
		try {
			if (receiverCheckActiveOrder(product.getId())) {
				return false;
			}
			instance.productDao.delete(product);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverProductDelete method", e);
		}
		return true;
	}

	public boolean receiverCheckActiveOrder(int id) throws ReceiverException {
		try {
			instance.productDao.checkActiveOrder(id);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverCheckActiveOrder method", e);
		}
		return true;
	}

	public void receiverDeleteFromOrdersProducts(int id) throws ReceiverException {
		try {
			instance.productDao.deleteFromOrdersProducts(id);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverDeleteFromOrdersProducts method", e);
		}
	}

	public boolean receiverProductAdd(Product product) throws ReceiverException {
		try {
			instance.productDao.add(product);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverProductAdd method", e);
		}
		return true;
	}

	public Product receiverProductUpdate(Product product) throws ReceiverException {
		try {
			instance.productDao.update(product);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverProductUpdate method", e);
		}
		return product;
	}

	public Integer receiverFindTypeId(String type) throws ReceiverException {
		Integer id = null;
		try {
			instance.productDao.findTypeId(type);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverFindTypeId method", e);
		}
		return id;
	}
	
	public Integer receiverFindPictureId(String type) throws ReceiverException {
		Integer id = null;
		try {
			instance.productDao.findPictureId(type);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverFindPictureId method", e);
		}
		return id;
	}
	
	public List<String> receiverFindAllProductTypes() throws ReceiverException {
		ArrayList<String> types = null;
		try {
			types =  (ArrayList<String>) instance.productDao.findAllProductTypes();
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverFindAllProductTypes method", e);
		}
		return types;
	}
	
	public List<String> receiverFindAllProductPicturePath() throws ReceiverException {
		ArrayList<String> path = null;
		try {
			path = (ArrayList<String>) instance.productDao.findAllProductPicturePath();
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverFindAllProductPicturePath method", e);
		}
		return path;
	}
}
