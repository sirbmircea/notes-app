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

    private EntityManager entityManager;

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
        String sql = "SELECT n FROM Note n";
        entityManager.getTransaction().begin();
        TypedQuery<Note> typedQuery = entityManager.createQuery(sql, Note.class);
        List<Note> notes = typedQuery.getResultList();
        entityManager.getTransaction().commit();
        return new HashSet<>(notes);

    }

    @Override
    public Note list(String noteTitle) {
        String sql = "SELECT n FROM Note n WHERE n.noteTitle = :noteTitle";
        entityManager.getTransaction().begin();
        TypedQuery<Note> typedQuery = (TypedQuery<Note>) entityManager.createQuery(sql);
        typedQuery.setParameter("noteTitle", noteTitle);
        Stream<Note> noteStream = typedQuery.getResultStream();
        Optional<Note> note = noteStream.findAny();
        entityManager.getTransaction().commit();
        return note.orElseGet(()->new Note("", ""));
    }
}
