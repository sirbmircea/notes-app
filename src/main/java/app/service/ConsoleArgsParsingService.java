package app.service;

import app.model.Command;
import app.model.ConsoleRequestObject;

import java.util.Arrays;

public class ConsoleArgsParsingService {
    ConsoleObjectDispatcherService consoleObjectDispatcherService;

    public ConsoleArgsParsingService() {
        consoleObjectDispatcherService = new ConsoleObjectDispatcherService();
    }

    public void parseAndDispatch(String... args) {
        ConsoleRequestObject consoleRequestObject = new ConsoleRequestObject();
        if (args.length == 0) {
            consoleObjectDispatcherService.dispatch(consoleRequestObject);
        } else if (Arrays.stream(Command.values())
                .anyMatch(command -> command.name().toLowerCase().equals(args[0]))) {
            String[] params = Arrays.copyOfRange(args, 1, args.length);
            consoleRequestObject.setArgs(args);
            consoleRequestObject.setParams(params);
            consoleRequestObject.setCommand(Command.valueOf(args[0].toUpperCase()));
            consoleObjectDispatcherService.dispatch(consoleRequestObject);
        } else {
            consoleRequestObject.setArgs(args);
            consoleObjectDispatcherService.dispatch(consoleRequestObject);
        }
    }

}
