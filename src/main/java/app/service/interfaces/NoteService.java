package app.service.interfaces;

import app.dto.NoteDTO;

import java.util.Set;

public interface NoteService {
    NoteDTO add(NoteDTO note);

    Set<NoteDTO> list();

    NoteDTO list(String noteTitle);
}