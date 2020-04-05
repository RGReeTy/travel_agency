package main.java.by.epam.travel_agency.dao.connection_pool;

public class ConnectionPoolException extends Exception {

    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ConnectionPoolException(Throwable throwable) {
        super(throwable);
    }
}
