package app.controller;

import app.model.Note;
import app.service.NoteService;
import app.service.ServiceProvider;

import java.util.Set;

public class NoteControllerConsole {
    private NoteService noteService;

    private static class SingletonHolder {
        private static final NoteControllerConsole INSTANCE = new NoteControllerConsole();
    }

    public static NoteControllerConsole getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public NoteControllerConsole() {
        noteService = ServiceProvider.getInstance().get(NoteService.class);
    }


    public Note add(Note newNote) {
        return noteService.add(newNote);
    }

    public Set<Note> list() {
        return noteService.list();
    }

    public Note list(String noteTitle) {
        return noteService.list(noteTitle);
    }


}
