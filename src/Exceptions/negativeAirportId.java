package Exceptions;

public class negativeAirportId extends Exception{
    private static final long serialVersionUID = 1L;

    public negativeAirportId() {
        super("airportID can't be negative!");

    }

}
