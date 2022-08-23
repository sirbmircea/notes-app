package app.service.interfaces;

import app.model.Note;

import java.util.Optional;
import java.util.Set;

public interface NoteSerializationService {

    Optional<Set<Note>> jsonToNoteSet(String jsonString);

    String noteSetToJSON(Set<Note> noteList);
}
