package app.service;

import app.repository.NoteRepository;
import app.repository.impl.NoteRepositoryFileSystem;
import app.service.impl.NoteSerializationServiceImpl;
import app.service.impl.NoteServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {

    private Map<Class, Object> registry = new HashMap<>();

    private ServiceProvider() {
        NoteSerializationService noteSerializationService = new NoteSerializationServiceImpl();
        NoteRepository noteRepository = new NoteRepositoryFileSystem(noteSerializationService);
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