package by.epam.travel_agency.dao;

public class DAOUserException extends Exception{

	private static final long serialVersionUID = 1L;

	public DAOUserException() {
	}

	public DAOUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOUserException(String message) {
		super(message);
	}

	public DAOUserException(Throwable cause) {
		super(cause);
	}
}
