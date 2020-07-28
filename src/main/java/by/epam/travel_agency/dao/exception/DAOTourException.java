package by.epam.travel_agency.dao.exception;

/**
 * The type Dao tour exception.
 */
public class DAOTourException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new Dao tour exception.
	 */
	public DAOTourException() {
	}

	/**
	 * Instantiates a new Dao tour exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public DAOTourException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new Dao tour exception.
	 *
	 * @param message the message
	 */
	public DAOTourException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new Dao tour exception.
	 *
	 * @param cause the cause
	 */
	public DAOTourException(Throwable cause) {
		super(cause);
	}
}
