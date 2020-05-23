package by.epam.travel_agency.dao;

public class DAOTourException extends Exception{

	private static final long serialVersionUID = 1L;

	public DAOTourException() {
	}

	public DAOTourException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOTourException(String message) {
		super(message);
	}

	public DAOTourException(Throwable cause) {
		super(cause);
	}
}
