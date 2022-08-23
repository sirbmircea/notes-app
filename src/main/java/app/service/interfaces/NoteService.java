package app.service.interfaces;

import app.model.Note;

import java.util.Set;

public interface NoteService {
    Note add(Note note);

    Set<Note> list();

    Note list(String noteTitle);
}