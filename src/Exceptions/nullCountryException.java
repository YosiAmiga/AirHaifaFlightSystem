package Exceptions;

public class nullCountryException extends Exception {
    private static final long serialVersionUID = 1L;

    public nullCountryException() {
        super("country invalid and equals null!");

    }
}