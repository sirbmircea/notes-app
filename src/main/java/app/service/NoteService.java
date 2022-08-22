package app.service;

import app.model.Note;

import java.util.Set;

public interface NoteService {
    Note add(Note note);

    Set<Note> list();

    Note list(String noteTitle);
}