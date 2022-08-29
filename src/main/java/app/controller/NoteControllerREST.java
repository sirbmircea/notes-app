package app.controller;

import app.dto.NoteDTO;
import app.service.interfaces.NoteService;
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
    public ResponseEntity<Set<NoteDTO>> list() {
        return new ResponseEntity<>(noteService.list(), HttpStatus.OK);
    }

    @GetMapping("/search/{noteTitle}")
    public ResponseEntity<NoteDTO> list(@PathVariable("noteTitle") String noteTitle) {
        return new ResponseEntity<>(noteService.list(noteTitle), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<NoteDTO> add(@RequestBody NoteDTO noteDTO) {
        return new ResponseEntity<>(noteService.add(noteDTO), HttpStatus.CREATED);
    }
}
