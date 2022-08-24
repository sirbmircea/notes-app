package app.model;

public class CliRequestObject {
    Command command;
    String[] args;
    boolean valid;

    public CliRequestObject(Command command, String[] args, boolean valid) {
        this.command = command;
        this.args = args;
        this.valid = valid;
    }

    public CliRequestObject(Command command, String[] args) {
        this.command = command;
        this.args = args;
    }

    public CliRequestObject(String[] args) {
        this.args = args;
    }
    public CliRequestObject(){

    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}