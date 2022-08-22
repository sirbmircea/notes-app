package app.controller;

import app.exceptions.InvalidNumberOfArgumentsException;
import app.exceptions.NoArgumentsException;
import app.model.Note;
import app.service.NoteService;
import app.service.ServiceProvider;

import java.util.Set;

public class NoteControllerConsole {
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
                noteSet.forEach(System.out::println);
            } else {
                System.out.println("List is empty!");
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
                System.out.println(note);
            } else {
                System.out.println("Couldn't find the note with the title: " + args[1] + "!");
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
                System.out.println("We have a duplicate!");
            } else {
                System.out.println("The note was added!");
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
