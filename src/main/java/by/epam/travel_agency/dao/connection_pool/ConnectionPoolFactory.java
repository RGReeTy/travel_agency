package by.epam.travel_agency.dao.connection_pool;

public class ConnectionPoolFactory {
    private static final ConnectionPoolFactory instance = new ConnectionPoolFactory();

    private final ConnectionPool pool = new ConnectionPool();

    private ConnectionPoolFactory() {
    }

    public static ConnectionPoolFactory getInstance() {
        return instance;
    }

    public ConnectionPool getConnectionPool() {
        return pool;
    }
}
