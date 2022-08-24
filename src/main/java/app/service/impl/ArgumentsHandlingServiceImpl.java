package app.service.impl;

import app.NotesAppApplication;
import app.exceptions.ExceptionContainer;
import app.model.CliRequestObject;
import app.model.Command;
import app.model.Note;
import app.service.ServiceProvider;
import app.service.interfaces.ArgumentsHandlingService;
import app.service.interfaces.NoteService;
import org.springframework.boot.SpringApplication;

import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

public class ArgumentsHandlingServiceImpl implements ArgumentsHandlingService {

    private static final Logger logger = Logger.getLogger(ArgumentsHandlingServiceImpl.class.getName());

    private NoteService noteService;

    public ArgumentsHandlingServiceImpl() {
        noteService = ServiceProvider.getInstance().get(NoteService.class);
    }


    public Optional<CliRequestObject> parse(String... args){
        Optional<Command> validCmdAndParams = Arrays.stream(Command.values())
                .filter(command -> command.getActualCommand().equals(args[0]))
                .filter(command -> command.getNoOfParams() == args.length - 1)
                .findAny();
        Optional<Command> validCommand = Arrays.stream(Command.values())
                .filter(command -> command.getActualCommand().equals(args[0]))
                .findAny();
        if(validCmdAndParams.isPresent()){
            return Optional.of(new CliRequestObject(validCmdAndParams.get(), args, true));
        }else if(validCommand.isPresent()){
            return Optional.of(new CliRequestObject(validCommand.get(), args, false));
        }else if(args.length!=0){
            return Optional.of(new CliRequestObject(args));
        }
        return Optional.empty();
    }

    public void dispatch(Optional<CliRequestObject> optionalCliRequestObject){
        if(optionalCliRequestObject.isPresent()){
            CliRequestObject cliRequestObject = optionalCliRequestObject.get();
            if(cliRequestObject.getCommand()!=null){
                if(cliRequestObject.isValid()){
                    switch (cliRequestObject.getCommand()){
                        case ADD:
                            noteService.add(new Note(cliRequestObject.getArgs()[1], cliRequestObject.getArgs()[2]));
                            break;
                        case LIST:
                            noteService.list();
                            break;
                        case FIND:
                            noteService.list(cliRequestObject.getArgs()[1]);
                            break;
                        case RUNSPRINGBOOT:
                            SpringApplication.run(NotesAppApplication.class, cliRequestObject.getArgs());
                            break;
                    }
                }else {
                    String exception = String.format(ExceptionContainer.INVALID_NO_OF_ARGS, cliRequestObject.getCommand().getActualCommand(), cliRequestObject.getCommand().getNoOfParams());
                    logger.severe(exception);
                }
            }else{
                String exception = String.format(ExceptionContainer.NO_SUCH_METHOD, cliRequestObject.getArgs()[0]);
                logger.severe(exception);
            }
        }else {
            logger.severe(ExceptionContainer.NO_ARGS_GIVEN);
        }
    }

}
