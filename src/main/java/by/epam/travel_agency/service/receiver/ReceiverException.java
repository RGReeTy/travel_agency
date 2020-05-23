package by.epam.travel_agency.service.receiver;

public class ReceiverException extends Exception {

    private static final long serialVersionUID = 1L;

    public ReceiverException() {
    }

    public ReceiverException(String message) {
        super(message);
    }

    public ReceiverException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReceiverException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "ReceiverException{}" + this.getMessage();
    }

}
