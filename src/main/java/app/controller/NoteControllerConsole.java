package app.controller;


import app.service.ConsoleArgsParsingService;

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
        new ConsoleArgsParsingService().parseAndDispatch(args);
    }

}