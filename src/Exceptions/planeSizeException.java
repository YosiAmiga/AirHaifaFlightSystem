package Exceptions;

public class planeSizeException extends Exception {
    private static final long serialVersionUID = 1L;
    public planeSizeException() {
        super("The plane size cannot be negative!");
    }

}


