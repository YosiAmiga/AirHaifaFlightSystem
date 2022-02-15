package Exceptions;

public class nullAirportNumeber extends Exception {
    private static final long serialVersionUID = 1L;

    public nullAirportNumeber() {
        super("airport number can't be negative!");

    }
}
