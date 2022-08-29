package app.dto;

import java.io.Serializable;
import java.util.Objects;

public class NoteDTO implements Serializable {
    private long id;
    private String noteTitle;
    private String noteContent;

    public NoteDTO(long id, String noteTitle, String noteContent) {
        this.id = id;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    NoteDTO() {

    }

    public long getId() {
        return id;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    @Override
    public String toString() {
        return "NoteDTO{" +
                "id=" + id +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteContent='" + noteContent + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteDTO noteDTO = (NoteDTO) o;
        return Objects.equals(noteTitle, noteDTO.noteTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteTitle);
    }
}
