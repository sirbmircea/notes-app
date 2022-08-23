package app.util;

import app.NotesAppApplication;
import app.controller.NoteControllerConsole;
import app.exceptions.InvalidNumberOfArgumentsException;
import app.exceptions.NoArgumentsException;
import app.model.Command;
import app.model.ConsoleRequestObject;
import app.model.Note;
import org.springframework.boot.SpringApplication;

import java.util.Arrays;
import java.util.logging.Logger;

public class ArgumentsParser {
    private ArgumentsParser() {
    }

    private static final Logger logger = Logger.getLogger(ArgumentsParser.class.getName());
    private static NoteControllerConsole noteControllerConsole = NoteControllerConsole.getInstance();

    public static void parseToConsoleRequestObject(String... args) {
        if (args.length == 0) {
            try {
                throw new NoArgumentsException();
            } catch (NoArgumentsException e) {
                logger.severe(e.getMessage());
            }
        } else if (Arrays.stream(Command.values()).anyMatch(command -> command.name().toLowerCase().equals(args[0]))) {
            Command command = Arrays.stream(Command.values()).filter(x -> x.name().toLowerCase().equals(args[0])).findFirst().get();
            String[] params = Arrays.copyOfRange(args, 1, args.length);
            ConsoleRequestObject consoleRequestObject = new ConsoleRequestObject(command, params, args);
            commandDispatcher(consoleRequestObject);
        } else {
            try {
                throw new NoSuchMethodException("The method " + args[0] + " doesn't exist!");
            } catch (NoSuchMethodException e) {
                logger.severe(e.getMessage());
            }
        }
    }

    public static void commandDispatcher(ConsoleRequestObject consoleRequestObject) {
        switch (consoleRequestObject.getCommand()) {
            case ADD:
                addCommand(consoleRequestObject.getParams());
                break;
            case LIST:
                listCommand(consoleRequestObject.getParams());
                break;
            case RUNSPRINGBOOT:
                runSpringBoot(consoleRequestObject.getArgs());
        }
    }

    private static void runSpringBoot(String... args) {
        SpringApplication.run(NotesAppApplication.class, args);
    }

    private static void listCommand(String... params) {
        switch (params.length) {
            case 0:
                noteControllerConsole.list();
                break;
            case 1:
                noteControllerConsole.list(params[0]);
                break;
            default:
                try {
                    throw new InvalidNumberOfArgumentsException("The method list receives 0 or 1 parameters!");
                } catch (InvalidNumberOfArgumentsException e) {
                    logger.severe(e.getMessage());
                }
                break;
        }
    }

    private static void addCommand(String... params) {
        switch (params.length) {
            case 2:
                noteControllerConsole.add(new Note(params[0], params[1]));
                break;
            default:
                try {
                    throw new InvalidNumberOfArgumentsException("The method add receives 2 parameters!");
                } catch (InvalidNumberOfArgumentsException e) {
                    logger.severe(e.getMessage());
                }
                break;
        }
    }
}
