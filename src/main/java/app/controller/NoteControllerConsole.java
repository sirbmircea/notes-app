package app.controller;

import app.exceptions.InvalidNumberOfArgumentsException;
import app.exceptions.NoArgumentsException;
import app.model.Note;
import app.service.NoteService;
import app.service.ServiceProvider;

import java.util.Set;
import java.util.logging.Logger;

public class NoteControllerConsole {
    private static final Logger logger = Logger.getLogger(NoteControllerConsole.class.getName());
    private NoteService noteService = ServiceProvider.getInstance().get(NoteService.class);
    private final String ADD_RECEIVES_2_PARAMETERS = "The method add receives 2 parameters!";


    public NoteControllerConsole(String... args) throws NoArgumentsException, InvalidNumberOfArgumentsException, NoSuchMethodException {
        switch (args.length) {
            case 0:
                throw new NoArgumentsException();
            case 1:
                litsAll(args);
                break;
            case 2:
                listSingleNote(args);
                break;
            case 3:
                add(args);
                break;
            default:
                invalidNumberOfArguments(args);
                break;
        }
    }

    public void litsAll(String... args) throws InvalidNumberOfArgumentsException, NoSuchMethodException {
        if (args[0].equals("list")) {
            Set<Note> noteSet = noteService.list();
            if (!noteSet.isEmpty()) {
                noteSet.forEach(note -> logger.info(note.toString()));
            } else {
                logger.info("List is empty!");
            }

        } else if (args[0].equals("add")) {
            throw new InvalidNumberOfArgumentsException(ADD_RECEIVES_2_PARAMETERS);
        } else {
            throw new NoSuchMethodException("The method add doesn't exist!");
        }
    }

    public void listSingleNote(String... args) throws InvalidNumberOfArgumentsException, NoSuchMethodException {
        if (args[0].equals("list")) {
            Note note = noteService.list(args[1]);
            if (!note.getNoteTitle().equals("")) {
                logger.info(note.toString());
            } else {
                logger.info(String.format("Couldn't find the note with the title: %s!", args[1]));
            }

        } else if (args[0].equals("add")) {
            throw new InvalidNumberOfArgumentsException(ADD_RECEIVES_2_PARAMETERS);
        } else {
            throw new NoSuchMethodException("The method " + args[0] + " doesn't exist!");
        }
    }

    public void add(String... args) throws InvalidNumberOfArgumentsException, NoSuchMethodException {
        if (args[0].equals("add")) {
            Note note = noteService.add(new Note(args[1], args[2]));
            if (note.getNoteTitle().equals("")) {
                logger.warning("We have a duplicate!");
            } else {
                logger.info("The note was added!");
            }

        } else if (args[0].equals("list")) {
            throw new InvalidNumberOfArgumentsException("The method list receives 0 or 1 parameters!");
        } else {
            throw new NoSuchMethodException("The method " + args[0] + " doesn't exist!");
        }
    }

    public void invalidNumberOfArguments(String... args) throws InvalidNumberOfArgumentsException {
        if (args[0].equals("add")) {
            throw new InvalidNumberOfArgumentsException(ADD_RECEIVES_2_PARAMETERS);
        } else if (args[0].equals("list")) {
            throw new InvalidNumberOfArgumentsException("The method list receives 0 or 1 parameters!");
        }
        throw new InvalidNumberOfArgumentsException("Invalid number of arguments!");
    }
}
