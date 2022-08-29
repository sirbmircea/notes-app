package app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity

@NamedQuery(name = "Note.list", query = "SELECT n FROM Note n")
@NamedQuery(name = "Note.find", query = "SELECT n FROM Note n WHERE n.noteTitle = :noteTitle")
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String noteTitle;
    private String noteContent;

    public Note() {

    }

    public Note(long id, String noteTitle, String noteContent) {
        this.id = id;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Note with the title: " + noteTitle + " and the content: " + noteContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(noteTitle, note.noteTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteTitle, noteContent);
    }
}
