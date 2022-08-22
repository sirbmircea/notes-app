package app.service.impl;

import app.model.Note;
import app.repository.NoteRepository;
import app.service.NoteService;

import java.util.Set;
import java.util.logging.Logger;

public class NoteServiceImpl implements NoteService {
    private static final Logger logger = Logger.getLogger(NoteServiceImpl.class.getName());

    NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note add(Note newNote) {
        if (noteRepository.list().stream().anyMatch(note -> note.equals(newNote))) {
            logger.warning("We have a duplicate!");
            return new Note("", "");
        } else {
            Note note = noteRepository.add(newNote);
            logger.info("The note was added!");
            return note;
        }
    }

    @Override
    public Set<Note> list() {
        Set<Note> noteSet = noteRepository.list();
        if (!noteSet.isEmpty()) {
            noteSet.forEach(note -> logger.info(note.toString()));
        } else {
            logger.info("List is empty!");
        }
        return noteSet;
    }

    @Override
    public Note list(String noteTitle) {
        Note note = noteRepository.list(noteTitle);
        if (!note.getNoteTitle().equals("")) {
            logger.info(String.format("Fond the note: %s", note));
        } else {
            logger.info(String.format("Couldn't find the note with the title: %s!", noteTitle));
        }
        return note;
    }

}
