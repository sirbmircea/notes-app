package app.controller;


import app.model.CliRequestObject;
import app.service.impl.ArgumentsHandlingServiceImpl;
import app.service.interfaces.ArgumentsHandlingService;

import java.util.Optional;

public class NoteControllerCLI {

    private static class SingletonHolder {
        private static final NoteControllerCLI INSTANCE = new NoteControllerCLI();
    }

    public static NoteControllerCLI getInstance() {
        return SingletonHolder.INSTANCE;
    }

    ArgumentsHandlingService argumentsHandlingService;

    private NoteControllerCLI() {
        argumentsHandlingService = new ArgumentsHandlingServiceImpl();
    }

    public void consoleRequest(String... args) {
        Optional<CliRequestObject> cliRequestObject = argumentsHandlingService.parse(args);
        argumentsHandlingService.dispatch(cliRequestObject);
    }

}