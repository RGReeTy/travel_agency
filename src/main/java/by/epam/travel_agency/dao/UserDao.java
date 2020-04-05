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
import by.epam.pharmacy.entity.User;
import main.java.by.epam.travel_agency.dao.DAOException;

public class UserDao implements AbstractDao<User> {
	private static final Logger LOGGER = LogManager.getLogger(UserDao.class.getName());

	private static final String SQL_SELECT_USERS = "SELECT * FROM final_project.users";
	private static final String SQL_SELECT_USERS_BY_ID_USER = "SELECT * FROM final_project.users WHERE id= ?";
	private static final String SQL_DELETE_USERS = "DELETE FROM final_project.users WHERE id= ?";
	private static final String SQL_INSERT_USERS = "INSERT INTO final_project.users VALUES (?,?,?,?,?,?)";
	private static final String SQL_SELECT_USERS_BY_LOGIN_AND_PASSWORD = "SELECT * FROM final_project.users JOIN final_project.access_level ON (users.access_level_id=access_level.id) WHERE users.login= ? AND users.password= ?";
	private static final String SQL_SELECT_USERS_BY_LOGIN = "SELECT * FROM final_project.users WHERE login= ?";
	private static final String SQL_UPDATE_COUNT = "UPDATE final_project.users SET account=? WHERE id=?";

	private static UserDao instance = new UserDao();

	private UserDao() {
	}

	public static UserDao getInstance() {
		return instance;
	}

	@Override
	public List<User> findAll() throws DAOException {
		ArrayList<User> users = new ArrayList<>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_USERS);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setAccessLevel(resultSet.getInt("access_level_id"));
				user.setAccount(resultSet.getInt("account"));
				users.add(user);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select all users." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return users;
	}

	@Override
	public User findEntityById(Integer id) throws DAOException {
		User user = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_USERS_BY_ID_USER);
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setAccessLevel(resultSet.getInt("access_level_id"));
				user.setAccount(resultSet.getInt("account"));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select user by id." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return user;
	}

	public User findEntityByLoginAndPassword(String login, String password) throws DAOException {
		User user = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_USERS_BY_LOGIN_AND_PASSWORD);
			prepareStatement.setString(1, login);
			prepareStatement.setString(2, password);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setAccessLevel(resultSet.getInt("access_level_id"));
				user.setAccount(resultSet.getInt("account"));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select user by login and password." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return user;
	}

	@Override
	public boolean delete(Integer id) throws DAOException {
		boolean flag = false;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE_USERS);
			prepareStatement.setInt(1, id);
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't delete user by id." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	@Override
	public boolean delete(User entity) throws DAOException {
		boolean flag = false;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_DELETE_USERS);
			prepareStatement.setInt(1, entity.getId());
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't delete user." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	@Override
	public boolean add(User entity) throws DAOException {
		boolean flag = false;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_INSERT_USERS);
			prepareStatement.setInt(1, entity.getId());
			prepareStatement.setString(2, entity.getLogin());
			prepareStatement.setString(3, entity.getPassword());
			prepareStatement.setString(4, entity.getEmail());
			prepareStatement.setInt(5, entity.getAccessLevel());
			prepareStatement.setInt(6, entity.getAccount());
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't insert user." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return flag;
	}

	@Override
	public User update(User entity) throws DAOException {
		User user = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_UPDATE_COUNT);
			prepareStatement.setInt(1, entity.getAccount());
			prepareStatement.setInt(2, entity.getId());
			int count = prepareStatement.executeUpdate();
			if (count == 1) {
				return entity;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't update count." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return user;
	}

	public User findEntityByLogin(String login) throws DAOException {
		User user = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(SQL_SELECT_USERS_BY_LOGIN);
			prepareStatement.setString(1, login);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setAccessLevel(resultSet.getInt("access_level_id"));
				user.setAccount(resultSet.getInt("account"));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARN, "Can't select user by login." + e);
		} finally {
			connectionPool.freeConnection(connection);
		}
		return user;
	}
}