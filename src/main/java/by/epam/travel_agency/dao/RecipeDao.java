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
import by.epam.pharmacy.entity.Recipe;
import main.java.by.epam.travel_agency.dao.DAOException;

public class RecipeDao implements AbstractDao<Recipe> {
	private static final Logger LOGGER = LogManager.getLogger(RecipeDao.class.getName());
	private static final String SQL_SELECT_RECIPE = "SELECT * FROM final_project.recipes JOIN final_project.users ON (recipes.users_id=users.id) JOIN final_project.products ON (recipes.products_id = products.id)";
	private static final String SQL_CREATE_RECIPE = "INSERT INTO final_project.recipes (id, users_id, products_id, status) VALUES (?,?,?,?)";
	private static final String SQL_SELECT_RECIPE_BY_ID = "SELECT * FROM final_project.recipes JOIN final_project.users ON (recipes.users_id = users.id) JOIN final_project.products ON (recipes.products_id = products.id) WHERE recipes.id= ?";
	private static final String SQL_UPDATE_RECIPE_STATUS = "UPDATE final_project.recipes SET status= ? WHERE id= ?";
	private static final String SQL_SELECT_RECIPE_BY_USER_PRODUCT_ID = "SELECT * FROM final_project.recipes JOIN final_project.users ON (recipes.users_id=users.id) JOIN final_project.products ON (recipes.products_id = products.id) WHERE recipes.users_id= ? AND recipes.products_id=?";

	private static RecipeDao instance = new RecipeDao();

	private RecipeDao() {
	}

	public static RecipeDao getInstance() {
		return instance;
	}

	@Override
	public List<Recipe> findAll() throws DAOException { 
		ArrayList<Recipe> recipes = new ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_RECIPE);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Recipe recipe = new Recipe();
				UserDao userDAO = UserDao.getInstance();
				ProductDao productDAO = ProductDao.getInstance();
				recipe.setId(resultSet.getInt("recipes.id"));
				recipe.setUser(userDAO.findEntityById(resultSet.getInt("recipes.users_id")));
				recipe.setProduct(productDAO.findEntityById(resultSet.getInt("recipes.products_id")));
				recipe.setStatusRecipe(resultSet.getString("recipes.status"));
				recipes.add(recipe);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select recipes." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return recipes;
	}

	@Override 
	public Recipe findEntityById(Integer id) throws DAOException {
		Recipe recipe = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_RECIPE_BY_ID);
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				recipe = new Recipe();
				UserDao userDAO = UserDao.getInstance();
				ProductDao productDAO = ProductDao.getInstance();
				recipe.setId(resultSet.getInt("recipes.id"));
				recipe.setUser(userDAO.findEntityById(resultSet.getInt("recipes.users_id")));
				recipe.setProduct(productDAO.findEntityById(resultSet.getInt("recipes.products_id")));
				recipe.setStatusRecipe(resultSet.getString("recipes.status"));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select recipes by id." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return recipe;
	}

	@Override
	public boolean delete(Integer id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(Recipe entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	@Override 
	public boolean add(Recipe entity) throws DAOException {
		boolean flag = false;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_CREATE_RECIPE);
			prepareStatement.setInt(1, entity.getId());
			prepareStatement.setInt(2, entity.getUser().getId());
			prepareStatement.setInt(3, entity.getProduct().getId());
			prepareStatement.setString(4, entity.getStatusRecipe());
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't insert recipes." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	@Override
	public Recipe update(Recipe entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	public boolean updateStatusRecipe(Recipe entity) throws DAOException { 
		boolean flag = false;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_UPDATE_RECIPE_STATUS);
			prepareStatement.setString(1, entity.getStatusRecipe());
			prepareStatement.setInt(2, entity.getId());
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't update status of recipe." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	public List<Recipe> findRecipeByUserProduct(int id, int idProduct) throws DAOException {
		ArrayList<Recipe> recipes = new ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_RECIPE_BY_USER_PRODUCT_ID);
			prepareStatement.setInt(1, id);
			prepareStatement.setInt(2, idProduct);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Recipe recipe = new Recipe();
				UserDao userDAO = UserDao.getInstance();
				ProductDao productDAO = ProductDao.getInstance();
				recipe.setId(resultSet.getInt("recipes.id"));
				recipe.setUser(userDAO.findEntityById(resultSet.getInt("recipes.users_id")));
				recipe.setProduct(productDAO.findEntityById(resultSet.getInt("recipes.products_id")));
				recipe.setStatusRecipe(resultSet.getString("recipes.status"));
				recipes.add(recipe);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select recipes by user and product." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return recipes;
	}
}