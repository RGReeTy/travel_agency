package by.epam.travel_agency.dao.exception;

/**
 * The type Dao user exception.
 */
public class DAOUserException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new Dao user exception.
	 */
	public DAOUserException() {
	}

	/**
	 * Instantiates a new Dao user exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public DAOUserException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Dao user exception.
	 *
	 * @param message the message
	 */
	public DAOUserException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Dao user exception.
	 *
	 * @param cause the cause
	 */
	public DAOUserException(Throwable cause) {
		super(cause);
	}
}
