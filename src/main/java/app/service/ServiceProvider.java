package app.service;

import app.repository.impl.NoteRepositoryHibernate;
import app.repository.interfaces.NoteRepository;
import app.service.impl.NoteServiceImpl;
import app.service.interfaces.NoteService;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {

    private Map<Class, Object> registry = new HashMap<>();

    private ServiceProvider() {
        NoteRepository noteRepository = new NoteRepositoryHibernate();
        registry.put(NoteService.class, new NoteServiceImpl(noteRepository));
    }

    private static final class SingletonHolder {
        private static final ServiceProvider INSTANCE = new ServiceProvider();
    }

    public static ServiceProvider getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public <T> T get(Class<T> classT) {
        Object service = registry.get(classT);
        return classT.cast(service);
    }
}