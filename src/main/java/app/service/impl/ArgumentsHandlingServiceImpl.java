package app.service.impl;

import app.NotesAppApplication;
import app.exceptions.ExceptionContainer;
import app.model.Command;
import app.model.Note;
import app.service.ServiceProvider;
import app.service.interfaces.ArgumentsHandlingService;
import app.service.interfaces.NoteService;
import org.springframework.boot.SpringApplication;

import java.util.Arrays;
import java.util.logging.Logger;

public class ArgumentsHandlingServiceImpl implements ArgumentsHandlingService {

    private static final Logger logger = Logger.getLogger(ArgumentsHandlingServiceImpl.class.getName());

    private NoteService noteService;

    public ArgumentsHandlingServiceImpl() {
        noteService = ServiceProvider.getInstance().get(NoteService.class);
    }

    @Override
    public void parseAndDispatch(String... args) {
        if (args.length == 0) {
            logger.severe(ExceptionContainer.NO_ARGS_GIVEN);
        } else if (Arrays.stream(Command.values())
                .noneMatch(command -> command.name().toLowerCase().equals(args[0]))) {
            String exception = String.format(ExceptionContainer.NO_SUCH_METHOD, args[0]);
            logger.severe(exception);
        } else {
            switch (Command.valueOf(args[0].toUpperCase())) {
                case ADD:
                    addCommand(args);
                    break;
                case LIST:
                    listCommand(args);
                    break;
                case RUNSPRINGBOOT:
                    runSpringBoot(args);
                    break;
            }
        }
    }

    private void runSpringBoot(String... args) {
        SpringApplication.run(NotesAppApplication.class, args);
    }


    private void listCommand(String... params) {
        switch (params.length) {
            case 1:
                noteService.list();
                break;
            case 2:
                noteService.list(params[1]);
                break;
            default:
                String exception = String.format(ExceptionContainer.INVALID_NO_OF_ARGS, "list", "0 or 1");
                logger.severe(exception);
                break;
        }
    }

    private void addCommand(String... params) {
        if (params.length == 3) {
            noteService.add(new Note(params[1], params[2]));
        } else {
            String exception = String.format(ExceptionContainer.INVALID_NO_OF_ARGS, "add", "2");
            logger.severe(exception);
        }

    }
}
