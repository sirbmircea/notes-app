package app.controller;


import app.service.ArgumentsHandlingService;

public class NoteControllerConsole {

    private static class SingletonHolder {
        private static final NoteControllerConsole INSTANCE = new NoteControllerConsole();
    }

    public static NoteControllerConsole getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private NoteControllerConsole() {

    }

    public void consoleRequest(String... args) {
        new ArgumentsHandlingService().parseAndDispatch(args);
    }

}