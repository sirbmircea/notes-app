package app;

import app.controller.NoteControllerConsole;
import app.exceptions.InvalidNumberOfArgumentsException;
import app.exceptions.NoArgumentsException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;

@SpringBootApplication
public class NotesAppApplication {

    public static void main(String[] args) {

        Console console = System.console();
        if (console != null || args.length > 0) {
            try {
                new NoteControllerConsole(args);
            } catch (NoArgumentsException | NoSuchMethodException | InvalidNumberOfArgumentsException e) {
                e.printStackTrace();
            }
        } else {
            SpringApplication.run(NotesAppApplication.class, args);
        }

    }

}