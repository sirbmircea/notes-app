package app.exceptions;

public class InvalidNumberOfArgumentsException extends Exception {

    public InvalidNumberOfArgumentsException(String errorMessage) {
        super(errorMessage);
    }
}
