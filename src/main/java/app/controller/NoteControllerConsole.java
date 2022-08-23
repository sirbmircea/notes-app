package app.controller;


import app.model.ConsoleRequestObject;
import app.util.ArgumentsHandler;

public class NoteControllerConsole {

    private static class SingletonHolder {
        private static final NoteControllerConsole INSTANCE = new NoteControllerConsole();
    }

    public static NoteControllerConsole getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private NoteControllerConsole() {

    }

    public void consoleRequest(String... args){
        ConsoleRequestObject consoleRequestObject = ArgumentsHandler.parseAndDispatch(args);
        ArgumentsHandler.commandDispatcher(consoleRequestObject);
    }

}
