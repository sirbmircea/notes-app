package app;

import app.util.ArgumentsParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;

@SpringBootApplication
public class NotesAppApplication {

    public static void main(String[] args) {

        Console console = System.console();
        if (console != null || args.length > 0) {
            ArgumentsParser.parseToConsoleRequestObject(args);
        } else {
            SpringApplication.run(NotesAppApplication.class, args);
        }

    }

}