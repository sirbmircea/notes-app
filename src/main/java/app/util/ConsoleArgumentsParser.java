package app.util;

import app.exceptions.ExceptionContainer;
import app.model.Command;
import app.model.ConsoleRequestObject;

import java.util.Arrays;
import java.util.logging.Logger;

public class ConsoleArgumentsParser {

    private static final Logger logger = Logger.getLogger(ConsoleArgumentsParser.class.getName());

    private ConsoleArgumentsParser() {
    }

    public static ConsoleRequestObject parse(String... args) {
        if (args.length == 0) {
            logger.severe(ExceptionContainer.NO_ARGS_GIVEN);
        } else if (Arrays.stream(Command.values())
                .anyMatch(command -> command.name().toLowerCase().equals(args[0]))) {
            String[] params = Arrays.copyOfRange(args, 1, args.length);
            return new ConsoleRequestObject(Command.valueOf(args[0].toUpperCase()), params, args);
        } else {
            String exception = String.format(ExceptionContainer.NO_SUCH_METHOD, args[0]);
            logger.severe(exception);
        }
        return new ConsoleRequestObject();
    }

}
