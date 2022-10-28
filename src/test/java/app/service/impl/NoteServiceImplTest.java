package app.service.impl;

import app.model.Note;
import app.repository.interfaces.NoteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

    @InjectMocks
    NoteServiceImpl noteService;
    @Mock
    NoteRepository noteRepository;

    @Test
    @DisplayName("Listing from an empty database -> returns an empty list")
    void testList_whenNoteSetIsEmpty() {
        when(noteRepository.list())
                .thenReturn(new HashSet<>());
        assertTrue(noteService.list().isEmpty());
        verify(noteRepository).list();
    }

    @Test
    @DisplayName("Listing from a populated database -> returns a populated list")
    void testList_whenNoteSetIsHasItems() {
        var note = new Note();
        when(noteRepository.list())
                .thenReturn(Set.of(note));
        assertTrue(noteService.list().contains(note));
        verify(noteRepository).list();
    }

    @Test
    @DisplayName("Searching for a note title that doesn't exist -> returns an empty note")
    void testList_whenSearchedNoteDoesntExist() {
        var nonexistentTitle = "Bla bla";
        when(noteRepository.list(nonexistentTitle))
                .thenReturn(Note.empty());
        assertTrue(noteService.list(nonexistentTitle).getNoteTitle().isEmpty());
        verify(noteRepository).list(anyString());
    }

    @Test
    @DisplayName("Searching for a note title that exists -> returns that note")
    void testList_whenSearchedNoteThatExist() {
        var existentNoteTitle = "Some title";
        when(noteRepository.list(existentNoteTitle))
                .thenReturn(new Note(existentNoteTitle, ""));
        assertEquals(noteService.list(existentNoteTitle).getNoteTitle(), existentNoteTitle);
        verify(noteRepository).list(anyString());
    }

    @Test
    @DisplayName("Trying to add a note with an already existing title -> returns an empty note")
    void testAdd_whenWeHaveADuplicate() {
        var existingNote = new Note("Duplicate title", "");
        when(noteRepository.list(existingNote.getNoteTitle()))
                .thenReturn(existingNote);
        assertTrue(noteService.add(existingNote).getNoteTitle().isEmpty());
        verify(noteRepository).list(anyString());
    }

    @Test
    @DisplayName("Trying to add a non-existing note -> returns that note")
    void testAdd_whenNoteIsntInDB() {
        var existingNote = new Note("Something", "Something");
        when(noteRepository.list(existingNote.getNoteTitle()))
                .thenReturn(Note.empty());
        when(noteRepository.add(existingNote))
                .thenReturn(existingNote);
        assertEquals(noteService.add(existingNote), existingNote);
        verify(noteRepository).list(anyString());
        verify(noteRepository).add(any(Note.class));
    }
}