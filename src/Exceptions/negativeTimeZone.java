package Exceptions;

public class negativeTimeZone extends Exception{
    private static final long serialVersionUID = 1L;

    public negativeTimeZone() {
        super("timeZone can't be negative!");

    }
}

