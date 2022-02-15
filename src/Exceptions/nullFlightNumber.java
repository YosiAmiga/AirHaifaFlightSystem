package Exceptions;

public class nullFlightNumber extends Exception{
    private static final long serialVersionUID = 1L;
    public nullFlightNumber() {
        super("The flight number is currently null!");
    }

}
