package app;

import app.controller.NoteControllerConsole;
import app.exceptions.InvalidNumberOfArgumentsException;
import app.exceptions.NoArgumentsException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(NotesAppApplication.class, args);
        try {
            new NoteControllerConsole(args);
        } catch (NoArgumentsException | NoSuchMethodException | InvalidNumberOfArgumentsException e) {
            e.printStackTrace();
        }
    }

}