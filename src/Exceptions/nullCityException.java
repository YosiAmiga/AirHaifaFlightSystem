package Exceptions;

public class nullCityException extends Exception{
    private static final long serialVersionUID = 1L;

    public nullCityException() {
        super("city invalid and equals null!");

    }
}