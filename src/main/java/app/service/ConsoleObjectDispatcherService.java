package app.service;

import app.NotesAppApplication;
import app.exceptions.ExceptionContainer;
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

    public void dispatch(ConsoleRequestObject consoleRequestObject) {
        if (consoleRequestObject.getArgs() == null) {
            logger.severe(ExceptionContainer.NO_ARGS_GIVEN);
        } else if (consoleRequestObject.getCommand() == null) {
            String exception = String.format(ExceptionContainer.NO_SUCH_METHOD, consoleRequestObject.getArgs()[0]);
            logger.severe(exception);
        } else {
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
                String exception = String.format(ExceptionContainer.INVALID_NO_OF_ARGS, "list", "0 or 1");
                logger.severe(exception);
                break;
        }
    }

    private void addCommand(String... params) {
        if (params.length == 2) {
            noteService.add(new Note(params[0], params[1]));
        } else {
            String exception = String.format(ExceptionContainer.INVALID_NO_OF_ARGS, "add", "2");
            logger.severe(exception);
        }

    }
}
