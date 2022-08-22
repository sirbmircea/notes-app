package app.repository;

import app.model.Note;

import java.util.Set;

public interface NoteRepository {
    Note add(Note note);

    Set<Note> list();

    Note list(String noteTitle);
}
