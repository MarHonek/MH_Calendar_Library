package mh.calendarlibrary.exceptions;

/**
 * @author Martin Honek
 */
public class InvalidDaysCountException extends RuntimeException {

    public InvalidDaysCountException() {
        super();
    }

    public InvalidDaysCountException(String message) {
        super(message);
    }

    public InvalidDaysCountException(String message, Throwable cause) {
        super(message, cause);
    }
}
