package app.util;

import app.exceptions.NoArgumentsException;
import app.model.Command;
import app.model.ConsoleRequestObject;
import app.service.ConsoleRequestHandler;
import app.service.impl.ConsoleRequestHandlerImpl;

import java.util.Arrays;
import java.util.logging.Logger;

public class ArgumentsHandler {

    private static ConsoleRequestHandler consoleRequestHandler = new ConsoleRequestHandlerImpl();
    private static final Logger logger = Logger.getLogger(ArgumentsHandler.class.getName());

    private ArgumentsHandler() {
    }

    public static ConsoleRequestObject parseAndDispatch(String... args) {
        if (args.length == 0) {
            try {
                throw new NoArgumentsException();
            } catch (NoArgumentsException e) {
                logger.severe(e.getMessage());
            }
        } else if (Arrays.stream(Command.values()).anyMatch(command -> command.name().toLowerCase().equals(args[0]))) {
            Command command = Arrays.stream(Command.values()).filter(x -> x.name().toLowerCase().equals(args[0])).findFirst().get();
            String[] params = Arrays.copyOfRange(args, 1, args.length);
            return new ConsoleRequestObject(command, params, args);
        } else {
            try {
                throw new NoSuchMethodException("The method " + args[0] + " doesn't exist!");
            } catch (NoSuchMethodException e) {
                logger.severe(e.getMessage());
            }
        }
        return new ConsoleRequestObject();
    }

    public static void commandDispatcher(ConsoleRequestObject consoleRequestObject) {
        if(consoleRequestObject.getCommand()!=null){
            switch (consoleRequestObject.getCommand()) {
                case ADD:
                    consoleRequestHandler.addCommand(consoleRequestObject.getParams());
                    break;
                case LIST:
                    consoleRequestHandler.listCommand(consoleRequestObject.getParams());
                    break;
                case RUNSPRINGBOOT:
                    consoleRequestHandler.runSpringBoot(consoleRequestObject.getArgs());
            }
        }
    }
}
