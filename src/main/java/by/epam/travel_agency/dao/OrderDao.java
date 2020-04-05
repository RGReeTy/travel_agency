package by.epam.pharmacy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.by.epam.travel_agency.dao.connectionpool.ConnectionPool;
import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.entity.Product;
import main.java.by.epam.travel_agency.dao.DAOException;

public class OrderDao implements AbstractDao<Order> {
	private static final Logger LOGGER = LogManager.getLogger(OrderDao.class.getName());
	
	private static final String SQL_SELECT_ORDER = "SELECT * FROM final_project.orders JOIN final_project.orders_statuses ON (orders.status_id = orders_statuses.id)";
	private static final String SQL_SELECT_ORDERS_PRODUCTS = "SELECT * FROM final_project.orders_products WHERE orders_id= ?";
	private static final String SQL_SELECT_ORDER_BY_ID = "SELECT * FROM final_project.orders JOIN final_project.orders_statuses ON (orders.status_id = orders_statuses.id) WHERE orders.id= ?";
	private static final String SQL_SELECT_ORDER_BY_USER_ID = "SELECT * FROM final_project.orders JOIN final_project.orders_statuses ON (orders.status_id = orders_statuses.id) WHERE orders.users_id= ?";
	private static final String SQL_CREATE_ORDER = "INSERT INTO final_project.orders (id, users_id, status_id) VALUES (?,?,?)";
	private static final String SQL_CREATE_ORDERS_PRODUCTS = "INSERT INTO final_project.orders_products (id, orders_id, products_id) VALUES (?,?,?)";
	private static final String SQL_UPDATE_ORDER_STATUS = "UPDATE final_project.orders SET status_id= ? WHERE id= ?";
	private static final String SQL_DELETE_ORDER = "DELETE FROM final_project.orders WHERE id= ?";
	private static final String SQL_DELETE_ORDERS_PRODUCTS = "DELETE FROM final_project.orders_products WHERE orders_id= ?";
	private static final String SQL_SELECT_STATUS = "SELECT * FROM final_project.orders_statuses WHERE description= ?";
	private static final String SQL_SELECT_ORDER_PRODUCT = "SELECT * FROM final_project.orders_products JOIN final_project.orders ON (orders_products.orders_id = orders.id) JOIN final_project.products ON (orders_products.products_id=products.id)";

	private static OrderDao instance = new OrderDao();
	private OrderDao () {
	}
	public static OrderDao getInstance(){
		return instance;
	}
	
	@Override
	public List<Order> findAll() throws DAOException {
		ArrayList<Order> orders = new ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_ORDER);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				UserDao userDAO = UserDao.getInstance();
				order.setId(resultSet.getInt("orders.id"));
				order.setUser(userDAO.findEntityById(resultSet.getInt("orders.users_id")));
				order.setStatus(resultSet.getString("orders_statuses.description"));
				order.setProducts(findProductsOfOrder(order));
				orders.add(order);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select all orders from database." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return orders;
	}

	public ArrayList<Product> findProductsOfOrder(Order order) throws DAOException {
		ArrayList<Product> products = new ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_ORDERS_PRODUCTS);
			ProductDao productDAO = ProductDao.getInstance();
			prepareStatement.setInt(1, order.getId());
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Product product = productDAO.findEntityById(resultSet.getInt("products_id"));
				products.add(product);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select products from orders." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return products;
	}

	@Override
	public Order findEntityById(Integer id) throws DAOException {
		Order order = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID);
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				order = new Order();
				UserDao userDAO = UserDao.getInstance();
				order.setId(resultSet.getInt("orders.id"));
				order.setUser(userDAO.findEntityById(resultSet.getInt("orders.users_id")));
				order.setStatus(resultSet.getString("orders_statuses.description"));
				order.setProducts(findProductsOfOrder(order));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select orders by id." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return order;
	}

	public List<Order> findEntitiesByUserId(int id) throws DAOException {
		ArrayList<Order> orders = new ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_ORDER_BY_USER_ID);
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				UserDao userDAO = UserDao.getInstance();
				order.setId(resultSet.getInt("orders.id"));
				order.setUser(userDAO.findEntityById(resultSet.getInt("orders.users_id")));
				order.setStatus(resultSet.getString("orders_statuses.description"));
				order.setProducts(findProductsOfOrder(order));
				orders.add(order);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select orders by id users." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return orders;
	}

	@Override
	public boolean delete(Integer id) throws DAOException {
		boolean flag = false;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE_ORDER);
			prepareStatement.setInt(1, id);
			deleteFromOrdersProducts(id);
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't delete orders by id." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	public void deleteFromOrdersProducts(int id) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE_ORDERS_PRODUCTS);
			prepareStatement.setInt(1, id);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't delete products from orders." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
	}

	@Override
	public boolean delete(Order entity) throws DAOException {
		boolean flag = false;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE_ORDER);
			prepareStatement.setInt(1, entity.getId());
			deleteFromOrdersProducts(entity.getId());
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't delete order." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	@Override
	public boolean add(Order entity) throws DAOException {
		boolean flag = false;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatementOrder = connection.prepareStatement(SQL_CREATE_ORDER);
			prepareStatementOrder.setInt(1, entity.getId());
			prepareStatementOrder.setInt(2, entity.getUser().getId());
			prepareStatementOrder.setInt(3, findStatusId(entity.getStatus()));
			int count = prepareStatementOrder.executeUpdate();
			if (count == 1 && createOrdersProducts(entity)) {
				flag = true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't insert order." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	public Integer findStatusId(String status) throws DAOException {
		Integer id = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_STATUS);
			prepareStatement.setString(1, status);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select status for order." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return id;
	}

	public boolean createOrdersProducts(Order entity) throws DAOException {
		boolean flag = false;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_CREATE_ORDERS_PRODUCTS);
			for (Product product : entity.getProducts()) {
				prepareStatement.setInt(1, findAllOrdersProduct() + 1);
				prepareStatement.setInt(2, entity.getId());
				prepareStatement.setInt(3, product.getId());
				int count = prepareStatement.executeUpdate();
				if (count != 1) {
					flag = false;
					break;
				}
				flag = true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't insert order with products." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	public int findAllOrdersProduct() throws DAOException {
		ArrayList<Order> orders = new ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_ORDER_PRODUCT);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt("orders_products.orders_id"));
				orders.add(order);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select products from order." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return orders.size();
	}

	@Override
	public Order update(Order entity) {
		throw new UnsupportedOperationException();
	}

	public boolean updateStatus(Order entity) throws DAOException {
		boolean flag = false;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_UPDATE_ORDER_STATUS);
			prepareStatement.setInt(1, findStatusId(entity.getStatus()));
			prepareStatement.setInt(2, entity.getId());
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't update status of order." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}
}