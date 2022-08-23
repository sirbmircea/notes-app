package app.service;

import app.NotesAppApplication;
import app.exceptions.InvalidNumberOfArgumentsException;
import app.model.ConsoleRequestObject;
import app.model.Note;
import app.service.interfaces.NoteService;
import org.springframework.boot.SpringApplication;

import java.util.logging.Logger;

public class ConsoleObjectDispatcherService {

    private static final Logger logger = Logger.getLogger(ConsoleObjectDispatcherService.class.getName());

    private NoteService noteService;

    public ConsoleObjectDispatcherService() {
        noteService = ServiceProvider.getInstance().get(NoteService.class);
    }

    public void commandDispatcher(ConsoleRequestObject consoleRequestObject) {
        if (consoleRequestObject.getCommand() != null) {
            switch (consoleRequestObject.getCommand()) {
                case ADD:
                    addCommand(consoleRequestObject.getParams());
                    break;
                case LIST:
                    listCommand(consoleRequestObject.getParams());
                    break;
                case RUNSPRINGBOOT:
                    runSpringBoot(consoleRequestObject.getArgs());
                    break;
            }
        }
    }

    private void runSpringBoot(String... args) {
        SpringApplication.run(NotesAppApplication.class, args);
    }


    private void listCommand(String... params) {
        switch (params.length) {
            case 0:
                noteService.list();
                break;
            case 1:
                noteService.list(params[0]);
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

    private void addCommand(String... params) {
        if (params.length == 2) {
            noteService.add(new Note(params[0], params[1]));
        } else {
            try {
                throw new InvalidNumberOfArgumentsException("The method add receives 2 parameters!");
            } catch (InvalidNumberOfArgumentsException e) {
                logger.severe(e.getMessage());
            }
        }

    }
}
