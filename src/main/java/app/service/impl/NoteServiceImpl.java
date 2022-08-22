package app.service.impl;

import app.model.Note;
import app.repository.NoteRepository;
import app.service.NoteService;

import java.util.Set;

public class NoteServiceImpl implements NoteService {

    NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note add(Note newNote) {
        if (noteRepository.list().stream().anyMatch(note -> note.equals(newNote))) {
            return new Note("", "");
        } else {
            Note note = noteRepository.add(newNote);
            return note;
        }
    }

    @Override
    public Set<Note> list() {
        return noteRepository.list();
    }

    @Override
    public Note list(String noteTitle) {
        return noteRepository.list(noteTitle);
    }

}
