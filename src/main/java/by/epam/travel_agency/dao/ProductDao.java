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
import by.epam.pharmacy.entity.Product;
import main.java.by.epam.travel_agency.dao.DAOException;

public class ProductDao implements AbstractDao<Product> {
	private static final Logger LOGGER = LogManager.getLogger(ProductDao.class.getName());
	
	private static final String SQL_SELECT_PRODUCTS = "SELECT * FROM final_project.products JOIN final_project.product_pictures ON (products.product_picture = product_pictures.id) JOIN final_project.product_types ON (products.disease_id=product_types.id)";
	private static final String SQL_SELECT_PRODUCTS_BY_ID_PRODUCTS = "SELECT * FROM final_project.products JOIN final_project.product_pictures ON (products.product_picture = product_pictures.id) JOIN final_project.product_types ON (products.disease_id=product_types.id) WHERE products.id= ?";
	private static final String SQL_SELECT_PRODUCTS_BY_TYPE = "SELECT * FROM final_project.products JOIN final_project.product_pictures ON (products.product_picture = product_pictures.id) JOIN final_project.product_types ON (products.disease_id=product_types.id) WHERE product_types.description= ?";
	private static final String SQL_DELETE_PRODUCTS = "DELETE FROM final_project.products WHERE id= ?";
	private static final String SQL_CHECK_ACTIVE_ORDER = "SELECT * FROM final_project.products JOIN final_project.orders_products ON (products.id = orders_products.products_id) JOIN final_project.orders ON (orders.id = orders_products.orders_id) WHERE orders.status_id = 1 AND products.id= ?";
	private static final String SQL_DELETE_ORDERS_PRODUCTS = "DELETE FROM final_project.orders_products WHERE products_id= ?";
	private static final String SQL_SELECT_PRODUCT_TYPES = "SELECT * FROM final_project.product_types";
	private static final String SQL_SELECT_PRODUCT_PICTURES = "SELECT * FROM final_project.product_pictures";
	private static final String SQL_UPDATE_PRODUCTS = "UPDATE final_project.products SET name= ?, price= ?, description= ?, product_picture=?, producer=?, dosage=?, disease_id=? WHERE id= ?";
	private static final String SQL_SELECT_TYPE = "SELECT * FROM final_project.product_types WHERE description= ?";
	private static final String SQL_SELECT_PICTURE = "SELECT * FROM final_project.product_pictures WHERE path= ?";
	private static final String SQL_INSERT_PRODUCTS = "INSERT INTO final_project.products VALUES (?,?,?,?,?,?,?,?)";

	private static ProductDao instance = new ProductDao();
	private ProductDao () {
	}
	public static ProductDao getInstance(){
		return instance;
	}
	
