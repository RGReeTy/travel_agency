package by.epam.pharmacy.receiver;

import java.util.ArrayList;
import java.util.List;

import by.epam.pharmacy.entity.Order;
import main.java.by.epam.travel_agency.dao.DAOException;

public class OrderReceiver {

	private OrderDao orderDao = OrderDao.getInstance();

	private static OrderReceiver instance = new OrderReceiver();

	private OrderReceiver() {
	}

	public static OrderReceiver getInstance() {
		return instance;
	}

	public List<Order> receiverOrderFindAll() throws ReceiverException {
		ArrayList<Order> orders = null;
		try {
			orders = (ArrayList<Order>) instance.orderDao.findAll();
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverOrderFindAll method", e);
		}
		return orders;
	}

	public ArrayList<Product> receiverFindProductsOfOrder(Order order) throws ReceiverException {
		ArrayList<Product> products = null;
		try {
			products = instance.orderDao.findProductsOfOrder(order);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverFindProductsOfOrder method", e);
		}
		return products;
	}

	public Order receiverOrderFindById(Integer id) throws ReceiverException {
		Order order = null;
		try {
			order = instance.orderDao.findEntityById(id);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverOrderFindById method", e);
		}
		return order;
	}

	public List<Order> receiverFindOrdersByUserId(int id) throws ReceiverException {
		ArrayList<Order> orders = null;
		try {
			orders = (ArrayList<Order>) instance.orderDao.findEntitiesByUserId(id);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverFindOrdersByUserId method", e);
		}
		return orders;
	}

	public boolean receiverOrderDelete(Integer id) throws ReceiverException {
		try {
			instance.orderDao.delete(id);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverOrderDelete method", e);
		}
		return true;
	}

	public boolean receiverOrderDelete(Order order) throws ReceiverException {
		try {
			instance.orderDao.delete(order);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverOrderDelete method", e);
		}
		return true;
	}

	public void receiverDeleteFromOrdersProducts(int id) throws ReceiverException {
		try {
			instance.orderDao.deleteFromOrdersProducts(id);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverOrderDelete method", e);
		}
	}

	public boolean receiverOrderAdd(Order order) throws ReceiverException {
		try {
			instance.orderDao.add(order);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverOrderAdd method", e);
		}
		return true;
	}

	public Integer receiverFindStatusId(String status) throws ReceiverException {
		Integer id = null;
		try {
			instance.orderDao.findStatusId(status);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverFindStatusId method", e);
		}
		return id;
	}

	public boolean receiverCreateOrdersProducts(Order order) throws ReceiverException {
		try {
			instance.orderDao.createOrdersProducts(order);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverCreateOrdersProducts method", e);
		}
		return true;
	}

	public void receiverFindAllOrdersProduct() throws ReceiverException {
		try {
			instance.orderDao.findAllOrdersProduct();
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverFindAllOrdersProduct method", e);
		}
	}

	public boolean receiverUpdateStatus(Order order) throws ReceiverException {
		try {
			instance.orderDao.updateStatus(order);
		} catch (DAOException e) {
			new ReceiverException("Exception in receiverUpdateStatus method", e);
		}
		return true;
	}
}
