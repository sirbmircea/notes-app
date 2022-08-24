package app;

import app.controller.NoteControllerCLI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;

@SpringBootApplication
public class NotesAppApplication {

    public static void main(String[] args) {
        Console console = System.console();
        if (console != null || args.length > 0) {
            NoteControllerCLI.getInstance().consoleRequest(args);
        } else {
            SpringApplication.run(NotesAppApplication.class, args);
        }
    }
}