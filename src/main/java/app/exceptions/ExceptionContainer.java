package app.exceptions;

public class ExceptionContainer {
    private ExceptionContainer() {
    }

    public static final String INVALID_NO_OF_ARGS = "Thy the method %s with %s parameters!";
    public static final String NO_ARGS_GIVEN = "No arguments given!";
    public static final String NO_SUCH_METHOD = "The method %s doesn't exist!";

}