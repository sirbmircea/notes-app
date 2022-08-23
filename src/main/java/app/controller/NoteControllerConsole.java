package app.controller;


import app.model.ConsoleRequestObject;
import app.util.ConsoleArgumentsParser;
import app.service.ConsoleObjectDispatcherService;

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
        ConsoleRequestObject consoleRequestObject = ConsoleArgumentsParser.parse(args);
        ConsoleObjectDispatcherService consoleObjectDispatcherService = new ConsoleObjectDispatcherService();
        consoleObjectDispatcherService.commandDispatcher(consoleRequestObject);
    }

}