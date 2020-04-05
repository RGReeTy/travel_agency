package main.java.by.epam.travel_agency.dao.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import com.mysql.jdbc.Driver;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {
	private static final Logger LOGGER = LogManager.getLogger();
	private static final String PROPERTY_FILE = "connection_pool";
	private String user;
	private String password;
	private String url;
	private ArrayBlockingQueue<Connection> connectionPool;
	private static int poolSize;
	private static ConnectionPool instance = new ConnectionPool();

	private ConnectionPool() {
		try {
			DriverManager.registerDriver(new Driver());
			ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTY_FILE);
			poolSize = Integer.parseInt(resourceBundle.getString("pool_size"));
			user = resourceBundle.getString("user");
			password = resourceBundle.getString("password");
			url = resourceBundle.getString("url");
			connectionPool = new ArrayBlockingQueue<>(poolSize);
			for (int i = 0; i < poolSize; i++) {
				connectionPool.put(DriverManager.getConnection(url, user, password));
			}
			LOGGER.log(Level.DEBUG, "SIZE " + connectionPool.size());
		} catch (SQLException | InterruptedException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	public Connection getConnection() { 
		Connection connection = null;
		try {
			connection = connectionPool.take();
		} catch (InterruptedException e) {
			LOGGER.log(Level.WARN, "Problems with getConnection " + e.getMessage());
		}
		return connection;
	}

	public void freeConnection(Connection connection) {
		try {
			if (!connection.isClosed()) {
				connectionPool.put(connection);
			}
		} catch (SQLException | InterruptedException e) {
			LOGGER.log(Level.WARN, "Problems with freeConnection " + e.getMessage());
		}
	}

	public void closeConnectionsInPool() {
		int countOfClosingConnection = 0;
		while (connectionPool.size() > 0) {
			LOGGER.log(Level.DEBUG, "SIZE " + connectionPool.size());
			try {
				connectionPool.take().close();
				countOfClosingConnection++;
				LOGGER.log(Level.DEBUG, "Connection was closed, count of closing " + countOfClosingConnection);
			} catch (SQLException | InterruptedException e) {
				LOGGER.log(Level.ERROR, "Connection wasnt closed, cause: " + e.getMessage());
			}
		}
	}
}