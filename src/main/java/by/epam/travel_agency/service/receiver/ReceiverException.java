package by.epam.travel_agency.service.receiver;

/**
 * The type Receiver exception.
 */
public class ReceiverException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Receiver exception.
     */
    public ReceiverException() {
    }

    /**
     * Instantiates a new Receiver exception.
     *
     * @param message the message
     */
    public ReceiverException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Receiver exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ReceiverException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Receiver exception.
     *
     * @param cause the cause
     */
    public ReceiverException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "ReceiverException{}" + this.getMessage();
    }

}
