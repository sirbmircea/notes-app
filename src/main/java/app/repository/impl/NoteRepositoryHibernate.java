package app.repository.impl;

import app.model.Note;
import app.repository.interfaces.NoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class NoteRepositoryHibernate implements NoteRepository {

    private final EntityManager entityManager;

    public NoteRepositoryHibernate() {
        entityManager = Persistence.createEntityManagerFactory("nttProject").createEntityManager();
    }

    @Override
    public Note add(Note note) {
        entityManager.getTransaction().begin();
        entityManager.persist(note);
        entityManager.getTransaction().commit();
        return note;
    }

    @Override
    public Set<Note> list() {
        TypedQuery<Note> typedQuery = entityManager.createNamedQuery("Note.list", Note.class);
        List<Note> notes = typedQuery.getResultList();
        return new HashSet<>(notes);

    }

    @Override
    public Note list(String noteTitle) {
        TypedQuery<Note> typedQuery = entityManager.createNamedQuery("Note.find", Note.class);
        typedQuery.setParameter("noteTitle", noteTitle);
        Stream<Note> noteStream = typedQuery.getResultStream();
        Optional<Note> note = noteStream.findAny();
        return note.orElseGet(() -> new Note(0, "", ""));
    }
}
