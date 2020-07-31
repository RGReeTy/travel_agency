package by.epam.travel_agency.dao.exception;


/**
 * The type Get incorrect parameter exception.
 */
public class GetIncorrectParameterException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new Get incorrect parameter exception.
	 */
	public GetIncorrectParameterException() {
	}


	/**
	 * Instantiates a new Get incorrect parameter exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public GetIncorrectParameterException(String message, Throwable cause) {
		super(message, cause);
	}


	/**
	 * Instantiates a new Get incorrect parameter exception.
	 *
	 * @param message the message
	 */
	public GetIncorrectParameterException(String message) {
		super(message);
	}


	/**
	 * Instantiates a new Get incorrect parameter exception.
	 *
	 * @param cause the cause
	 */
	public GetIncorrectParameterException(Throwable cause) {
		super(cause);
	}
}
