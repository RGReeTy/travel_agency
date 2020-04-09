package by.epam.travel_agency.dao.connection_pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public class ConnectionPool {

    private static ConnectionPool instance = new ConnectionPool();

    private static Logger LOGGER = LogManager.getLogger();
    private String driver;
    private String user;
    private String password;
    private String url;
    private BlockingQueue<Connection> availableConnection;
    private BlockingQueue<Connection> usedConnection;
    private static int poolSize;

    private ConnectionPool() {
        ResourceManager resourceManager = ResourceManager.getInstance();
        this.driver = resourceManager.getValue(DBConnectionParameter.DRIVER.getKey());
        this.user = resourceManager.getValue(DBConnectionParameter.USER.getKey());
        this.password = resourceManager.getValue(DBConnectionParameter.PASSWORD.getKey());
        this.url = resourceManager.getValue(DBConnectionParameter.URL.getKey());
        poolSize = Integer.parseInt(resourceManager.getValue(DBConnectionParameter.POOL_SIZE.getKey()));

        //DELETE
        System.out.println("ConnPool constructor message");

        try {
            poolInitialization();
        } catch (ConnectionPoolException e) {
            LOGGER.log(Level.ERROR, "Connection doesn't initialize, cause: " + e);
        }
    }

    public static ConnectionPool getInstance() {
        //DELETE
        System.out.println("Connection getInstance message");
        return instance;
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = availableConnection.take();

            //DELETE
            System.out.println("Connection getConnection message");
        } catch (InterruptedException e) {
            LOGGER.log(Level.WARN, "Problems with getConnection " + e);
        }
        return connection;
    }

    public void poolInitialization() throws ConnectionPoolException {
//DELETE
        System.out.println("poolInitialization message");
        try {
            Class.forName(driver);
            availableConnection = new ArrayBlockingQueue<>(poolSize);
            usedConnection = new ArrayBlockingQueue<>(poolSize);
            LOGGER.log(Level.INFO, "queue are cr8");
            System.out.println("queue are cr8");

            //DELETE
            System.out.println("poolInitialization message");

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                PooledConnection pooledConnection = new PooledConnection(connection);
                availableConnection.add(pooledConnection);
            }
        } catch (SQLException e) {
			LOGGER.log(Level.WARN, "SQLException during pool creation! " + e);
        } catch (ClassNotFoundException e) {
			LOGGER.log(Level.WARN, "There is no driver! " + e);
        }
    }

    public void freeConnection(Connection connection) {
        try {
            if (!connection.isClosed()) {
                availableConnection.put(connection);
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.log(Level.WARN, "Problems with freeConnection " + e);
        }
    }

    public void closeConnectionsInPool() {
        int countOfClosingConnection = 0;
        while (availableConnection.size() > 0) {
            LOGGER.log(Level.DEBUG, "SIZE " + availableConnection.size());
            try {
                availableConnection.take().close();
                countOfClosingConnection++;
                LOGGER.log(Level.DEBUG, "Connection was closed, count of closing " + countOfClosingConnection);
            } catch (SQLException | InterruptedException e) {
                LOGGER.log(Level.ERROR, "Connection wasnt closed, cause: " + e);
            }
        }
    }

    public void dispose() {
        clearConnectionQueue();
    }

    private void clearConnectionQueue() {
        try {
            closeConnectionsQueue(availableConnection);
            closeConnectionsQueue(usedConnection);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Error closing the connection." + e);
        }
    }


    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = availableConnection.take();
            usedConnection.add(connection);

            //DELETE
            System.out.println("takeConnection message");

        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Exception during taking the connection!");
        }
        return connection;
    }

    public void closeConnection(Connection con, Statement st, ResultSet rs) {
        try {
            con.close();
        } catch (SQLException e) {
            //logger.log(Level.ERROR, "Connection isn't return to the pool. ");
        }
        try {
            rs.close();
        } catch (SQLException e) {
            //logger.log(Level.ERROR, "ResultSet isn't closed.");
        }
        try {
            st.close();
        } catch (SQLException e) {
            //logger.log(Level.ERROR, "Statement isn't closed.");
        }
    }

    public void closeConnection(Connection con, Statement st) {
        try {
            con.close();
        } catch (SQLException e) {
            //logger.log(Level.ERROR, "Connection isn't return to the pool. ");
        }
        try {
            st.close();
        } catch (SQLException e) {
            //logger.log(Level.ERROR, "Statement isn't closed.");
        }
    }

    private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection;
        while ((connection = queue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            ((PooledConnection) connection).reallyClose();
        }
    }

    private class PooledConnection implements Connection {
        private Connection connection;

        public PooledConnection(Connection c) throws SQLException {
            this.connection = c;
            this.connection.setAutoCommit(true);
        }

        public void reallyClose() throws SQLException {
            connection.close();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public void close() throws SQLException {
            if (connection.isClosed()) {
                throw new SQLException("Attempting to close closed connection.");
            }
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }

            if (!availableConnection.remove(this)) {
                throw new SQLException("Error deleting connection from the given away connections pool.");
            }

            if (!usedConnection.offer(this)) {
                throw new SQLException("Error allocating connection in the pool.");
            }


        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements)
                throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public Statement createStatement(int resultSetType,
                                         int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType,
                    resultSetConcurrency);
        }

        @Override
        public Statement createStatement(int resultSetType,
                                         int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return connection.createStatement(resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes)
                throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType,
                                             int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType,
                    resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType,
                                             int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return connection.prepareCall(sql, resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareStatement(sql, resultSetType,
                    resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql,
                                                  int resultSetType, int resultSetConcurrency,
                                                  int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public void setClientInfo(String name, String value)
                throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public void abort(Executor arg0) throws SQLException {
            connection.abort(arg0);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void releaseSavepoint(Savepoint arg0) throws SQLException {
            connection.releaseSavepoint(arg0);
        }

        @Override
        public void rollback(Savepoint arg0) throws SQLException {
            connection.rollback(arg0);
        }

        @Override
        public void setClientInfo(Properties arg0)
                throws SQLClientInfoException {
            connection.setClientInfo(arg0);
        }

        @Override
        public void setNetworkTimeout(Executor arg0, int arg1)
                throws SQLException {
            connection.setNetworkTimeout(arg0, arg1);
        }

        @Override
        public void setSchema(String arg0) throws SQLException {
            connection.setSchema(arg0);
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
            connection.setTypeMap(arg0);
        }
    }
}