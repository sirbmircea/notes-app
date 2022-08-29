package app.service.impl;

import app.dto.NoteDTO;
import app.model.Note;
import app.repository.interfaces.NoteRepository;
import app.service.interfaces.NoteService;

import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class NoteServiceImpl implements NoteService {
    private static final Logger logger = Logger.getLogger(NoteServiceImpl.class.getName());

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public NoteDTO add(NoteDTO noteDTO) {
        Note newNote = convertDTOtoEntity(noteDTO);
        if (noteRepository.list(noteDTO.getNoteTitle()).equals(newNote)) {
            logger.warning("We have a duplicate!");
            return new NoteDTO(0, "", "");
        } else {
            Note note = noteRepository.add(newNote);
            String message = String.format("The note %s was added!", note);
            logger.info(message);
            return convertEntityToDTO(note);
        }
    }

    @Override
    public Set<NoteDTO> list() {
        Set<Note> noteSet = noteRepository.list();
        if (!noteSet.isEmpty()) {
            noteSet.forEach(note -> logger.info(note.toString()));
        } else {
            logger.info("List is empty!");
        }
        return noteSet.stream().map(this::convertEntityToDTO).collect(Collectors.toSet());
    }

    @Override
    public NoteDTO list(String noteTitle) {
        Note note = noteRepository.list(noteTitle);
        if (!note.getNoteTitle().equals("")) {
            String message = String.format("Fond the note: %s", note);
            logger.info(message);
        } else {
            String message = String.format("Couldn't find the note with the title: %s!", noteTitle);
            logger.info(message);
        }
        return convertEntityToDTO(note);
    }

    private NoteDTO convertEntityToDTO(Note note) {
        return new NoteDTO(note.getId(), note.getNoteTitle(), note.getNoteContent());
    }

    private Note convertDTOtoEntity(NoteDTO noteDTO) {
        return new Note(noteDTO.getId(), noteDTO.getNoteTitle(), noteDTO.getNoteContent());
    }

}
