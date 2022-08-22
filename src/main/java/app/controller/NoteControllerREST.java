package app.controller;

import app.model.Note;
import app.service.NoteService;
import app.service.ServiceProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/notes")
public class NoteControllerREST {
    private final NoteService noteService;

    public NoteControllerREST() {
        this.noteService = ServiceProvider.getInstance().get(NoteService.class);
    }

    @GetMapping()
    public ResponseEntity<Set<Note>> list() {
        return new ResponseEntity<>(noteService.list(), HttpStatus.OK);
    }

    @GetMapping("/search/{noteTitle}")
    public ResponseEntity<Note> list(@PathVariable("noteTitle") String noteTitle) {
        return new ResponseEntity<>(noteService.list(noteTitle), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Note> add(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.add(note), HttpStatus.CREATED);
    }
}
