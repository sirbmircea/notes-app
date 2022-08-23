package app.service;

import app.exceptions.NoArgumentsException;
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
            try {
                throw new NoArgumentsException();
            } catch (NoArgumentsException e) {
                logger.severe(e.getMessage());
            }
        } else if (Arrays.stream(Command.values())
                .anyMatch(command -> command.name().toLowerCase().equals(args[0]))) {
            String[] params = Arrays.copyOfRange(args, 1, args.length);
            return new ConsoleRequestObject(Command.valueOf(args[0].toUpperCase()), params, args);
        } else {
            try {
                throw new NoSuchMethodException("The hhhhhhh " + args[0] + " doesn't exist!");
            } catch (NoSuchMethodException e) {
                logger.severe(e.getMessage());
            }
        }
        return new ConsoleRequestObject();
    }

}
