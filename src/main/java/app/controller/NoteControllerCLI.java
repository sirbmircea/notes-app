package app.controller;


import app.service.impl.ArgumentsHandlingServiceImpl;

public class NoteControllerCLI {

    private static class SingletonHolder {
        private static final NoteControllerCLI INSTANCE = new NoteControllerCLI();
    }

    public static NoteControllerCLI getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private NoteControllerCLI() {

    }

    public void consoleRequest(String... args) {
        new ArgumentsHandlingServiceImpl().parseAndDispatch(args);
    }

}