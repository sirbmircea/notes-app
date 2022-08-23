package app.service.impl;

import app.NotesAppApplication;
import app.exceptions.InvalidNumberOfArgumentsException;
import app.model.Note;
import app.service.ConsoleRequestHandler;
import app.service.NoteService;
import app.service.ServiceProvider;
import org.springframework.boot.SpringApplication;

import java.util.logging.Logger;

public class ConsoleRequestHandlerImpl implements ConsoleRequestHandler {
    private static final Logger logger = Logger.getLogger(ConsoleRequestHandlerImpl.class.getName());
    private NoteService noteService;

    public ConsoleRequestHandlerImpl() {
        noteService = ServiceProvider.getInstance().get(NoteService.class);
    }

    @Override
    public void runSpringBoot(String... args) {
        SpringApplication.run(NotesAppApplication.class, args);
    }

    @Override
    public void listCommand(String... params) {
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

    @Override
    public void addCommand(String... params) {
        if (params.length==2) {
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