	@Override
	public List<Product> findAll() throws DAOException {
		ArrayList<Product> products = new ArrayList<Product>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_PRODUCTS);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("products.id"));
				product.setName(resultSet.getString("products.name"));
				product.setPrice(resultSet.getInt("products.price"));
				product.setDescription(resultSet.getString("products.description"));
				product.setPicturePath(resultSet.getString("product_pictures.path"));
				product.setProducer(resultSet.getString("products.producer"));
				product.setDosage(resultSet.getInt("products.dosage"));
				product.setDisease(resultSet.getString("product_types.description"));
				products.add(product);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select all products from database." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return products;
	}

	@Override
	public Product findEntityById(Integer id) throws DAOException {
		Product product = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_PRODUCTS_BY_ID_PRODUCTS);
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				product = new Product();
				product.setId(resultSet.getInt("products.id"));
				product.setName(resultSet.getString("products.name"));
				product.setPrice(resultSet.getInt("products.price"));
				product.setDescription(resultSet.getString("products.description"));
				product.setPicturePath(resultSet.getString("product_pictures.path"));
				product.setProducer(resultSet.getString("products.producer"));
				product.setDosage(resultSet.getInt("products.dosage"));
				product.setDisease(resultSet.getString("product_types.description"));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select product by id products in database." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return product;
	}
	
	public List<Product> findEntitiesByType(String type) throws DAOException {
		ArrayList<Product> products = new ArrayList<Product>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_PRODUCTS_BY_TYPE);
			prepareStatement.setString(1, type);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("products.id"));
				product.setName(resultSet.getString("products.name"));
				product.setPrice(resultSet.getInt("products.price"));
				product.setDescription(resultSet.getString("products.description"));
				product.setPicturePath(resultSet.getString("product_pictures.path"));
				product.setProducer(resultSet.getString("products.producer"));
				product.setDosage(resultSet.getInt("products.dosage"));
				product.setDisease(resultSet.getString("product_types.description"));
				products.add(product);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select products by type products in database." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return products;
	}

	@Override
	public boolean delete(Integer id) throws DAOException {
		boolean flag = false;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE_PRODUCTS);
			deleteFromOrdersProducts(id);
			prepareStatement.setInt(1, id);
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't delete product by id." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	@Override
	public boolean delete(Product entity) throws DAOException {
		boolean flag = false;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE_PRODUCTS);
			deleteFromOrdersProducts(entity.getId());
			prepareStatement.setInt(1, entity.getId());
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't delete product." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	public boolean checkActiveOrder(int id) throws DAOException {
		boolean flag = false;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_CHECK_ACTIVE_ORDER);
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't check order." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	public void deleteFromOrdersProducts(int productId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE_ORDERS_PRODUCTS);
			prepareStatement.setInt(1, productId);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't delete products from order." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
	}

	@Override
	public boolean add(Product entity) throws DAOException {
		boolean flag = false;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_INSERT_PRODUCTS);
			int typeId = findTypeId(entity.getDisease());
			int pictureId = findPictureId(entity.getPicturePath());
			prepareStatement.setInt(1, entity.getId());
			prepareStatement.setString(2, entity.getName());
			prepareStatement.setInt(3, entity.getPrice());
			prepareStatement.setString(4, entity.getDescription());
			prepareStatement.setInt(5, pictureId);
			prepareStatement.setString(6, entity.getProducer());
			prepareStatement.setInt(7, entity.getDosage());
			prepareStatement.setInt(8, typeId);
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't insert product." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	@Override
	public Product update(Product entity) throws DAOException {
		Product product = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_UPDATE_PRODUCTS);
			int typeId = findTypeId(entity.getDisease());
			int pictureId = findPictureId(entity.getPicturePath());
			prepareStatement.setString(1, entity.getName());
			prepareStatement.setInt(2, entity.getPrice());
			prepareStatement.setString(3, entity.getDescription());
			prepareStatement.setInt(4, pictureId);
			prepareStatement.setString(5, entity.getProducer());
			prepareStatement.setInt(6, entity.getDosage());
			prepareStatement.setInt(7, typeId);
			prepareStatement.setInt(8, entity.getId());
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				return entity;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't update product." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return product;
	}

	public Integer findTypeId(String type) throws DAOException {
		Integer id = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_TYPE);
			prepareStatement.setString(1, type);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				id = new Integer(resultSet.getInt("id"));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select type." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return id;
	}

	public Integer findPictureId(String path) throws DAOException {
		Integer id = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_PICTURE);
			prepareStatement.setString(1, path);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				id = new Integer(resultSet.getInt("id"));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select picture." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return id;
	}

	public List<String> findAllProductTypes() throws DAOException {
		ArrayList<String> types = new ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_PRODUCT_TYPES);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				types.add(resultSet.getString("description"));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select all types of product." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return types;
	}

	public List<String> findAllProductPicturePath() throws DAOException {
		ArrayList<String> path = new ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_PRODUCT_PICTURES);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				path.add(resultSet.getString("path"));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select picture path." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return path;
	}
}