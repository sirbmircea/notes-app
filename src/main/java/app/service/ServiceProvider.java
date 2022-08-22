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
        registry.put(NoteSerializationService.class, noteSerializationService);

        NoteRepository noteRepository = new NoteRepositoryFileSystem(noteSerializationService);
        registry.put(NoteRepository.class, noteRepository);

        NoteService noteService = new NoteServiceImpl(noteRepository);
        registry.put(NoteService.class, noteService);
    }

    private final static class SingletonHolder {
        private final static ServiceProvider INSTANCE = new ServiceProvider();
    }

    public static ServiceProvider getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public <T> T get(Class<T> classT) {
        Object service = registry.get(classT);
        return classT.cast(service);
    }
}