package app;

import app.controller.NoteControllerConsole;
import app.exceptions.InvalidNumberOfArgumentsException;
import app.exceptions.NoArgumentsException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;
import java.util.logging.Logger;

@SpringBootApplication
public class NotesAppApplication {
    private static final Logger logger = Logger.getLogger(NotesAppApplication.class.getName());

    public static void main(String[] args) {

        Console console = System.console();
        if (console != null || args.length > 0) {
            if (args.length == 1 && args[0].equals("run-rest-controller") ) {
                SpringApplication.run(NotesAppApplication.class, args);
            } else {
                try {
                    new NoteControllerConsole(args);
                } catch (NoArgumentsException | InvalidNumberOfArgumentsException | NoSuchMethodException e) {
                    logger.severe(e.getMessage());
                }
            }
        } else {
            SpringApplication.run(NotesAppApplication.class, args);
        }

    }

}