package by.epam.travel_agency.dao.connection_pool;

/**
 * The type Connection pool exception.
 */
public class ConnectionPoolException extends Exception {

    /**
     * Instantiates a new Connection pool exception.
     */
    public ConnectionPoolException() {
    }

    /**
     * Instantiates a new Connection pool exception.
     *
     * @param message the message
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Connection pool exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public ConnectionPoolException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instantiates a new Connection pool exception.
     *
     * @param throwable the throwable
     */
    public ConnectionPoolException(Throwable throwable) {
        super(throwable);
    }
}
