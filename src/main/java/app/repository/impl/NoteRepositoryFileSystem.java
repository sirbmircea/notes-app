package app.repository.impl;

import app.model.Note;
import app.repository.interfaces.NoteRepository;
import app.service.interfaces.NoteSerializationService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class NoteRepositoryFileSystem implements NoteRepository {

    private NoteSerializationService noteSerializationService;
    private Path path = Paths.get("notes.json");

    public NoteRepositoryFileSystem(NoteSerializationService noteSerializationService) {
        this.noteSerializationService = noteSerializationService;
    }


    @Override
    public Note add(Note note) {
        Set<Note> noteSet = list();
        noteSet.add(note);
        String formatedSet = noteSerializationService.noteSetToJSON(noteSet);
        try (FileWriter file = new FileWriter(path.toString())) {
            file.write(formatedSet);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return note;
    }

    @Override
    public Set<Note> list() {
        String jSONSet = "";
        createJSONIfDoesntExist();
        try (FileReader reader = new FileReader(path.toString())) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            jSONSet = bufferedReader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return noteSerializationService
                .jsonToNoteSet(jSONSet)
                .orElseGet(HashSet::new);
    }

    @Override
    public Note list(String noteTitle) {
        return list().stream()
                .filter(note1 -> note1.getNoteTitle()
                        .equals(noteTitle))
                .findAny()
                .orElseGet(() -> new Note("", ""));

    }


    private void createJSONIfDoesntExist() {

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
