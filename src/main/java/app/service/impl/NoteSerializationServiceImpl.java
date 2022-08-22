package app.service.impl;

import app.model.Note;
import app.service.NoteSerializationService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Optional;
import java.util.Set;


public class NoteSerializationServiceImpl implements NoteSerializationService {
    Gson gson;

    public NoteSerializationServiceImpl() {
        gson = new Gson();
    }

    @Override
    public Optional<Set<Note>> jsonToNoteSet(String jsonString) {
        Type userSetType = new TypeToken<Set<Note>>() {
        }.getType();
        Set<Note> noteSet = gson.fromJson(jsonString, userSetType);
        return Optional.ofNullable(noteSet);
    }

    @Override
    public String noteSetToJSON(Set<Note> noteSet) {
        return gson.toJson(noteSet);
    }
}